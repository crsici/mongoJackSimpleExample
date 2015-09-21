package com.sic.org.console;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MongoClient client = new MongoClient("localhost");
		DB db = client.getDB("console");
		DBCollection dbCollection = db.getCollection("myobjects");

		MongoDatabase database = client.getDatabase("console");

		MongoCollection<DBObject> mongoCollection = database.getCollection("console", DBObject.class);

		JacksonDBCollection<BlogPost, String> coll = JacksonDBCollection.wrap(dbCollection, BlogPost.class,
				String.class);
		BlogPost blogPost = new BlogPost();
		blogPost.set("test", 1);
		coll.insert(blogPost);

		
		dbCollection.find().iterator();
		
		//mongoCollection.insertOne(blogPost);
		
		
		DBCursor<BlogPost> cursor = coll.find();
		while (cursor.hasNext()) {
			BlogPost blogPost2 = cursor.next();
			System.out.println(mapper.writeValueAsString(blogPost2));
		}

	}
}
