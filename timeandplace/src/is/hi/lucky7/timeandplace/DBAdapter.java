package is.hi.lucky7.timeandplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	
	// Database fields
	// id | name | start_time | end_time | location | info

	static final String dbName="timeandplace";
	private static final String eventTable ="events";
	public static final String colId = "_id";
	public static final String colName="name";
	public static final String colStartTime ="start_time";
	public static final String colEndTime="end_time";
	public static final String colLocation="loaction";
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
		cv.put(colInfo, e.getInfo());
		
		return db.insert(eventTable, colName, cv);
	}
	
	 Cursor getAllEvents()
	 {		 
		 Cursor cur= db.rawQuery("SELECT * FROM "+eventTable,null);
		 return cur;
	 }
	 
	 Cursor getEvent(int id)
	 {
		 Cursor cur = db.rawQuery("SELECT "+colName+" FROM "+eventTable+" WHERE " +colId+ "= " +Integer.toString(id),null);
		 return cur;
	 }
	 
	 

}