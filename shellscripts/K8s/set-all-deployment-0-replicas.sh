#!/bin/bash

# 检查是否提供了 namespace 参数
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <namespace>"
    exit 1
fi

# 使用脚本的第一个参数作为 namespace
NAMESPACE="$1"

# 获取指定 namespace 下的所有 Deployments
deployments=$(kubectl get deployments -n "$NAMESPACE" -o jsonpath='{.items[*].metadata.name}')

# 检查是否有 Deployments 存在
if [ -z "$deployments" ]; then
    echo "No deployments found in namespace: $NAMESPACE"
    exit 0
fi

# 遍历 Deployments 并设置副本数为 0
for deploy in $deployments; do
    echo "Scaling $deploy to 0 replicas in namespace: $NAMESPACE..."
    kubectl scale deployment "$deploy" --replicas=0 -n "$NAMESPACE"
done

echo "All deployments in namespace: $NAMESPACE have been scaled to 0 replicas."