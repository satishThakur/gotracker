FROM sbtscala/scala-sbt:openjdk-17.0.2_1.7.1_3.2.0

RUN mkdir -p gotracker
WORKDIR /root/gotracker
ADD . .
RUN sbt assembly
RUN ls -lrt  /root/gotracker/target/scala-3.2.0

ENTRYPOINT ["java", "-jar", "/root/gotracker/target/scala-3.2.0/gotracker-assembly-0.1.0-SNAPSHOT.jar"]


