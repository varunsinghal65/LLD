package KVStore;

import KVStore.exceptions.DataTypeError;
import KVStore.models.AttributeValue;
import KVStore.models.Value;
import KVStore.services.IStoreService;

import java.util.*;
import java.util.function.Predicate;

public class UcManager {

    private IStoreService storeService;
    private final Map<String, Value> store;

    UcManager(IStoreService service) {
        this.storeService = service;
        this.store = new HashMap<>();
    }


    public Map<String, String> getAttributePairsByKey(String key) {
        synchronized (store) {
            return storeService.getAttributePairsByKey(key, store);
        }
    }

    public void put(String key, HashMap<String, String> attributePairs) throws DataTypeError {
        synchronized (store) {
            storeService.putInStore(key, attributePairs, store);
        }
    }

    public void delete(String key) {
        synchronized (store) {
            storeService.deleteFromStore(key, store);
        }
    }

    public List<String> getSortedKeysContainingAttributePair(String attribKey, String attribValue) {
        synchronized (store) {
            Predicate<Map.Entry<String, Value>> containsPairPredicate = (keyValue) ->
                    keyValue.getValue().getAttributePairs().containsKey(attribKey) &&
                            keyValue.getValue().getAttributePairs().get(attribKey).getVal().equals(attribValue);
            List<String> keys = storeService.getKeysBasedOnPredicate(containsPairPredicate, store);
            Collections.sort(keys);
            return keys;
        }
    }

    public Set<String> getSortedKeys() {
        synchronized (store) {
            TreeSet<String> toReturn = new TreeSet<>(store.keySet());
            return Collections.unmodifiableSet(toReturn);
        }
    }
}
