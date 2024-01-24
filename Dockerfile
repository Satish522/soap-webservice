From openjdk:17
EXPOSE 8080
ADD target/soap-webservice.jar soap-webservice.jar
ENTRYPOINT [ "java", "-jar", "soap-webservice.jar" ]
