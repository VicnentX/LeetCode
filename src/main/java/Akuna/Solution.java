package Akuna;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Feel free to add any imports you need

public class Solution {
    /**
     * A class which wraps a raw binary WAL and reconstructs DML Events.
     */
    public static class WAL {
        /**
         * Construct the WAL
         * @param input the raw binary WAL
         */
        private ArrayList<String> eventsList;
        private Map<Integer, String> IDToName = new HashMap<Integer, String>(){{
            put(0, "INSERT");
            put(1, "UPSERT");
            put(2, "DELETE");
        }};
        // private Map<String, String> IDToName = new HashMap<String, String>(){{
        //     put("0", "INSERT");
        //     put("1", "UPSERT");
        //     put("2", "DELETE");
        // }};

        public WAL(byte[] input) {
            //
            eventsList = new ArrayList();
            int i = 0;
            final int BYTE_ARRAY_LENGTH = input.length;
            while (i < BYTE_ARRAY_LENGTH) {
                StringBuilder sb = new StringBuilder();

                Long epochMilli = ByteBuffer.wrap(input, i, 8).getLong();
                String epochMilliString = String.valueOf(epochMilli);
                sb.append(epochMilliString).append("|");
                i += 8;

                int id = input[i] & 0xFF;
                String name = IDToName.get(id);
                sb.append(name).append("|");
                i += 1;

                Short keyLength = ByteBuffer.wrap(input, i, 2).getShort();
                // String keyLengthString = String.valueOf(keyLength);
                // System.out.println("keyLengthString" + keyLengthString);
                i += 2;


                //String asciiString = Arrays.toString(ascii);
                Charset characterSet = Charset.forName("US-ASCII");
                String key = new String(input, i, keyLength, characterSet);
                //String key = Arrays.toString(keyByteArray);
                sb.append(key);
                i += keyLength;

                if (name.equals("DELETE")) {
                    //Do nothing
                } else {
                    Short valueLength = ByteBuffer.wrap(input, i, 2).getShort();
                    //String valueLengthString = String.valueOf(valueLength);
                    i += 2;

                    String value = new String(input, i, valueLength, characterSet);
                    //byte[] valueByteArray = ByteBuffer.wrap(input, i, valueLength).array();
                    //String value = new String(valueByteArray, characterSet);
                    //String value = Arrays.toString(valueByteArray);
                    sb.append("|").append(value);
                    i += valueLength;
                }

                eventsList.add(sb.toString());
            }
        }

        /**
         * Retrieve all events contained within the WAL as their string values in time order
         * DML Event String Format: "<epoch_milli>|<message_name>|<key>|<value>"
         *
         * @return a time-ordered sequence of DML Event strings
         */
        public ArrayList<String> getEvents() {
            //
            return eventsList;
        }
    }
}



