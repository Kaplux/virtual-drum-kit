package fr.mildlyusefulsoftware.virtualdrumkit;

public class BoundingBox {
	
	private float startX;
	private float startY;
	private float endX;
	private float endY;
	
	public BoundingBox(float startX, float startY, float endX, float endY) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	
	public boolean contains(float sourceX,float sourceY){
		return sourceX>=startX && sourceX<=endX && sourceY>=startY && sourceY<=endY;
	}
	
}
