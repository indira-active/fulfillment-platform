# FROM alpine:latest # Ideal for more lean images (more complex Dockerfile setup)
########################################################################
# BUILD STAGE 1 - Start with the same image that will be used at runtime
FROM ubuntu:latest as builder

COPY . /build
WORKDIR /build

# Debug
RUN pwd && ls -la

# May want to copy in an additional secret for script parameters (API keys, etc.)

# Install build dependencies
RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -q -y openjdk-8-jdk maven build-essential git ssh && \
  apt-get autoremove && \
  apt-get clean

# Setup temp ssh key to pull from private git repo
RUN mkdir -p /root/.ssh/ && \
	chmod 0700 /root/.ssh && \
	cat ./id_fulfilment-platform > /root/.ssh/id_rsa && \
	chmod 0700 /root/.ssh/id_rsa && \
	cat /root/.ssh/id_rsa && \
	ssh-keyscan github.com > /root/.ssh/known_hosts

# Import latest script
RUN git clone git@github.com:indira-active/Scripts.git
# May want to move script from ./Scripts/ directory to a new location. Or update webapp.
RUN pwd && ls -la

# Build java application
RUN mvn clean install -Dmaven.test.skip=true

# Cleanup ssh keys 
RUN rm -vf id_fulfilment-platform /root/.ssh/id*

# Debug
RUN pwd && ls -la

########################################################################
# BUILD STAGE 2 - copy the compiled build dir into a fresh runtime image
FROM ubuntu:latest as runtime
COPY --from=builder /build /build2
WORKDIR /build2

# Debug
RUN pwd && ls -la

# Install runtime dependencies
RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -q -y openjdk-8-jdk python-dev python-pip && \
  apt-get autoremove && \
  apt-get clean

RUN pip install --no-cache-dir -r requirements.txt && \
	pip install --user virtualenv && \
	pip install --user virtualenvwrapper

# Start service
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "./target/fulfillment-platform.jar"]
