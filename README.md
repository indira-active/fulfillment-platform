[![Build Status](https://travis-ci.com/indira-active/fulfillment-platform.svg?token=s8nDqBPzyxfbb3fVZTFy&branch=master)](https://travis-ci.com/indira-active/fulfillment-platform) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/e2906ab1ca4c4ea9a5a01baee82f572a)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=indira-active/fulfillment-platform&amp;utm_campaign=Badge_Grade)

# InventoryUpdaterWebApp
Web application that will be called to update product inventory from our suppliers. 

# Set up environment (first time only)
## Install gCloud SDK CDK
https://cloud.google.com/sdk/docs/

    gcloud init

## Select project and auth
    gcloud auth login
    gcloud config set project zip-zap-gateway



# Important Secrets
Each file ending with *.enc is the encrypted version of the file and safe to commit. These can de be decrypted locally for testing/development, or automatically during the production build process.

Each secret is hosted securely on Cloud KMS. 

Keys:

* scripts-deploy-key # ciphertext-file=id_fulfilment-platform.enc plaintext-file=id_fulfilment-platform
* supplier-master-sql-credentials # ciphertext-file=credentials.json.enc plaintext-file=credentials.json

# Important Enviorment Variabes
These enviorment variables auto automatically set by our CI/CD systems. However locally they will be need to set manually.

    export <VARIABLE_NAME>=<VARIABLE_VALUE> # Sets variable in envoriemtn
    echo $<VARIABLE_NAME> # Prints variable to test

ENV:
* DB_USER
* DB_PASSWORD
* OKTA_ISSUER
* OKTA_CLIENTID
* OKTA_CLIENTSECRET


## Command to decrypt:
    gcloud kms decrypt --ciphertext-file=[INPUT_FILE] --plaintext-file=[OUTPUT_FILE] --location=global --keyring=fulfilment-platform --key=[SPECIFED_KEY]


# Manually build docker image and upload to registry
    gcloud container builds submit --config cloudbuild.yaml .

