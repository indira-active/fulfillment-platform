# ---- Build ----
FROM openjdk:8-jdk-alpine3.7 as build
LABEL maintainer="Nathan Guenther <nathang@indiraactive.com>"

WORKDIR /build

# # Install build dependencies
RUN apk --update add --no-cache maven curl git bash openssh openssl python-dev py-pip

# Setup temp ssh key to pull from private git repo
COPY id_fulfilment-platform* ./ 

RUN mkdir -p /root/.ssh/ && \
chmod 0700 /root/.ssh && \
cat ./id_fulfilment-platform > /root/.ssh/id_rsa && \
chmod 0700 /root/.ssh/id_rsa && \
ssh-keyscan github.com > /root/.ssh/known_hosts

# Import latest script
RUN git clone git@github.com:indira-active/Scripts.git

# Copy remaining src
COPY . /build

# Build java application
RUN mvn clean install -Dmaven.test.skip=true

# Cleanup ssh keys 
RUN rm -vf id_fulfilment-platform /root/.ssh/id*


# ---- Test ----
FROM build as test
# TODO: nathang - Can we split out devDependencies with mvn?

# Run tests and generate coverage
ARG CODECOV_TOKEN
RUN mvn clean test jacoco:report
RUN curl -s https://codecov.io/bash > .codecov && \
chmod +x .codecov && \
 ./.codecov -t $CODECOV_TOKEN


# ---- Release ----
FROM openjdk:8-jdk-alpine3.7 as release
# TODO: nathang - Can we cache runtime dependencies from a base stage?

# Install Runtime dependencies
COPY requirements.txt .
RUN apk --update add --no-cache maven python-dev py-pip && \
pip install -r requirements.txt

WORKDIR /runtime
COPY --from=build /build/target/fulfillment-platform.jar ./target/fulfillment-platform.jar
COPY --from=build /build/Scripts/sync_inventory.py ./Scripts/sync_inventory.py

# Start service
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "./target/fulfillment-platform.jar"]
