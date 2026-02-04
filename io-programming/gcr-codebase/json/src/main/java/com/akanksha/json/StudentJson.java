package com.akanksha.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StudentJson {
  public static void main(String[] args) throws Exception {
    ObjectMapper m = new ObjectMapper();
    ObjectNode student = m.createObjectNode();
    student.put("name", "Alice");
    student.put("age", 20);
    ArrayNode subjects = m.createArrayNode();
    subjects.add("Math").add("Physics").add("Chemistry");
    student.set("subjects", subjects);
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(student));
  }
}