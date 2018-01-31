# FROM alpine:latest # Ideal for more lean images (more complex Dockerfile setup)
FROM ubuntu:16:04 # Simple setup

COPY requirements.txt /
COPY id_fulfilment-platform /
# May want to copy in an additional secret for script parameters (API keys, etc.)

RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -q -y openjdk-8-jdk build-essential python-dev python-pip && \
  apt-get autoremove && \
  apt-get clean

RUN pip install --no-cache-dir -r requirements.txt && \
	pip install --user virtualenv && \
	pip install --user virtualenvwrapper

# Use ssh deploy key secret "id_fulfilment-platform" tp fetch script from github.

# Build java application

# Start service etc.

EXPOSE 8089

# These should be built as part of the build process.
# ADD target/inventory-updater-web-app.jar inventory-updater-web-app.jar
# ENTRYPOINT ["java", "-jar", "inventory-updater-web-app.jar"]
# CMD ["/quickstart.sh"] # Example
