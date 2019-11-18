# Getting Started
This project builds a simple Spring Boot web app and builds a Docker container from it. The application has two endpoints; one that simply generates a message and the other that reads the message from a file.

Build the project (and the dockerfile) using

```bash
./mvnw clean package
```

## Pushing to Docker Hub

To use [Docker Hub](https://hub.docker.com) as a repo add the following section to your Maven settings.xml (or better yet: use the [encryption features of Maven](https://maven.apache.org/guides/mini/guide-encryption.html) and not store them plaintext)

```xml
<servers>
    <server>
      <id>docker.io</id>
      <username>youruserid</username>
      <password>yourpassword</password>
    </server>
  </servers>
```

In the `pom.xml` file replace the `docker.image.prefix` property with `youruserid`. To push the image to Docker Hub use
```bash
./mvnw dockerfile:push
``` 

## Setup storage class and persistent volume claim on Azure
Setup the `StorageClass` and `PersistentVolumeClaim` using the following commands:

```bash
kubectl apply -f azure-file-sc.yml
kubectl apply -f azure-file-pvc.yml
```

Note: Depending on your Azure setup and k8s provider you may have to configure a storage account and/or a cluster role and binding. See also [https://docs.microsoft.com/en-us/azure/aks/azure-files-dynamic-pv](https://docs.microsoft.com/en-us/azure/aks/azure-files-dynamic-pv)

## Deploying to k8s

Select a k8s provider of your choice (e.g. PKS/AKS/GKE/etc.) and create a cluster. Then deploy the image as pods using

```bash
kubectl apply -f k8s-deployment.yml
```

and expose them as a service through a load balancer with a randomly assigned ephemeral IP using

```bash
kubectl apply -f k8s-service.yml
```

If you want to expose the service through a load balancer with a pre-assigned static IP address, use

```bash
kubectl apply -f k8s-service-static-ip.yml
```

Note that you must reserve the static IP address at your IAAS of choice before exposing it with a static IP.