FROM openjdk:11-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} conversionfactor.jar
ENTRYPOINT ["java","-jar","/conversionfactor.jar"]