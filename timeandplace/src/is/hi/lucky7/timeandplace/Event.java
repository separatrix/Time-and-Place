package is.hi.lucky7.timeandplace;

public class Event {

	int _id;
	String _name;
	long _startTime;
	long _endTime;
	String _location;
	int _transport;
	String _info;
	
	// Smidur fyrir nyskraningu atburda
	public Event(String name, long startTime, long endTime, String location, int transport, String info){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._location = location;
		this._transport = transport;
		this._info = info;
	}
	// Smidur fyrir lestur ur gagnagrunni og editing
	public Event(int id, String name, long startTime, long endTime, String location, int transport, String info){
		this._id = id;
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._location = location;
		this._transport = transport;
		this._info = info;
	}
	
	public Event(String name, long startTime, long endTime, String location, String info){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._location = location;
		this._transport = 0;
		this._info = info;
	}
		
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
		
		public String getLocation() {
			return this._location;
		}
		
		public void setLocation(String location) {
			this._location = location;
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
}
