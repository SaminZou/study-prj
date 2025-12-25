#!/bin/bash
echo "=== Checking for NodeAffinity Pods ==="
pods=$(kubectl get pods -A --no-headers | awk '$4=="NodeAffinity"{print $1":"$2}')
if [ -z "$pods" ]; then
  echo "✅ No NodeAffinity pods found."
  exit 0
fi

echo "=== Force deleting NodeAffinity pods ==="
echo "$pods" | while IFS=: read -r ns pod; do
  echo "Deleting $ns/$pod ..."
  kubectl delete pod "$pod" -n "$ns" --grace-period=0 --force
done
echo "=== Done ==="