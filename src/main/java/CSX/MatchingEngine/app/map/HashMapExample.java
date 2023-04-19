package CSX.MatchingEngine.app.map;

import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        // Adding key-value pairs to the map
        map.put("name", "John");
        map.put("age", "30");
        map.put("city", "New York");

        // Accessing values using keys
        String name = map.get("name");
        String age = map.get("age");
        String city = map.get("city");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}

