package is.hi.lucky7.timeandplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	
	// Database fields
	// id | name | start_time | end_time | latitude | longitude | info | passed

	static final String dbName="Events";
	private static final String eventTable ="events";
	public static final String colId = "_id";
	public static final String colName="name";
	public static final String colStartTime ="start_time";
	public static final String colEndTime="end_time";
	public static final String colLatitude="latitude";
	public static final String colLongitude="longitude";
	public static final String transport ="transport";
	public static final String colInfo="info";
	public static final String colPassed="passed";
	
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
		cv.put(colLatitude, e.getLatitude());
		cv.put(colLongitude, e.getLongitude());
		cv.put(transport, e.getTransport());
		cv.put(colInfo, e.getInfo());
		cv.put(colPassed, e.getPassed());
		
		return db.insert(eventTable, colName, cv);
	}
	
	 Cursor getAllEvents()
	 {		 
		 Cursor cur= db.rawQuery("SELECT * FROM "+eventTable+" ORDER BY "+colStartTime+" ASC",null);
		 return cur;
	 }
	 
	 Cursor getEventName(int id)
	 {
		 Cursor cur = db.rawQuery("SELECT "+colName+" FROM "+eventTable+" WHERE " +colId+ "= " +Integer.toString(id),null);
		 return cur;
	 }
	 
	 Event getEvent(int id)
	 {
		 Cursor c = db.rawQuery("SELECT * FROM "+eventTable+" WHERE "+colId+"= " +Integer.toString(id), null);
		 c.moveToFirst();
		 // indice 0 is event ID
		 int evid = c.getInt(0);
		 String name = c.getString(1);
		 long start = c.getLong(2);
		 long end = c.getLong(3);
		 double lat = c.getDouble(4);
		 double lon = c.getDouble(5);
		 int trans = c.getInt(6);
		 String info = c.getString(7);
		 boolean passed = false;
		 if(c.getInt(8)>0) {
			 passed = true;
		 }
		 else{
			 passed = false;
		 }
		 
		 Event e = new Event(evid, name, start, end, lat, lon, trans, info, passed);
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
	 
	 public void updateEvent(Event e)
	 {
		 ContentValues cv = new ContentValues();
			cv.put(colName, e.getName());
			cv.put(colStartTime, e.getStartTime());
			cv.put(colEndTime, e.getEndTime());
			cv.put(colLatitude, e.getLatitude());
			cv.put(colLongitude, e.getLongitude());
			cv.put(transport, e.getTransport());
			cv.put(colInfo, e.getInfo());
			
			if(e.getPassed()) {
				cv.put(colPassed, 1);
			}
			else {
				cv.put(colPassed, 0);
			}
			
			db.update(eventTable, cv, colId + "=" + Integer.toString(e.getId()), null);
	 }
	 
	 public void updateEventWithId(int id, Event e)
	 {
		 ContentValues cv = new ContentValues();
			cv.put(colName, e.getName());
			cv.put(colStartTime, e.getStartTime());
			cv.put(colEndTime, e.getEndTime());
			cv.put(colLatitude, e.getLatitude());
			cv.put(colLongitude, e.getLongitude());
			cv.put(transport, e.getTransport());
			cv.put(colInfo, e.getInfo());
			cv.put(colPassed, e.getPassed());
			
			db.update(eventTable, cv, colId + "=" + Integer.toString(id), null);
	 }
	 
	 public void deleteEvent(int id) {
		 db.delete(eventTable,colId+ " = " +id,null);
	 }
	 
	 // Adferd til ad tjekka a
	 Cursor getEventsWithinTimeRange(long range) {
		 String[] cols = new String[] {colId, colName, colInfo, colStartTime, colLatitude, colLongitude};
		 String currTime = Long.toString(System.currentTimeMillis());
		 String selection = colStartTime + "-" + currTime + "<=" + Long.toString(range) +
				 " AND " + colPassed + "=0";
		 Cursor c = db.query(eventTable, cols, selection, null, null, null, null);
		 
		 return c;
	 }
}
