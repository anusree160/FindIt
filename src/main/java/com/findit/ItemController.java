
package com.findit;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final List<Map<String, Object>> items = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @PostMapping
    public Map<String, Object> addItem(@RequestBody Map<String, String> request) {

        Map<String, Object> item = new java.util.HashMap<>();
        item.put("id", idCounter.getAndIncrement());
        item.put("name", request.get("name"));
        item.put("description", request.get("description"));
        item.put("type", request.get("type"));
        item.put("claimed", false);

        items.add(item);

        return item;
    }

    @GetMapping
    public List<Map<String, Object>> getItems() {
        return items;
    }

    @GetMapping("/search")
    public List<Map<String, Object>> searchItems(@RequestParam String name) {

        return items.stream()
                .filter(item -> item.get("name").toString()
                        .toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @PostMapping("/{id}/claim")
    public Map<String, Object> claimItem(@PathVariable int id) {

        for (Map<String, Object> item : items) {
            if ((int) item.get("id") == id) {
                item.put("claimed", true);
                return item;
            }
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Item not found");
    }
}
