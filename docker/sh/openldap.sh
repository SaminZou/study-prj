#!/bin/bash
docker run --name openldap -itd --restart always \
--publish 389:389 \
--env LDAP_DOMAIN="example.org" \
--env LDAP_ADMIN_PASSWORD="admin" \
osixia/openldap