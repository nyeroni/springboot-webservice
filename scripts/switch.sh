#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc  # 하나의 문장으로 만들어 파이프 라인으로 넘겨주기 위해 echo 사용 -> 앞에서 넘겨준 문장을 service-url.inc에 덮어씀

  echo "> 엔진엑스 Reload"
  sudo service nginx reload # restart는 잠시 끊김 현상, reload는 끈김 없이 다시 불러옴.
}