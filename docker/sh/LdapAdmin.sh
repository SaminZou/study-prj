#!/bin/bash

# LDAP 是一种协议，ldap-admin 是一种具体实现应用
docker run --name ldap-admin \
-p 6443:80 -itd \
-e LDAP_SERVER_NAME="ldap" \
-e LDAP_SERVER_BASE_DN="cn=admin,dc=example,dc=org" \
-e LDAP_SERVER_HOST="samin.dev" \
-e LDAP_SERVER_PORT="389" \
accenture/adop-ldap-phpadmin