from flask import Flask, request, jsonify

app = Flask(__name__)

items = []
next_id = 1


@app.route("/")
def home():
    return jsonify({"message": "Welcome to FindIt API"})


@app.route("/health")
def health():
    return jsonify({"status": "healthy"})

@app.route("/items/count")
def item_count():
    return jsonify({"total_items": len(items)})

@app.route("/items", methods=["POST"])
def add_item():
    global next_id

    data = request.get_json()

    if not data or not data.get("name"):
        return jsonify({"error": "Item name is required"}), 400

    item = {
        "id": next_id,
        "name": data["name"],
        "description": data.get("description", ""),
        "type": data.get("type", "lost"),
        "claimed": False
    }

    items.append(item)
    next_id += 1

    return jsonify(item), 201


@app.route("/items", methods=["GET"])
def get_items():
    return jsonify(items)


@app.route("/items/search", methods=["GET"])
def search_item():
    keyword = request.args.get("name", "").lower()

    result = [
        item for item in items
        if keyword in item["name"].lower()
    ]

    return jsonify(result)


@app.route("/items/<int:item_id>/claim", methods=["PUT"])
def claim_item(item_id):
    for item in items:
        if item["id"] == item_id:
            item["claimed"] = True
            return jsonify(item)

    return jsonify({"error": "Item not found"}), 404


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)