package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;

public class ExtractFields {
  public static void main(String[] args) throws Exception {
    File f = args.length > 0 ? new File(args[0]) : new File("input.json");
    String[] fields = args.length > 1 ? args[1].split(",") : new String[] { "name", "email" };
    ObjectMapper m = new ObjectMapper();
    JsonNode root = m.readTree(f);
    if (root.isArray()) {
      ArrayNode out = m.createArrayNode();
      for (JsonNode n : root) {
        ObjectNode obj = m.createObjectNode();
        for (String fld : fields)
          if (n.has(fld))
            obj.set(fld, n.get(fld));
        out.add(obj);
      }
      System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(out));
    } else {
      ObjectNode obj = m.createObjectNode();
      for (String fld : fields)
        if (root.has(fld))
          obj.set(fld, root.get(fld));
      System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    }
  }
}