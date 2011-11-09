package is.hi.lucky7.timeandplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	
	// Database fields
	// id | name | start_time | end_time | location | info

	static final String dbName="Events";
	private static final String eventTable ="events";
	public static final String colId = "_id";
	public static final String colName="name";
	public static final String colStartTime ="start_time";
	public static final String colEndTime="end_time";
	public static final String colLocation="location";
	public static final String transport ="transport";
	public static final String colInfo="info";
	
	private Context context;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	public DBAdapter(Context context){
		this.context = context;
	}
	
	public DBAdapter open() throws SQLException {
		dbHelper = new DBHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}
	
	public long AddEvent(Event e)
	{	 	 
		ContentValues cv=new ContentValues();
		
		cv.put(colName, e.getName());
		cv.put(colStartTime, e.getStartTime());
		cv.put(colEndTime, e.getEndTime());
		cv.put(colLocation, e.getLocation());
		cv.put(transport, e.getTransport());
		cv.put(colInfo, e.getInfo());
		
		return db.insert(eventTable, colName, cv);
	}
	
	 Cursor getAllEvents()
	 {		 
		 Cursor cur= db.rawQuery("SELECT * FROM "+eventTable,null);
		 return cur;
	 }
	 
	 Cursor getEventName(int id)
	 {
		 Cursor cur = db.rawQuery("SELECT "+colName+" FROM "+eventTable+" WHERE " +colId+ "= " +Integer.toString(id),null);
		 return cur;
	 }
	 
	 Event getEvent(int id)
	 {
		 Cursor c = db.rawQuery("SELECT * FROM " +eventTable+ " WHERE " +colId+ "= " +Integer.toString(id), null);
		 c.moveToFirst();
		 String name = c.getString(0);
		 int start = c.getInt(1);
		 int end = c.getInt(2);
		 String loc = c.getString(3);
		 int trans = c.getInt(4);
		 String info = c.getString(5);
		 
		 Event e = new Event(name, start, end, loc, trans, info);
		 return e;
	 }
	 
	 Cursor getEventCursor(int id) {
		 Cursor c = db.rawQuery("SELECT * FROM " +eventTable+ " WHERE " +colId+ "= " +Integer.toString(id), null);
		 return c;
	 }
	 
	 public int getEventCount() 
	 {
			Cursor cur= db.rawQuery("Select * from "+eventTable, null);
			int x= cur.getCount();
			cur.close();
			return x;
	 }
	 
	 // Spurning: Yfirskrifa alla linuna alltaf eda bara einn field i einu ?
	 // Ef allt i einu tha, saekja event og edita staka fields ..........
	 // Vidmots utfaersla ?
	 
	 // Ein leid vaeri ad nota getEvent og breyta thvi yfir i Event obj og lesa ur thvi i "Edit fields" i vidmoti
	 // Sidan gaeti notandi editad thad sem stendur i theim fields og submittad aftur.
	 // Tha yrdi skrifad aftur i event objectid og thad skrifad i db med sama id og adur. (Gamla entry yfirskrifad).
	 public void updateEvent(Event e)
	 {
		 ContentValues cv = new ContentValues();
			cv.put(colName, e.getName());
			cv.put(colStartTime, e.getStartTime());
			cv.put(colEndTime, e.getEndTime());
			cv.put(colLocation, e.getLocation());
			cv.put(transport, e.getTransport());
			cv.put(colInfo, e.getInfo());
			
			db.update(eventTable, cv, colId + "=" + e.getId(), null);
	 }
	 
	 public void deleteEvent(int id) {
		 db.delete(eventTable,colId+ " = " +id,null);
	 }
}
