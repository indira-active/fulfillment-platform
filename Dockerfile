# FROM alpine:latest # Ideal for more lean images (more complex Dockerfile setup)
FROM ubuntu:16:04 # Simple setup

# COPY requirements.txt /
# COPY id_fulfilment-platform /
# COPY SRC/ /
# COPY pom.xml /
COPY . /

# May want to copy in an additional secret for script parameters (API keys, etc.)

# Install dependencies
RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -q -y openjdk-8-jdk maven build-essential git python-dev python-pip && \
  apt-get autoremove && \
  apt-get clean

RUN pip install --no-cache-dir -r requirements.txt && \
	pip install --user virtualenv && \
	pip install --user virtualenvwrapper

# Use ssh deploy key secret "id_fulfilment-platform" tp fetch script from github.
# RUN git clone script repo


# Build java application
RUN mvn clean intall

# Start service etc.
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "inventory-updater-web-app.jar"]