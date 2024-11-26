FROM openjdk:17-jdk-alpine
COPY target/java_code_test-0.0.1-SNAPSHOT.jar java_code_test.jar
ENTRYPOINT ["java", "-jar", "/java_code_test.jar"]