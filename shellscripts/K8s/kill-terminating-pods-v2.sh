#!/bin/bash
echo "=== Checking for Terminating Pods ==="
pods=$(kubectl get pods -A --no-headers | awk '$4=="Terminating"{print $1":"$2}')
if [ -z "$pods" ]; then
  echo "✅ No terminating pods found."
  exit 0
fi

echo "=== Force deleting terminating pods ==="
echo "$pods" | while IFS=: read -r ns pod; do
  echo "Deleting $ns/$pod ..."
  kubectl delete pod "$pod" -n "$ns" --grace-period=0 --force
done
echo "=== Done ==="