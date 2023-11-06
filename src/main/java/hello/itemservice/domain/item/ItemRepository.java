package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ItemRepository
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
@Repository
public class ItemRepository {
    // 참고, 실무에서는 HashMap 사용하면 안됨, 싱글톤으로 생성되기 때문에 동시에 여러 스레드가 접근할 수 있어서 문제 발생 ConcurrentHashMap 사용 해야 함
    private static final Map<Long, Item> store = new HashMap<>(); // static
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // ArrayList 감싸서 보내면 해당 리스트에 처리를 해도 store에 반영되지 않아 안전
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
