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

* scripts-deploy-key
* supplier-master-sql-credentials

## Command to decrypt:
    gcloud kms decrypt --ciphertext-file=[INPUT_FILE] --plaintext-file=[OUTPUT_FILE] --location=global --keyring=fulfilment-platform --key=[SPECIFED_KEY]


# Manually build docker image and upload to registry
    gcloud container builds submit --config cloudbuild.yaml .