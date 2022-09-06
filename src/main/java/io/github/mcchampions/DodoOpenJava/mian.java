package io.github.mcchampions.DodoOpenJava;

import org.json.JSONObject;

public class mian {
    public static void main(String[] args) {
        JSONObject n = new JSONObject("{\"a\": \"b\"}");
        System.out.println(n);
        n.put("a","C");
        System.out.println(n);
    }
}
