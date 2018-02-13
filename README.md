# InventoryUpdaterWebApp
Web application that will be called to update product inventory from our suppliers. 

# Install gCloud SDK CDK
https://cloud.google.com/sdk/docs/

# Run to auth and set enviorment project
gcloud auth login
gcloud config set project zip-zap-gateway

# Build docker image and upload to registry
gcloud container builds submit --config cloudbuild.yaml .

# Locally decrypt secret for testing
gcloud kms decrypt --plaintext-file id_fulfilment-platform --ciphertext-file=id_fulfilment-platform.enc --location=global --keyring=fulfilment-platform --key=scripts-deploy-key

id_fulfilment-platform (DO NOT COMMIT)
fulfilment-platform.enc (safe)
id_fulfilment-platform.pub (safe)