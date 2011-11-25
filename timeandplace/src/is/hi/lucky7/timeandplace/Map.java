package is.hi.lucky7.timeandplace;

import java.util.List;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

public class Map extends Activity {
    /** Called when the activity is first created. */
    private MapController mapController;
    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setZoom(15);
        LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Location loc = locMan.getLastKnownLocation("GPS_PROVIDER");
        GeoPoint point;
        if (loc != null) {
        	point = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        } else {
        	point = new GeoPoint(64.14310, -21.9146);
        }        
        mapController.setCenter(point);
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        MapOverlay tOverlay = new MapOverlay(this, this);
        tOverlay.setEnabled(true);
        mapOverlays.add(tOverlay);
    }
    
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    public void setCoords(IGeoPoint point) {
    	Intent i = new Intent();
    	i.putExtra("lat", point.getLatitudeE6());
    	i.putExtra("lon", point.getLongitudeE6());	
    	setResult(1, i);
    	finish();
    }
    
}  