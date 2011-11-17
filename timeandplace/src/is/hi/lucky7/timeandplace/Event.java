package is.hi.lucky7.timeandplace;

public class Event {

	int _id;
	String _name;
	long _startTime;
	long _endTime;
	Double _locLat;
	Double _locLon;
	int _transport;
	String _info;
	
	public Event(String name, long startTime, long endTime, Double locLat, Double locLon, int transport, String info){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._locLat = locLat;
		this._locLon = locLon;
		this._transport = transport;
		this._info = info;
	}
	
	public Event(String name, long startTime, long endTime, Double locLat, Double locLon, String info){
		this._name = name;
		this._startTime = startTime;
		this._endTime = endTime;
		this._locLat = locLat;
		this._locLon = locLon;
		this._transport = 0;
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
		
		public Double getLocLat() {
			return this._locLat;
		}
		
		public Double getLocLon() {
			return this._locLon;
		}
		
		public void setLocLat(Double locLat) {
			this._locLat = locLat;
		}
		
		public void setLocLon(Double locLon) {
			this._locLon = locLon;
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
