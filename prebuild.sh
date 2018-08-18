#!/bin/bash

rm src/main/resources/core-1.0-SNAPSHOT.jar
rm src/main/resources/library-1.0-SNAPSHOT.jar
rm src/main/resources/controller-1.0-SNAPSHOT.jar

mvn org.apache.maven.plugins:maven-dependency-plugin:get -DrepoUrl=https://oss.sonatype.org/content/repositories/snapshots -Dartifact=io.cresco:core:1.0-SNAPSHOT
mvn org.apache.maven.plugins:maven-dependency-plugin:get -DrepoUrl=https://oss.sonatype.org/content/repositories/snapshots -Dartifact=io.cresco:library:1.0-SNAPSHOT
mvn org.apache.maven.plugins:maven-dependency-plugin:get -DrepoUrl=https://oss.sonatype.org/content/repositories/snapshots -Dartifact=io.cresco:controller:1.0-SNAPSHOT

cp ~/.m2/repository/io/cresco/core/1.0-SNAPSHOT/core-1.0-SNAPSHOT.jar src/main/resources/
cp ~/.m2/repository/io/cresco/library/1.0-SNAPSHOT/library-1.0-SNAPSHOT.jar src/main/resources/
cp ~/.m2/repository/io/cresco/controller/1.0-SNAPSHOT/controller-1.0-SNAPSHOT.jar src/main/resources/
