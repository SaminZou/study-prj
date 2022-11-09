#!/bin/bash

# 返回时间
getDate() {
  local date="$(date "+%Y-%m-%d %H:%M:%S")"
  echo $date
}

startDate=$(getDate)

echo "#开始时间：$startDate" >> run.log

# 单位秒
sleep 2

endDate=$(getDate)

echo "#结束时间：$endDate" >> run.log

cat run.log