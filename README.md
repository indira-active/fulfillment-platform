[![CircleCI](https://circleci.com/gh/indira-active/fulfillment-platform.svg?style=svg&circle-token=dede407d73b7eb5b0427274c18cfdd6a4ba083bb)](https://circleci.com/gh/indira-active/fulfillment-platform)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e2906ab1ca4c4ea9a5a01baee82f572a)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=indira-active/fulfillment-platform&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/indira-active/fulfillment-platform/branch/master/graph/badge.svg?token=Fy61fd6xfd)](https://codecov.io/gh/indira-active/fulfillment-platform)
[![Known Vulnerabilities](https://snyk.io/test/github/indira-active/fulfillment-platform/badge.svg)](https://snyk.io/test/github/indira-active/fulfillment-platform)

# InventoryUpdaterWebApp
Web application that will be called to update product inventory from our suppliers
  
    git clone git@github.com:indira-active/fulfillment-platform.git

# Set up environment (first time only)
## Install gCloud SDK CDK
https://cloud.google.com/sdk/docs/

    gcloud init
    gcloud components install kubectl

## Select project and auth
    gcloud auth login
    gcloud config set project zip-zap-gateway



# Important Secrets

## Encrypted Keys (Files):  
Every key is hosted securely and encrypted on Cloud KMS. Each file ending with *.enc is the encrypted version of the file and safe to commit. These can de be decrypted locally for testing/development, or automatically during the production build process:
  

| Cloud KMS key          | Command to retrieve |
|-------------------------|-------------|
| **scripts-deploy-key** `gcloud cli` | gcloud kms decrypt --ciphertext-file=id_fulfilment-platform.enc --plaintext-file=id_fulfilment-platform--location=global --keyring=fulfilment-platform --key=scripts-deploy-key |
| **supplier-master-sql-credentials** `gcloud cli` | gcloud kms decrypt --ciphertext-file=credentials.json.enc --plaintext-file=credentials.json--location=global --keyring=fulfilment-platform --key=scripts-deploy-key |

### Decrypt file:
    gcloud kms decrypt --ciphertext-file=[INPUT_FILE] --plaintext-file=[OUTPUT_FILE] --location=global --keyring=fulfilment-platform --key=scripts-deploy-key
    # These are exluded in .gitignore so they won't be committed.

## Encrypted Secrets (Enviorment Variables)
These enviorment variables are automatically set by our CI/CD systems. However locally they will be need to set manually. They can easily be retrieved via kubectl secret name, and set in an enviorment: 

| Secret location            | Description |
|-------------------------|-------------|
| **cloudsql-db-credentials** `kubectl` | Containes database enviorment variables below |
| **cloudsql-instance-credentials** `kubectl` | Contains Cloud SQL instance connection configuration |
| **okta-oauth** `kubectl` | Contains Okta Client ID enviorment variables below  |
| **Codecov token** `go/fp-codecov-token` | Lists repo coverage enviorment variables below |


### Retrive and set variables:
    # Create an copy .env file
    cp .env.example .env

    # Gather secrets from CLI or GUI
    kubectl get secret <SECRET_NAME> -o yaml
    
    # Edit file to add applicable variables
    # Then Set enviorment variables locally
    source ./.env

| Variable                | Description |
|-------------------------|-------------|
| **DB_USER** `required to run` | Cloud SQL database username |
| **DB_PASSWORD** `required to run` | Cloud SQL database password |
| **OKTA_ISSUER** `required to run` | URL as configured in Okta to issue the SAML request |
| **OKTA_CLIENTID** `required for run` | Okta Client ID used in web app Oauth process |
| **OKTA_CLIENTSECRET**`required for run` | Okta Okta Client Secret used in web app Oauth process |
| **CODECOV_TOKEN**`required to build docker image local` | Token to submit coverage results after tests pass |



# Development
    # Install dependencies & devDependencies
    mvn clean install
    
    # Start server
    java -jar ./target/fulfillment-platform.jar

    # Run tests
    mvn clean test

    # Check test coverage
    mvn clean test jacoco:report

    # Check vulnerabilities
    npm install -g snyk && npm test



# Production
## Build & test local
    docker-compose build
    # Note subsequent builds will be cached, add --no-cache to rebuild from sctatch.
    TODO: nathang - Figure out ENV's, sidecar container etc.


## Run local
    docker-compose up 
    # Use -d to detach and run in background
    # Note subsequent runs will re-use the image, add --build to rebuild image. 
    


# Misc
Manually build docker image and upload to registry

    gcloud container builds submit --config cloudbuild.yaml .

