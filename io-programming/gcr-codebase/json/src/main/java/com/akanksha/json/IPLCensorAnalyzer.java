package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLCensorAnalyzer {
  public static void main(String[] args) throws Exception {
    if (args.length==0) throw new IllegalArgumentException("Provide input file path (json or csv)");
    File in = new File(args[0]);
    String outJson = args.length>1?args[1]:"censored.json";
    String outCsv = args.length>2?args[2]:"censored.csv";
    ObjectMapper jm = new ObjectMapper();
    ArrayNode records;
    if (in.getName().toLowerCase().endsWith(".csv")) {
      CsvMapper cm = new CsvMapper();
      CsvSchema schema = CsvSchema.builder().setUseHeader(true).build();
      Iterator<JsonNode> it = cm.readerFor(JsonNode.class).with(schema).readValues(in);
      records = jm.createArrayNode();
      while(it.hasNext()) records.add((JsonNode) it.next());
    } else {
      JsonNode root = jm.readTree(in);
      records = (ArrayNode) (root.isArray()?root:jm.createArrayNode().add(root));
    }
    ArrayNode out = jm.createArrayNode();
    for (JsonNode r : records) {
      ObjectNode o = r.deepCopy();
      if (o.has("team1")) o.put("team1", maskTeamName(o.get("team1").asText()));
      if (o.has("team2")) o.put("team2", maskTeamName(o.get("team2").asText()));
      if (o.has("player_of_match")) o.put("player_of_match","REDACTED");
      out.add(o);
    }
    try (FileWriter w = new FileWriter(outJson)) {
      w.write(jm.writerWithDefaultPrettyPrinter().writeValueAsString(out));
    }
    writeCsv(outCsv,out,jm);
    System.out.println("Wrote: " + outJson + " and " + outCsv);
  }

  static String maskTeamName(String team) {
    if (team == null)
      return null;
    String[] p = team.split(" ");
    if (p.length == 1) {
      int keep = Math.max(1, team.length() / 3);
      return team.substring(0, keep) + "***";
    }
    p[p.length - 1] = "***";
    return String.join(" ", p);
  }

  static void writeCsv(String path, ArrayNode rows, ObjectMapper jm) throws Exception {
    if (rows.size() == 0) {
      new File(path).createNewFile();
      return;
    }
    Map<String, Integer> order = new LinkedHashMap<>();
    JsonNode first = rows.get(0);
    first.fieldNames().forEachRemaining(f -> order.put(f, order.size()));
    CsvMapper cm = new CsvMapper();
    CsvSchema.Builder sb = CsvSchema.builder();
    order.keySet().forEach(sb::addColumn);
    CsvSchema schema = sb.build().withHeader();
    cm.writerFor(JsonNode.class).with(schema).writeValues(new File(path))
        .writeAll(StreamSupport.stream(rows.spliterator(), false).map(row -> {
          try {
            return jm.writeValueAsString(row);
          } catch (Exception e) {
            return null;
          }
        }).map(s -> {
          try {
            return cm.readerFor(JsonNode.class).readValue(s);
          } catch (Exception e) {
            return null;
          }
        }).toList());
  }
}