package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.util.Set;

public class Practice4_EmailJsonSchemaValidator {
  public static void main(String[] args) throws Exception {
    String schemaStr = "{" +
        "\"$schema\":\"http://json-schema.org/draft-07/schema#\"," +
        "\"type\":\"object\"," +
        "\"properties\":{\"email\":{\"type\":\"string\",\"format\":\"email\"}},\"required\":[\"email\"]}";
    ObjectMapper m = new ObjectMapper();
    JsonNode schemaNode = m.readTree(schemaStr);
    JsonNode json = m.readTree(args.length > 0 ? args[0] : "{\"email\":\"user@example.com\"}");
    JsonSchemaFactory f = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
    JsonSchema schema = f.getSchema(schemaNode);
    Set<ValidationMessage> msgs = schema.validate(json);
    if (msgs.isEmpty())
      System.out.println("EMAIL VALID");
    else
      msgs.forEach(System.out::println);
  }
}