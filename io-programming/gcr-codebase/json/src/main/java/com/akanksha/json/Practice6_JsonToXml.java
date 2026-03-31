package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;

public class Practice6_JsonToXml {
  public static void main(String[] args) throws Exception {
    File in = args.length > 0 ? new File(args[0]) : new File("input.json");
    ObjectMapper jm = new ObjectMapper();
    JsonNode n = jm.readTree(in);
    XmlMapper xm = new XmlMapper();
    System.out.println(xm.writerWithDefaultPrettyPrinter().writeValueAsString(n));
  }
}