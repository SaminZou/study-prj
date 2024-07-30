#!/bin/bash

set -o nounset
set -o errexit

# 标准用法模板
# cat <<EOF>> filename
# line1
# line2
# EOF

cat <<EOF>> test.txt
line1
line2
line3
EOF