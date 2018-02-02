# InventoryUpdaterWebApp
Web application that will be called to update product inventory from our suppliers. 


# Install gCloud SDK CDK
https://cloud.google.com/sdk/docs/

# Run to auth and set enviorment project
gcloud auth login
gcloud config set project [PROJECT_ID] #fulfillment-platform

# Build docker image and upload to registry
gcloud container builds submit --config cloudbuild.yaml .

# Locally decrypt secret for testing
gcloud kms decrypt --ciphertext-file=id_fulfilment-platform.enc --plaintext-file=id_fulfilment-platform --location=global --keyring=ssh-keys --key=fulfilment-platform