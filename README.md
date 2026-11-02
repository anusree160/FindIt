# FindIt - Lost and Found Management API

## Overview
FindIt is a REST API that helps users manage lost and found items.
It is developed using Python and Flask.

## Features
- Add a lost or found item.
- View all reported items.
- Search for items by name.
- Claim an item.
- Check the total number of items.
- Health-check endpoint.

## Technologies Used
- Python
- Flask
- Git and GitHub
- Jenkins
- Ansible
- Docker
- Kubernetes

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | / | Welcome message |
| GET | /health | Health status |
| GET | /items | View all items |
| POST | /items | Add an item |
| GET | /items/search?name=Wallet | Search items |
| GET | /items/count | Count items |
| PUT | /items/1/claim | Claim an item |

## Run Locally

```bash
pip install -r requirements.txt
python app.py