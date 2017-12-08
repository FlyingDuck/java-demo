#!/bin/bash
echo 'Start with sandbox shell'
echo $JAVA_HOME
echo `java -version`
DIR=$(dirname $(cd `dirname $0`;pwd))
java \
    -server \
    -XX:+PrintGCDetails \
    -XX:+PrintGCDateStamps \
    -Xloggc:./gc.log \
    -jar ${DIR}/${pom.build.finalName}-server.jar
