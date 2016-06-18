package com.uc.system.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Component
public class Sequence {
	private static final String COLLECTION = "seq";
	private static final String SEQUENCEFIELD = "seq";
	@Value(value = "${mongo.Local.IP}")
	private String IP;
	@Value(value = "${mongo.Local.DBName}")
	private String DBNAME;
	@Value(value = "${mongo.Local.port}")
	private int port;

	public String getNextId(String seq_name) {
		Mongo mongo = null;
		DBCollection seq = null;
		try {
			mongo = new Mongo(IP, port);
		} catch (MongoException e) {
			e.printStackTrace();
		}

		int maxid = getMaxId(mongo, seq_name) + 1;

		setSeqValue(seq_name, maxid);

		seq = mongo.getDB(DBNAME).getCollection(COLLECTION);

		DBObject res = seq.findOne(new BasicDBObject("_id", seq_name));

		mongo.close();

		return res.get(SEQUENCEFIELD).toString();
	}

	private int getMaxId(Mongo mongo, String seq_name) {
		try {
			DBCollection coll = mongo.getDB(DBNAME).getCollection(seq_name);
			DBObject maxID = coll
					.find(new BasicDBObject(), new BasicDBObject("_id", 1))
					.sort(new BasicDBObject("_id", -1)).limit(1).toArray()
					.get(0);
			System.out.println("当前最大ID：" + maxID.get("_id"));
			return Integer.valueOf(maxID.get("_id").toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("获取seq出错：name--" + seq_name);
			return 0;
		}
	}

	public String setSeqValue(String seq_name, int value) {
		Mongo mongo = null;
		DBCollection seqColletion = null;
		try {
			mongo = new Mongo(IP, port);
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
