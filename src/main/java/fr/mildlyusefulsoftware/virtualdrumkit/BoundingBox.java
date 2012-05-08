package fr.mildlyusefulsoftware.virtualdrumkit;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class BoundingBox {
	
	private float startX;
	private float startY;
	private float endX;
	private float endY;
	private String name;
	private static String TAG = "virtualdrumkit";
	
	private static float REFERENCE_WIDTH=800;
	private static float REFERENCE_HEIGHT=480;
	
	public BoundingBox(String name,float startX, float startY, float endX, float endY,float actualWidth,float actualHeight) {
		super();
		this.startX = startX*actualWidth/REFERENCE_WIDTH;
		this.startY = startY*actualHeight/REFERENCE_HEIGHT;
		this.endX = endX*actualWidth/REFERENCE_WIDTH;
		this.endY = endY*actualHeight/REFERENCE_HEIGHT;
		this.name=name;
		
	}
	
	public boolean contains(float sourceX,float sourceY){
		boolean result= sourceX>=startX && sourceX<=endX && sourceY>=startY && sourceY<=endY;
		if (result)
			Log.d(TAG,"insinde bounding box "+name);
		return result;
	}
	
}
