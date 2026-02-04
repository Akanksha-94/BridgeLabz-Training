package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.util.List;

public class Practice7_CsvToJson {
  public static void main(String[] args) throws Exception {
    File in = args.length > 0 ? new File(args[0]) : new File("input.csv");
    CsvMapper cm = new CsvMapper();
    CsvSchema schema = CsvSchema.builder().setUseHeader(true).build();
    List<JsonNode> nodes = cm.readerFor(JsonNode.class).with(schema).<JsonNode>readValues(in).readAll();
    ObjectMapper m = new ObjectMapper();
    System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(nodes));
  }
}