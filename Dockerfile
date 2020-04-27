FROM openjdk:8u121-jdk

EXPOSE 8080

ARG JAR_FILE=target/covid-tracker-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} covid-tracker.jar

ENTRYPOINT ["java","-Xmx100m", "-jar","/covid-tracker.jar"]