package is.hi.lucky7.timeandplace;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class MapOverlay extends Overlay {
	Context mContext;
	Map activity;

	public MapOverlay(Context ctx, Map act) {
		super(ctx);
		mContext = ctx;
		activity = act;
		// TODO Auto-generated constructor stub
	}

	public MapOverlay(ResourceProxy pResourceProxy) {
		super(pResourceProxy);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void draw(Canvas c, MapView osmv, boolean shadow) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean onLongPress(final MotionEvent e, final MapView mapView) {
	  float xCoord = e.getX();
	  float yCoord = e.getY();
	  IGeoPoint point = mapView.getProjection().fromPixels(xCoord, yCoord);
	  activity.setCoords(point);
	  return true;
	}

}
