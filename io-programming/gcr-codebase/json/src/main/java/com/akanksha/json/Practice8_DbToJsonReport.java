package com.akanksha.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice8_DbToJsonReport {
  public static void main(String[] args) throws Exception {
    List<Map<String, Object>> rows = List.of(
        row(1, "Alice", 30),
        row(2, "Bob", 25),
        row(3, "Charlie", 28));
    ObjectMapper m = new ObjectMapper();
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(rows));
  }

  static Map<String, Object> row(int id, String name, int age) {
    Map<String, Object> r = new HashMap<>();
    r.put("id", id);
    r.put("name", name);
    r.put("age", age);
    return r;
  }
}