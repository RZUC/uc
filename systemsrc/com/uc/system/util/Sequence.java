package com.uc.system.util;

import java.io.IOException;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Sequence {
	private static final String IP = "127.0.0.1";

	private static final String DBNAME = "uc";

	private static final String COLLECTION = "seq";

	private static final int port = 27001;

	private static final String SEQUENCEFIELD = "seq";

	public Sequence() throws IOException {
	}

	public static String getNextId(String seq_name) {
		Mongo mongo = null;
		DBCollection seq = null;
		try {
			mongo = new Mongo(IP, port);
		} catch (MongoException e) {
			e.printStackTrace();
		}

		int maxid = getMaxId(mongo, seq_name) + 1;
		// 设置新的值
		setSeqValue(seq_name, maxid);
		seq = mongo.getDB(DBNAME).getCollection(COLLECTION);

		// DBObject query = new BasicDBObject();
		// query.put("_id", seq_name);
		// DBObject change = new BasicDBObject(sequence_field, 1);
		// DBObject update = new BasicDBObject("$inc", change);
		// DBObject res = seq.findAndModify(query, new BasicDBObject(), new
		// BasicDBObject(), false, update, true, true);
		DBObject res = seq.findOne(new BasicDBObject("_id", seq_name));
		mongo.close();
		return res.get(SEQUENCEFIELD).toString();
	}

	private static int getMaxId(Mongo mongo, String seq_name) {
		try {
			DBCollection coll = mongo.getDB(DBNAME).getCollection(seq_name);
			DBObject maxID = coll
					.find(new BasicDBObject(), new BasicDBObject("_id", 1))
					.sort(new BasicDBObject("_id", -1)).limit(1).toArray().get(0);
			System.out.println("当前最大ID："+maxID.get("_id"));
			return Integer.valueOf(maxID.get("_id").toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("获取seq出错：name--" + seq_name);
			return 0;
		}
	}

	@Test
	public void test() {
		Mongo mongo = new Mongo();
		DBCollection coll = mongo.getDB(DBNAME).getCollection("policyInfo");
		DBObject maxID = coll
				.find(new BasicDBObject(), new BasicDBObject("_id", 1))
				.sort(new BasicDBObject("_id", -1)).toArray().get(0);
		System.out.println(maxID);
	}

	public static String setSeqValue(String seq_name, int value) {
		Mongo mongo = null;
		DBCollection seqColletion = null;
		try {
			mongo = new Mongo(IP, 27017);
		} catch (MongoException e) {
			e.printStackTrace();
		}
		seqColletion = mongo.getDB(DBNAME).getCollection(COLLECTION);
		String sequence_field = "seq";
		DBObject query = new BasicDBObject();
		query.put("_id", seq_name);
		DBObject update = new BasicDBObject(sequence_field, value);
		DBObject res = seqColletion.findAndModify(query, new BasicDBObject(),
				new BasicDBObject(), false, update, true, true);
		mongo.close();
		return res.get(sequence_field).toString();
	}
}
