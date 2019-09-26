package Akuna;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * my input is a list of string (string pattern is like time|action|key|(value)).  value could exist or not
 * expected is output is a hash table
 */

public class StreamEventFromBinaryWAL {
    public Map<String, String> buildStreamEventMap(List<String> streamEvents) {
        Map<String, String> hashTable = new HashMap<>();

        for(String event: streamEvents) {
            String[] eventInfo = event.split("\\|");
            if (eventInfo.length == 3 && eventInfo[1].equals("DELETE") && hashTable.containsKey(eventInfo[2])) {
                hashTable.remove(eventInfo[2]);
            }
            else if (eventInfo.length == 4) {
                if (eventInfo[1].equals("INSERT") && !hashTable.containsKey(eventInfo[2]) || eventInfo[1].equals("UPSERT")) {
                    hashTable.put(eventInfo[2], eventInfo[3]);
                }
            }
        }

        return hashTable;
    }

    public static void main(String[] args) {
        StreamEventFromBinaryWAL streamEventFromBinaryWAL = new StreamEventFromBinaryWAL();
        Map<String, String> output= streamEventFromBinaryWAL.buildStreamEventMap(Arrays.asList("001|INSERT|test|123", "002|UPSERT|test|234", "003|DELETE|test"));
        for (String k: output.keySet()) {
            System.out.println(k + "   " + output.get(k));
        }
    }
}
