使用 JNI 的 jdbc 连接方式本地需要安装 taos 客户端，本 demo 使用的 tdengine 服务端版本是 2.4.0.5，taos 客户端是 2.4.0.5-Windows-x64，taos-jdbcdriver 版本是 2.0.36

> Dockerfile 事例中的 TDengine-client-2.4.0.5-Linux-x64.tar.gz 和 libtaos.so.2.4.0.5 可以在官网中下载

# 供参考镜像打包命令

$ docker build -t <ip>:<port>/td-test:v1 .

# 供参考 docker-compose.yml

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

# 供参考的 K8s deployment.yaml

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

# 总结

如果需要使用原生连接，以下是注意事项：

1. 应用程序需要依赖 tdengine 提供的 client，下载对应版本 windows 或者 Linux 的客户端，放置在工作目录

2. 需要修改 hosts 文件添加 tdengine 服务端 ip 和 hostname 的映射关系

3. 应用运行时，会自动依赖 /etc/taos/taos.cfg 文件，里面注意填写关键配置

| 如果是在 K8s 中运行，所有的 Node 需要配置 /etc/hosts 文件，添加 tdengine 服务端 ip 和 hostname 的映射关系

4. taos -s "show databases;" 可以测试连通性