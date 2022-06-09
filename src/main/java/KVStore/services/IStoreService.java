package KVStore.services;

import KVStore.exceptions.DataTypeError;
import KVStore.models.Value;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface IStoreService {

    Map<String, String> getAttributePairsByKey(String key, Map<String, Value> store);

    void putInStore(String key, Map<String, String> attributePairs, Map<String, Value> store) throws DataTypeError;

    void deleteFromStore(String key, Map<String, Value> store);

    List<String> getKeysBasedOnPredicate(Predicate<Map.Entry<String, Value>> containsPairPredicate, Map<String, Value> store);
}
