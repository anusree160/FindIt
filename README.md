# FindIt - Lost and Found Management System

## Overview
FindIt is a Lost and Found Management System developed to help users report, search, and manage lost and found items. The project demonstrates DevOps practices through two implementations: a Java Spring Boot application for Continuous Integration and a Python Flask REST API for Configuration Management and Kubernetes Deployment.

## Features
- Add a lost or found item.
- View all reported items.
- Search for items by name.
- Claim an item.
- Check the total number of reported items.
- Check application health through a health-check endpoint.

## Technologies Used

### Component 1 – Continuous Integration
- Java and Spring Boot
- Git and GitHub
- Maven
- Jenkins

### Component 2 – Configuration Management and Deployment
- Python and Flask
- Ansible
- Docker
- Kubernetes
- Minikube
- kubectl

## API Endpoints

The following endpoints are implemented in the Python Flask version of FindIt.

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Returns a welcome message |
| GET | `/health` | Checks application health |
| GET | `/items` | Retrieves all reported items |
| POST | `/items` | Adds a lost or found item |
| GET | `/items/search?name=Wallet` | Searches for items by name |
| GET | `/items/count` | Returns the total number of items |
| PUT | `/items/1/claim` | Marks an item as claimed |

## Run Locally – Python Flask Version

Install the required dependencies:

```bash
pip install -r requirements.txt
```

Run the application:

```bash
python app.py
```

The application runs locally at:

```text
http://127.0.0.1:5000
```

## DevOps Implementation

- **Continuous Integration:** Git and GitHub were used for version control and source-code management. Maven was used for building the Java application, while Jenkins automated the build process.
- **Configuration Management:** Ansible was used to automate application configuration and deployment tasks through a custom role.
- **Containerization:** Docker was used to package the Python Flask application and its dependencies into an image named `findit:1.0`.
- **Container Orchestration:** Kubernetes with Minikube was used to deploy the application, manage replicas, expose the service, and demonstrate horizontal scaling.
- **Health Monitoring:** Kubernetes liveness and readiness probes were configured using the `/health` endpoint.
