package daviscook447.particles;

import java.awt.Color;
import java.awt.Graphics2D;

import daviscook447.vectors.Vector2D;

public class Particle {

	public static final float RADIUS = 1.0f;
	
	public Vector2D position, velocity;
	public Color color;
	public float magnitude;
	public int age;
	
	public Particle(Vector2D position, Vector2D velocity, Color color, float magnitude) {
		this.position = position;
		this.velocity = velocity;
		this.color = color;
		this.magnitude = magnitude;
		this.age = 0;
	}
	
	public void render(Graphics2D g2D, Vector2D modifier) {
		Vector2D drawPosition = position.plus(modifier);
		g2D.setColor(color);
		g2D.drawOval((int)(drawPosition.x-RADIUS), (int)(drawPosition.y-RADIUS), (int)(2*RADIUS), (int)(2*RADIUS));
		age++;
	}
	
	public void applyAcceleration(Vector2D acceleration) {
		velocity = velocity.plus(acceleration);
	}
	
	public void movePosition() {
		position = position.plus(velocity);
	}
	 
}
