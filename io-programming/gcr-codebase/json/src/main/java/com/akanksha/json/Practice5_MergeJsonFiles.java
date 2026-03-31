package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;

public class Practice5_MergeJsonFiles {
  public static void main(String[] args) throws Exception {
    File a = new File(args[0]);
    File b = new File(args[1]);
    ObjectMapper m = new ObjectMapper();
    ObjectNode na = (ObjectNode) m.readTree(a);
    ObjectNode nb = (ObjectNode) m.readTree(b);
    na.setAll(nb);
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(na));
  }
}