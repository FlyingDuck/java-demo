#!/bin/bash
echo 'Start with sandbox shell'
echo $JAVA_HOME
echo `java -version`
DIR=$(dirname $(cd `dirname $0`;pwd))
java \
    -server \
    -javaagent:lib/quasar-core-0.7.9.jar \
    -XX:+PrintGCDetails \
    -XX:+PrintGCDateStamps \
    -Xloggc:./gc.log \
    -jar ${DIR}/${pom.build.finalName}-server.jar