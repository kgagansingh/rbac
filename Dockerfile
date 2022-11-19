FROM openjdk:17
COPY . /app
WORKDIR /app
RUN microdnf install findutils
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c" , "./gradlew clean && ./gradlew test && ./gradlew bootRun"]