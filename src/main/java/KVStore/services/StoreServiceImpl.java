package KVStore.services;

import KVStore.exceptions.DataTypeError;
import KVStore.models.AttributeValue;
import KVStore.models.Value;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class StoreServiceImpl implements IStoreService {
    @Override
    public Map<String, String> getAttributePairsByKey(String key, Map<String, Value> store) {
        if (store.containsKey(key)) {
            Map<String, AttributeValue> attributePairs = store.get(key).getAttributePairs();
            HashMap<String, String> toReturn = new HashMap<>();
            attributePairs.keySet().forEach(attributeKey -> toReturn.put(attributeKey,
                    attributePairs.get(attributeKey).getVal()));
            return toReturn;
        }
        return new HashMap<>();
    }

    @Override
    public void putInStore(String key, Map<String, String> ipAttributePairs, Map<String, Value> store) throws DataTypeError {
        if (store.containsKey(key)) {
            //delta update
            Map<String, AttributeValue> existingAttributePairs = store.get(key).getAttributePairs();
            Map<String, AttributeValue> newAttributePairs = new HashMap<>();
            //for each new pair, check if it exists and type is different
            for (Map.Entry<String, String> ipAttribPair : ipAttributePairs.entrySet()) {
                if (existingAttributePairs.containsKey(ipAttribPair.getKey()) &&
                        !new AttributeValue(ipAttribPair.getValue()).getType()
                                .equals(existingAttributePairs.get(ipAttribPair.getKey()).getType())) {
                    throw new DataTypeError();
                }
                newAttributePairs.put(ipAttribPair.getKey(), new AttributeValue(ipAttribPair.getValue()));
            }
            //now iterate all the existing pairs, and add those pairs that were not added in the previous code block.
            for (Map.Entry<String, AttributeValue> existingPair : existingAttributePairs.entrySet()) {
                if (!newAttributePairs.containsKey(existingPair.getKey())) {
                    newAttributePairs.put(existingPair.getKey(), existingPair.getValue());
                }
            }
            Value existingValue = store.get(key);
            existingValue.setAttributePairs(newAttributePairs);
        } else {
            HashMap<String, AttributeValue> pairsToSet = new HashMap<>();
            ipAttributePairs.entrySet().forEach((pair) -> pairsToSet.put(pair.getKey(),
                    new AttributeValue(pair.getValue())));
            Value value = new Value();
            value.setAttributePairs(pairsToSet);
            store.put(key, value);
        }
    }

    @Override
    public void deleteFromStore(String key, Map<String, Value> store) {
        store.remove(key);
    }

    @Override
    public List<String> getKeysBasedOnPredicate(Predicate<Map.Entry<String, Value>> containsPairPredicate,
                                                Map<String, Value> store) {
        List<String> toReturn = new ArrayList<>();
        store.entrySet().stream().filter(containsPairPredicate).forEach(entry->toReturn.add(entry.getKey()));
        return toReturn;
    }
}
