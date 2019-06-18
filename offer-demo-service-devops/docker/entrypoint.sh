#!/bin/sh

echo 'Logging image build information...'
cat build_information.json >> ./logs/info.log

echo 'Launching application...'
exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar -server ${JAVA_OPTS} ${service-to-deploy}.jar
