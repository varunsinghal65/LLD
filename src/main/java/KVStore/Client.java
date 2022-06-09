 package KVStore;

import KVStore.exceptions.DataTypeError;
import KVStore.services.StoreServiceImpl;

import java.io.InputStreamReader;
import java.util.*;

//https://workat.tech/machine-coding/practice/design-key-value-store-6gz6cq124k65
public class Client {

    public static void main(String[] args) {
        UcManager ucManager = new UcManager(new StoreServiceImpl());
        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String[] cmdArgs = scanner.nextLine().split(" ");
            String action = cmdArgs[0].toLowerCase();
            switch (action) {
                case "get":
                    Set<Map.Entry<String, String>> entrySet = ucManager.getAttributePairsByKey(cmdArgs[1]).entrySet();
                    entrySet.forEach((entry) -> System.out.print(entry.getKey() + ": " + entry.getValue() + ", "));
                    break;
                case "put":
                    HashMap<String, String> attributePairs = new HashMap<>();
                    for (int i = 2; i < cmdArgs.length; i = i + 2) {
                        //strings at even indices are key
                        attributePairs.put(cmdArgs[i], cmdArgs[i + 1]);
                    }
                    try {
                        ucManager.put(cmdArgs[1], attributePairs);
                    } catch (DataTypeError e) {
                        System.out.print("Data Type Error");
                    }
                    break;
                case "delete":
                    ucManager.delete(cmdArgs[1]);
                    break;
                case "search":
                    ucManager.getSortedKeysContainingAttributePair(cmdArgs[1], cmdArgs[2])
                            .forEach(key -> System.out.println(key + ","));
                    break;
                case "keys":
                    ucManager.getSortedKeys().forEach(key -> System.out.println(key + ","));
                    break;
                case "exit":
                    System.exit(0);
                default:
            }
        }
    }

}
