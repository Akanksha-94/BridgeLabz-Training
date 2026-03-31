package com.akanksha.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.io.File;
import java.util.Set;

public class ValidateJsonStructure {
  public static void main(String[] args) throws Exception {
    File schemaFile = new File(args[0]);
    File jsonFile = new File(args[1]);
    ObjectMapper m = new ObjectMapper();
    JsonNode schemaNode = m.readTree(schemaFile);
    JsonNode jsonNode = m.readTree(jsonFile);
    JsonSchemaFactory f = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
    JsonSchema schema = f.getSchema(schemaNode);
    Set<ValidationMessage> msgs = schema.validate(jsonNode);
    if (msgs.isEmpty())
      System.out.println("VALID");
    else
      msgs.forEach(System.out::println);
  }
}