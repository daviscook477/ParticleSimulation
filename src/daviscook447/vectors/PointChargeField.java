package daviscook447.vectors;

import java.util.ArrayList;

import daviscook447.particles.Particle;

public class PointChargeField {
	
	public static final Vector2D accelerationFor(PointCharge pointCharge, Particle particle) {
		float distanceSquared = (particle.position.x-pointCharge.location.x)*(particle.position.x-pointCharge.location.x)+(particle.position.y-pointCharge.location.y)*(particle.position.y-pointCharge.location.y);
		return new Vector2D(particle.position.x-pointCharge.location.x,particle.position.y-pointCharge.location.y).mul(pointCharge.magnitude/distanceSquared*particle.magnitude);
	}
	
	private ArrayList<PointCharge> pointCharges;
	
	public PointChargeField(ArrayList<PointCharge> pointCharges) {
		this.pointCharges = pointCharges;
	}
	
	public Vector2D accelerationFor(Particle particle) {
		Vector2D accel = new Vector2D(0.0f, 0.0f);
		for (PointCharge pointCharge : pointCharges) {
			accel = accel.plus(accelerationFor(pointCharge, particle));
		}
		return accel;
	}

}
