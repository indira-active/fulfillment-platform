FROM rappdw/docker-java-python
ADD target/inventory-updater-web-app.jar inventory-updater-web-app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "inventory-updater-web-app.jar"]