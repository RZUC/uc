package com.uc.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * CSV文件写入
 *
 */
public class CSV {
	public static void writeCsvToDB(File file) {

	}

	public static void readCsv(File file) throws IOException {
		try {
			Mongo m = new Mongo("127.0.0.1", 27017);
			DBCollection coll = m.getDB("uc").getCollection(
					file.getName().split("\\.")[0]);
			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
			CsvReader reader = new CsvReader(new FileInputStream(file), ',',
					Charset.forName("gbk")); // 一般用这编码读就可以了
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
			String[] head = reader.getHeaders(); // 获取表头
			DBObject ob = null;
			while (reader.readRecord()) {
				ob = new BasicDBObject();
				for (int i = 0; i < head.length; i++) {
					String value = reader.get(head[i]);
					System.out.print(head[i] + ":" + value);
					if (head[i].equals("_id")) {
						ob.put(head[i], Integer.valueOf(value));
					} else {
						if (null == value || "".equals(value)) {
							ob.put(head[i], "");
						} else {
							try {

								ob.put(head[i], Integer.valueOf(value));
							} catch (Exception e) {
								ob.put(head[i], value);
							}

						}
					}

				}
				coll.save(ob);
				System.out.println();
				System.out.println("----------------");
			}
			m.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File(System.getProperty("user.dir")
					+ File.separatorChar + "testData");
			File[] list = file.listFiles();
			for (File f : list) {
//				if (f.getName().contains("industry.csv")) {
//				}
				readCsv(f);
			}
			//
			// readCsv(System.getProperty("user.dir")+File.separatorChar+"test"+File.separatorChar+"areas.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
