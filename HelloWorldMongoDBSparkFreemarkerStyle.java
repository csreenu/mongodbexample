package com.mongodb;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreemarkerStyle {
	public static void main(String[]args){
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");
		
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("test");
		
		final MongoCollection<Document> coll = db.getCollection("hello");
		
		coll.insertOne(new Document("name", "MongoDB"));
		
		try {
			Template helloTemplate = configuration.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name","freemarker");
			
			Document doc = coll.find().first();
			helloTemplate.process(doc,writer);
			//System.out.println(writer);
			spark.Spark.get("/hello", (req, res) -> writer);
			
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
