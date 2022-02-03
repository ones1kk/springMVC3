package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    // multi thread 환경을 고려하여 HashMap 사용은 지양한다.
    private static final Map<Long, Item> store = new HashMap<>(); // static

    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Item updatedItem) {
        Item item = findById(id);
        item.setItemName(updatedItem.getItemName());
        item.setPrice(updatedItem.getPrice());
        item.setQuantity(updatedItem.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
