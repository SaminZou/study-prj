#!/bin/bash

# 检查是否提供了 namespace 参数
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <namespace>"
    exit 1
fi

NAMESPACE="$1"  # 使用脚本的第一个参数作为 namespace

# 获取指定 namespace 下的所有 pods
pods=$(kubectl get pods -n "$NAMESPACE" | grep Terminating | awk '{print $1}')

# 检查是否有 pods 存在
if [ -z "pods" ]; then
    echo "No pods found in namespace: $NAMESPACE"
    exit 0
fi

echo $pods

# 遍历 pods
for pod in $(echo "$pods"); do
    echo "killing $pod to 0 replicas in namespace: $NAMESPACE..."
    sudo kubectl delete pod $pod -n $NAMESPACE --force --grace-period=0
done

echo "All pods in namespace: $NAMESPACE have been killed."