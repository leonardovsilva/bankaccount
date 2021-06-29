FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bankaccount.jar
ENTRYPOINT ["java","-jar","/bankaccount.jar"]