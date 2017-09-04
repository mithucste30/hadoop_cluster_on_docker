#!/bin/bash

for x in `cd /etc/init.d ; ls hadoop-hdfs-*` ; do service $x start ; done

mkdir -p /data/1/yarn/local
mkdir -p /data/1/yarn/logs
chown yarn:yarn -R /data/1/yarn/local
chown yarn:yarn -R /data/1/yarn/logs

service hadoop-yarn-nodemanager restart

