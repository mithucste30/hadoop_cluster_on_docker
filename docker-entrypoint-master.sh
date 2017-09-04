#!/bin/bash

for x in `cd /etc/init.d ; ls hadoop-hdfs-*` ; do service $x start ; done


su - hdfs -c "hadoop fs -mkdir /tmp"
su - hdfs -c "hadoop fs -chmod -R 1777 /tmp"

su - hdfs -c "hadoop fs -mkdir -p /user/root"
su - hdfs -c "hadoop fs -chown root:root /user/root"

su - hdfs -c "hadoop fs -mkdir -p /user/history"
su - hdfs -c "hadoop fs -chmod -R 1777 /user/history"
su - hdfs -c "hadoop fs -chown mapred:hadoop /user/history"

su - hdfs -c "hadoop fs -mkdir -p /var/log/hadoop-yarn"
su - hdfs -c "hadoop fs -chown yarn:mapred /var/log/hadoop-yarn"

mkdir -p /data/1/yarn/local
mkdir -p /data/1/yarn/logs
chown yarn:yarn -R /data/1/yarn/local
chown yarn:yarn -R /data/1/yarn/logs

service hadoop-yarn-resourcemanager restart

service hadoop-mapreduce-historyserver restart

tail -f /dev/null
