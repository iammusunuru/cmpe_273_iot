package ass2_server;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.BasicBSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class dblayer {
	private MongoClient conn;
	private DB db;
	DBCollection coll;
	public dblayer(String uname, String pass)
	{
		try {
			this.conn = new MongoClient( "localhost" , 27017 );
			this.db = this.conn.getDB("bootstrap");
		//	boolean auth = db.authenticate(uname, pass.toCharArray());
			this.coll = db.getCollection("params");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert_data(injecter i)
	{
		BasicDBObject document = new BasicDBObject();
		document.put("endpoint_name", i.getEndpoint_name());
		BasicDBObject bootydocument = new BasicDBObject();
		bootydocument.put("shortserverid","1001");
		bootydocument.put("Reg_server_add",i.getBooty().getReg_server_add());
		bootydocument.put("manufacturer",i.getBooty().getManufacturer());
		bootydocument.put("model_no",i.getBooty().getModel_no());
		bootydocument.put("serial_no",i.getBooty().getSerial_no());
		bootydocument.put("frimware_no",i.getBooty().getFrimware_no());
		bootydocument.put("power_source",i.getBooty().getPower_source());
		bootydocument.put("power_source_voltage",i.getBooty().getPower_source_voltage());
		bootydocument.put("battery_level",i.getBooty().getBattery_level());
		bootydocument.put("server_key",i.getBooty().getServer_key());
		bootydocument.put("hold_time",i.getBooty().getHold_time());
		bootydocument.put("current_time",i.getBooty().getCurrent_time());
		bootydocument.put("utc_offset",i.getBooty().getUtc_offset());
		document.put("boot_info", bootydocument);	
		this.coll.insert(document);
		return 1;
		
	}
	
	public Bootstrap_params fetch_data(String id)
	{
		BasicDBObject doc1 = new BasicDBObject("endpoint_name",id);
		DBCursor cursor = this.coll.find(doc1);
		DBObject ret=null;
		try {
		    while (cursor.hasNext()) {
		        ret = (cursor.next());
		    }
		} finally {
		    cursor.close();
		}
		injecter i = new injecter();
		i.setEndpoint_name((String) ret.get("endpoint_name"));
		Bootstrap_params b = new Bootstrap_params();
		b.setBattery_level((String) ((BasicDBObject) ret.get("boot_info")).get("battery_level"));
		b.setShortserverid((String) ((BasicDBObject) ret.get("boot_info")).get("shortserverid"));
		b.setManufacturer((String) ((BasicDBObject) ret.get("boot_info")).get("manufacturer"));
		b.setModel_no((String) ((BasicDBObject) ret.get("boot_info")).get("model_no"));
		b.setSerial_no((String) ((BasicDBObject) ret.get("boot_info")).get("serial_no"));
		b.setFrimware_no((String) ((BasicDBObject) ret.get("boot_info")).get("frimware_no"));
		b.setPower_source((String) ((BasicDBObject) ret.get("boot_info")).get("power_source"));
		b.setPower_source_voltage((String) ((BasicDBObject) ret.get("boot_info")).get("power_source_voltage"));
		b.setBattery_level((String) ((BasicDBObject) ret.get("boot_info")).get("battery_level"));
		b.setServer_key((String) ((BasicDBObject) ret.get("boot_info")).get("server_key"));
		b.setHold_time((String) ((BasicDBObject) ret.get("boot_info")).get("hold_time"));
		b.setCurrent_time((String) ((BasicDBObject) ret.get("boot_info")).get("current_time"));
		b.setUtc_offset((String) ((BasicDBObject) ret.get("boot_info")).get("utc_offset"));
		b.setReg_server_add("http://localhost:8080/register");
		return  b;
		
	}
	
	public registratio_info dump_into_db(registratio_info i)
	{
		this.db = this.conn.getDB("registration");
		this.coll = db.getCollection("params");
		
		BasicDBObject document = new BasicDBObject();
		/*
		private String version;
		private String lifetime;
		private String binding_mode;
		private String sms_no;
		private String endpoint_name;*/
		
		document.put("version", i.getVersion());
		document.put("lifetime", i.getLifetime());
		document.put("binding_mode", i.getBinding_mode());
		document.put("sms_no",i.getSms_no());
		document.put("endpoint_name", i.getEndpoint_name());
		this.coll.insert(document);
		return i;
		
	}
	
	public int delete_rec(String id)
	{
		this.db = this.conn.getDB("registration");
		this.coll = db.getCollection("params");
		BasicDBObject document = new BasicDBObject();
		document.put("endpoint_name", id);
		this.coll.remove(document);
		return 0;
	}

}
