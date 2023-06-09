# 1 下载需要的资源

- TDengine-client-2.4.0.5-Linux-x64.tar.gz

- libtaos.so.2.4.0.5（TDengine-client-2.4.0.5-Linux-x64.tar.gz 解压后，driver 目录中）

- TDengine-client-2.4.0.5-Windows-x64.exe（ Windows 环境调试用）

- Java 项目中对应 taos-jdbcdriver 2.0.36

> 官网可以下载以上内容

# 2 供参考镜像打包命令

$ docker build -t <ip>:<port>/td-test:v1 .

或者使用以下轻量级构建方式，区别在于使用完整版的构建，在容器中可以使用 taos 命令，以及查看相应的操作日志

$ docker build -t <ip>:<port>/td-test:v1 -f Dockerfile-simple .

# 3 供参考的启动方式

## 3.1 docker-compose.yml（ docker 容器启动 ）

```yaml
version: "3.3"

services:
    td-test:
        container_name: td-test
        restart: always
        image: <ip>:<port>/td-test:v1
        extra_hosts:
          - "tdengine-dev:<your_ip>"
        environment:
          - tdengine-host=jdbc:TAOS://tdengine-dev:6030/dev
        ports:
            - "8080:8080"
```

## 3.2 deployment.yaml（ K8s 启动 ）

```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: td-test
  namespace: test
  labels:
    workload.user.cattle.io/workloadselector: td-test
spec:
  selector:
    matchLabels:
      workload.user.cattle.io/workloadselector: td-test
  template:
    metadata:
      labels:
        workload.user.cattle.io/workloadselector: td-test
    spec:
      hostAliases:
      # 修改为 td 服务的 ip      
      - ip: "192.168.10.10"
        hostnames:
        - "tdengine-dev"
      containers:
        - name: td-test
          image: 192.168.10.10:5000/td-test:latest
          imagePullPolicy: Always
          volumeMounts:
            - name: taos-test-config
              mountPath: /etc/taos/taos.cfg
              subPath: taos.cfg
          ports:
            - containerPort: 8080
              name: http
              protocol: TCP
          env:
          - name: tdengine-host
            value: tdengine-dev
          readinessProbe:
            failureThreshold: 3
            initialDelaySeconds: 20
            periodSeconds: 30
            successThreshold: 1
            tcpSocket:
              port: 8080
            timeoutSeconds: 1
          livenessProbe:
            failureThreshold: 3
            initialDelaySeconds: 20
            periodSeconds: 30
            successThreshold: 1
            tcpSocket:
              port: 8080
            timeoutSeconds: 1
          resources:
            limits:
              cpu: 500m
              memory: 512Mi
            requests:
              cpu: 500m
              memory: 512Mi
      volumes:
        - name: taos-test-config
          configMap:
            name: taos-test-config
            items:
            - key: taos.cfg
              path: taos.cfg
      restartPolicy: Always
  replicas: 1

---
apiVersion: v1
kind: ConfigMap
metadata:
    namespace: test
    name: taos-test-config
data:
  taos.cfg: |
    firstEp                   tdengine-dev:6030
    fqdn                      tdengine-dev
    serverPort                6030

---
apiVersion: v1
kind: Service
metadata:
  name: td-test
  namespace: test
spec:
  selector:
    workload.user.cattle.io/workloadselector: td-test
  ports:
    - name: td-test
      port: 8080
      protocol: TCP
      targetPort: 8080
```

# 4 tips

- 使用原生连接方式，应用程序需要依赖 tdengine 提供的 client，下载对应版本 windows 或者 Linux 的客户端，放置在工作目录

- 重要的配置文件 /etc/taos/taos.cfg

- 在 K8s 中运行，所有的 Node 需要配置 /etc/hosts 文件，添加 tdengine 服务端 ip 和 hostname 的映射关系

- taos -s "show databases;" 可以测试连通性