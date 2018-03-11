[![Build Status](https://travis-ci.com/indira-active/fulfillment-platform.svg?token=s8nDqBPzyxfbb3fVZTFy&branch=master)](https://travis-ci.com/indira-active/fulfillment-platform) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/e2906ab1ca4c4ea9a5a01baee82f572a)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=indira-active/fulfillment-platform&amp;utm_campaign=Badge_Grade)

# InventoryUpdaterWebApp
Web application that will be called to update product inventory from our suppliers
  
    git clone git@github.com:indira-active/fulfillment-platform.git

# Set up environment (first time only)
## Install gCloud SDK CDK
https://cloud.google.com/sdk/docs/

    gcloud init
    # Need to install kubectl addon

## Select project and auth
    gcloud auth login
    gcloud config set project zip-zap-gateway



# Important Secrets

## Encrypted Keys (Files):  
Every key is hosted securely and encrypted on Cloud KMS. Each file ending with *.enc is the encrypted version of the file and safe to commit. These can de be decrypted locally for testing/development, or automatically during the production build process:
  
* scripts-deploy-key
    * ciphertext-file=id_fulfilment-platform.enc
    * plaintext-file=id_fulfilment-platform
* supplier-master-sql-credentials
    * ciphertext-file=credentials.json.enc
    * plaintext-file=credentials.json

### Decrypt file:
    gcloud kms decrypt --ciphertext-file=[INPUT_FILE] --plaintext-file=[OUTPUT_FILE] --location=global --keyring=fulfilment-platform --key=[SPECIFED_KEY]
    # These are exluded in .gitignore so they won't be committed.

## Encrypted Secrets (Enviorment Variables)
These enviorment variables are automatically set by our CI/CD systems. However locally they will be need to set manually. They can easily be retrieved via kubectl secret name, and set in an enviorment: 

* cloudsql-db-credentials
    * DB_USER
    * DB_PASSWORD
* cloudsql-instance-credentials
* okta-oauth
    * OKTA_ISSUER
    * OKTA_CLIENTID
    * OKTA_CLIENTSECRET

### Retrive and set variable:
    kubectl get secret <SECRET_NAME> -o yaml
    # Sets variable in enviorment
    export <VARIABLE_NAME>=<VARIABLE_VALUE>



# Development
    # Install dependencies & devDependencies
    mvn clean install
    # Start server
    
    # Run tests
    
    # Check test coverage
    


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

