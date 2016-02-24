package com.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Sorts.ascending;

public class RemoveLowest {
    public static void main(final String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase numbersDB = client.getDatabase("school");
        MongoCollection<Document> students = numbersDB.getCollection("students");

      /*  MongoCursor<Document> cursor = students.find(eq("type", "homework"))
                                .sort(ascending("student_id", "score")).iterator();*/
        
        MongoCursor<Document> cursor = students.find().iterator();

        Object studentId = -1;
        try {
            while (cursor.hasNext()) {
                Document entry = cursor.next();
                List<Document> scores = (List<Document>) entry.get("scores");
                Double lowest = null;
                for (Document i : scores){
                	if (!i.get("type").equals("homework")) continue;
                	//System.out.println(i.get("type"));
                	//System.out.println(i.get("score"));
                	if (lowest==null) {
                		lowest = i.getDouble("score");
                	} else {
                		if (lowest.compareTo(i.getDouble("score")) > 0) {
                			lowest = i.getDouble("score");
                  		}
                	}
                }
                List<Document> scores1 = new ArrayList<Document>();
                
                for (Document i : scores){
                	if (i.get("type").equals("homework") && i.getDouble("score").equals(lowest)) {
                		//scores.remove(i);
                	} else {
                		scores1.add(i);
                	}
                }
                students.updateOne(eq("_id", entry.get("_id")), new Document("$set", new Document("scores", scores1)));
           }
        } finally {
            cursor.close();
        }
    }
}
