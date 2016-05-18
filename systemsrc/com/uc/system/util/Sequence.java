package com.uc.system.util;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Sequence
{
    private static final String IP = "127.0.0.1";
    
    private static final String DBNAME = "uc";
    
    private static final String COLLECTION = "seq";
    
    public Sequence()
        throws IOException
    {
    }
    
    public static String getNextId(String seq_name)
    {
        Mongo mongo = null;
        DBCollection seq = null;
        try
        {
            mongo = new Mongo(IP, 27017);
        }
        catch (MongoException e)
        {
            e.printStackTrace();
        }
        seq = mongo.getDB(DBNAME).getCollection(COLLECTION);
        String sequence_field = "seq";
        DBObject query = new BasicDBObject();
        query.put("_id", seq_name);
        DBObject change = new BasicDBObject(sequence_field, 1);
        DBObject update = new BasicDBObject("$inc", change);
        DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
        mongo.close();
        return res.get(sequence_field).toString();
    }
    
    public static String setSeqValue(String seq_name, int value)
    {
        Mongo mongo = null;
        DBCollection seqColletion = null;
        try
        {
            mongo = new Mongo(IP, 27017);
        }
        catch (MongoException e)
        {
            e.printStackTrace();
        }
        seqColletion = mongo.getDB(DBNAME).getCollection(COLLECTION);
        String sequence_field = "seq";
        DBObject query = new BasicDBObject();
        query.put("_id", seq_name);
        DBObject update = new BasicDBObject(sequence_field, value);
        DBObject res =
            seqColletion.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
        mongo.close();
        return res.get(sequence_field).toString();
    }
}
