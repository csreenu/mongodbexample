package com.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class FindOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("enron");
		MongoCollection<Document> collection = db.getCollection("images");
		MongoCollection<Document> collection1 = db.getCollection("albums");
		
		
		//Document doc = collection.find().first();
		//System.out.println(doc.toJson().toString());
		
		
		
		//List<Document> list = collection.find(filter).into(new ArrayList<Document>());
		
		//for (Document cur : list){
			//System.out.println(cur.toJson().toString());
		//}
		List<Integer> idsToDelete = new ArrayList<Integer>();
		MongoCursor<Document> cur = collection.find().iterator();
		try { 
			while(cur.hasNext()){
				Document d =  cur.next();
				//System.out.println(d.toJson().toString());
				Bson filter = Filters.eq("images", d.get("_id"));
				Document doc = collection1.find(filter).first();
				if (doc == null) {
					System.out.println("BAdding"+ d.get("_id"));
					idsToDelete.add(d.getInteger("_id"));
					collection.deleteOne(d);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}finally{
			cur.close();
		}
		
		
		
	}

}
