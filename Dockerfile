# FROM alpine:latest # Ideal for more lean images (more complex Dockerfile setup)
# ---- Base ----
# FROM ubuntu:16.04 as base
FROM openjdk:8-jdk-alpine3.7 as base
LABEL maintainer="Nathan Guenther <nathang@indiraactive.com>"

WORKDIR /build

# Debug
RUN pwd && ls -la

# Install build dependencies
RUN apk --update add --no-cache maven git openssh bash python-dev py-pip

# Install runtime dependencies
COPY requirements.txt .
RUN pip install -r requirements.txt

# Setup temp ssh key to pull from private git repo
COPY id_fulfilment-platform .
RUN mkdir -p /root/.ssh/ && \
	chmod 0700 /root/.ssh && \
	cat ./id_fulfilment-platform > /root/.ssh/id_rsa && \
	chmod 0700 /root/.ssh/id_rsa && \
	ssh-keyscan github.com > /root/.ssh/known_hosts

# Import latest script
RUN git clone git@github.com:indira-active/Scripts.git

# Debug
# May want to move script from ./Scripts/ directory to a new location. Or update webapp.
RUN pwd && ls -la

# Copy remaining src
COPY . /build

# Build java application
RUN mvn clean install -Dmaven.test.skip=true

# Cleanup ssh keys 
RUN rm -vf id_fulfilment-platform /root/.ssh/id*

# Debug
RUN pwd && ls -la


# ---- Test ----
# FROM base as test
# Run tests and coverage


# ---- Release ----
# FROM ubuntu:latest as runtime
FROM base as release

# Debug
RUN pwd && ls -la

WORKDIR /runtime
COPY --from=base /build/target/ ./target/
COPY --from=base /build/Scripts/ ./Scripts/

# Debug
RUN pwd && ls -la

# Start service
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "./target/fulfillment-platform.jar"]
