package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;

public class FilterByAge {
  public static void main(String[] args) throws Exception {
    File in = args.length > 0 ? new File(args[0]) : new File("users.json");
    ObjectMapper m = new ObjectMapper();
    JsonNode root = m.readTree(in);
    ArrayNode out = m.createArrayNode();
    if (root.isArray())
      for (JsonNode n : root)
        if (n.has("age") && n.get("age").asInt() > 25)
          out.add(n);
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(out));
  }
}