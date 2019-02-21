package leetcode.JSON;

import org.json.*;

public class App {
    public String getGreeting() {
        return "Hello world, hello Gradle!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        JSONArray ja = new JSONArray();
        for (int i = 0; i < 6; ++i){
            JSONObject jo = new JSONObject();
            jo.put("hello gradle!",i);
            ja.put(jo);
        }
        System.out.println(ja.toString());
    }
}
