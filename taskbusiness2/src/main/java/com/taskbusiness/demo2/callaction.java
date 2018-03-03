package com.taskbusiness.demo2;

import java.util.Calendar;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class callaction {

	public String Test() {
		Calendar c = Calendar.getInstance();
		String result = "com.task.demo2.Test date:" + c.getTime();
		System.out.println(result);
		return result;
	}

	public String Test2() {
		Calendar c = Calendar.getInstance();
		String result = "com.task.demo2.Test2 date:" + c.getTime();
		System.out.println(result);
		return result;
	}

	public String TestMongodb() {
		Calendar c = Calendar.getInstance();
		String result = "com.task.demo2.TestMongodb date:" + c.getTime();
		System.out.println(result);
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("172.0.0.1", 27017);
			MongoDatabase mongoDatabase = mongoClient.getDatabase("testlu");
			MongoCollection mongoCollection = mongoDatabase.getCollection("testmongodb");
			Document doc = new Document();
			doc.append("msg", result);
			doc.append("ectime", Calendar.getInstance().getTime());
			mongoCollection.insertOne(doc);
			System.out.println("insert doc testlu.testmongodb");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return result;
	}
}