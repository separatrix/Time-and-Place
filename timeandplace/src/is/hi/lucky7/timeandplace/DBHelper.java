package is.hi.lucky7.timeandplace;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Events";

	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	// id | name | start_time | end_time | location | info

	private static final String DATABASE_CREATE = ""
			+ "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name TEXT NOT NULL,"
			+ "start_time INTEGER NOT NULL,"
			+ "end_time INTEGER NOT NULL,"
			+ "location TEXT,"
			+ "info TEXT);";

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