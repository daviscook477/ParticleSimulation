package daviscook447.vectors;

import java.awt.Color;

public class PointCharge {

	public Vector2D location;
	public float magnitude;
	public Color color;
	public float radius;
	
	public PointCharge(float x, float y, float magnitude, Color color, float radius) {
		location = new Vector2D(x, y);
		this.magnitude = magnitude;
		this.color = color;
		this.radius = radius;
	}
	
}
