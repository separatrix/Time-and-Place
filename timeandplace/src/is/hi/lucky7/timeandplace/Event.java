package is.hi.lucky7.timeandplace;

public class Event {

	int _id;
	String _name;
	int _startTime;
	int _endTime;
	String _location;
	String _info;
	
	public Event(String name, int startTime, int endTime, String location, String info){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._location = location;
		this._info = info;
	}
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
		
		public int getStartTime() {
			return this._startTime;
		}
		
		public void setStartTime(int startTime){
			this._startTime = startTime;
		}
		
		public int getEndTime() {
			return this._endTime;
		}
		
		public void setEndTime(int endTime) {
			this._endTime = endTime;
		}
		
		public String getLocation() {
			return this._location;
		}
		
		public void getLocation(String location) {
			this._location = location;
		}
		
		public String getInfo() {
			return this._info;
		}
		
		public void setInfo(String info) {
			this._info = info;
		}
}
