package is.hi.lucky7.timeandplace;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Events";

	private static final int DATABASE_VERSION = 4;

	// Database creation SQL statement
	// id | name | start_time | end_time | latitude | longitude | info
	// TODO: Add fields for latitude and longitude instead of location field
	// TODO: Add (boolean?) field for event passed/not passed
	// TODO: UPDATE DATABASE VERSION
	// TODO: Fix all functions according to database update
	private static final String DATABASE_CREATE = ""
			+ "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name TEXT NOT NULL,"
			+ "start_time INTEGER NOT NULL,"
			+ "end_time INTEGER NOT NULL,"
			+ "latitude DOUBLE NOT NULL,"
			+ "longitude DOUBLE NOT NULL,"
			+ "transport INTEGER NOT NULL,"
			+ "info TEXT,"
			+ "passed INTEGER NOT NULL"
			+ ");";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	// Method is called during an upgrade of the database, e.g. if you increase
	// the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(DBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS events");
		onCreate(database);
	}
}