#!/usr/bin/env bash

bash -x gradlew html:dist

ssh root@lavamancer.com "rm -rf /root/nginx/html/index/misc"
ssh root@lavamancer.com "mkdir /root/nginx/html/index/misc"
scp -r html/build/dist/* root@lavamancer.com:/root/nginx/html/index/misc
