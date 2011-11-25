package is.hi.lucky7.timeandplace;

public class Event {

	int _id;
	String _name;
	long _startTime;
	long _endTime;
	int _latitude;
	int _longitude;
	int _transport;
	String _info;
	boolean _passed;
	
	// Smidur fyrir nyskraningu atburda
	public Event(String name, long startTime, long endTime, int latitude, int longitude, 
			int transport, String info, boolean passed){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._latitude = latitude;
		this._longitude = longitude;
		this._transport = transport;
		this._info = info;
		this._passed = passed; 
	}
	
	// Smidur fyrir lestur ur gagnagrunni og editing
	public Event(int id, String name, long startTime, long endTime, int latitude, int longitude, 
			int transport, String info, boolean passed){
		this._id = id;
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._latitude = latitude;
		this._longitude = longitude;
		this._transport = transport;
		this._info = info;
		this._passed = passed;
	}
	/*
	public Event(String name, long startTime, long endTime, String location, String info){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._locLat = locLat;
		this._locLon = locLon;
		this._transport = 0;
		this._info = info;
	}
	*/	
		// Notist adeins a Event hluti sem innihalda id
		public int getId() {
			return this._id;
		}
		
		public void setId(int id) {
			this._id = id;
		}
		
		public String getName() {
			return this._name;
		}
		
		public void setName(String name) {
			this._name = name;
		}
		
		public long getStartTime() {
			return this._startTime;
		}
		
		public void setStartTime(long startTime){
			this._startTime = startTime;
		}
		
		public long getEndTime() {
			return this._endTime;
		}
		
		public void setEndTime(long endTime) {
			this._endTime = endTime;
		}
		public int getLatitude() {
			return this._latitude;
		}
		
		public void setLatitude(int latitude) {
			this._latitude = latitude;
		}
		
		public int getLongitude() {
			return this._longitude;
		}
		
		public void setLongitude(int longitude) {
			this._longitude = longitude;
		}
		
		public String getInfo() {
			return this._info;
		}
		
		public void setInfo(String info) {
			this._info = info;
		}
		
		public int getTransport() {
			return this._transport;
		}
		
		public void setTransport(int transport) {
			this._transport = transport;
		}
		
		public boolean getPassed() {
			return this._passed;
		}
		
		public void setPassed(boolean passed) {
			this._passed = passed;
		}
}
