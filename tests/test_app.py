import app as app_module


def setup_function():
    app_module.items.clear()
    app_module.next_id = 1


def test_home():
    client = app_module.app.test_client()
    response = client.get("/")
    assert response.status_code == 200


def test_health():
    client = app_module.app.test_client()
    response = client.get("/health")
    assert response.json["status"] == "healthy"


def test_add_item():
    client = app_module.app.test_client()
    response = client.post("/items", json={
        "name": "Wallet",
        "description": "Black wallet",
        "type": "lost"
    })
    assert response.status_code == 201


def test_get_items():
    client = app_module.app.test_client()
    client.post("/items", json={"name": "Wallet"})
    response = client.get("/items")
    assert len(response.json) == 1


def test_search_item():
    client = app_module.app.test_client()
    client.post("/items", json={"name": "Wallet"})
    response = client.get("/items/search?name=Wallet")
    assert len(response.json) == 1


def test_claim_item():
    client = app_module.app.test_client()
    client.post("/items", json={"name": "Wallet"})
    response = client.put("/items/1/claim")
    assert response.json["claimed"] is True