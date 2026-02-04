package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;

public class Practice1_ReadAllKeys {
  public static void main(String[] args) throws Exception {
    File in = args.length > 0 ? new File(args[0]) : new File("input.json");
    ObjectMapper m = new ObjectMapper();
    JsonNode n = m.readTree(in);
    printNode(n, "");
  }

  static void printNode(JsonNode n, String prefix) {
    if (n.isObject()) {
      Iterator<String> it = n.fieldNames();
      while (it.hasNext()) {
        String f = it.next();
        System.out.println(prefix + f + ": " + n.get(f));
        printNode(n.get(f), prefix + f + ".");
      }
    } else if (n.isArray())
      for (JsonNode e : n)
        printNode(e, prefix + "[]");
  }
}