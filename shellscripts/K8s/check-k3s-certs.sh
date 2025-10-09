#!/bin/bash
# =====================================================
# K3s Certificate Expiration Checker
# =====================================================

TLS_DIR="/var/lib/rancher/k3s/server/tls"
WARN_DAYS=30

if [ ! -d "$TLS_DIR" ]; then
  echo "❌ TLS directory not found: $TLS_DIR"
  exit 1
fi

echo "🔍 Checking K3s TLS certificates under: $TLS_DIR"
echo "⚠️  Warn if certificate expires within $WARN_DAYS days"
echo "-----------------------------------------------------"

# find all certificates
find "$TLS_DIR" -type f -name "*.crt" | while read -r cert; do
  subject=$(openssl x509 -in "$cert" -noout -subject | sed 's/^subject= //')
  notAfter=$(openssl x509 -in "$cert" -noout -enddate | cut -d= -f2)
  expireEpoch=$(date -d "$notAfter" +%s)
  nowEpoch=$(date +%s)
  remaining=$(( (expireEpoch - nowEpoch) / 86400 ))

  if [ $remaining -le 0 ]; then
    color="\033[0;31m"  # red - expired
    status="EXPIRED"
  elif [ $remaining -le $WARN_DAYS ]; then
    color="\033[0;33m"  # yellow - soon
    status="Expiring soon"
  else
    color="\033[0;32m"  # green - OK
    status="OK"
  fi

  printf "${color}%-50s %-25s %4s days left (%s)\033[0m\n" \
    "$(basename "$cert")" "$status" "$remaining" "$subject"
done

echo "-----------------------------------------------------"
echo "✅ Done."
