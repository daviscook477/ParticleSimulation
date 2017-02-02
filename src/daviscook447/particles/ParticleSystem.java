package daviscook447.particles;

import java.awt.Graphics2D;
import java.util.ArrayList;

import daviscook447.vectors.PointCharge;
import daviscook447.vectors.PointChargeField;
import daviscook447.vectors.Vector2D;

public class ParticleSystem {
	
	public ArrayList<Particle> particles;
	
	public ParticleSystem() {
		particles = new ArrayList<Particle>();
	}
	
	public void drawAll(Graphics2D g2D, Vector2D drawOffset) {
		for (Particle p : particles) {
			if (p == null) {
				continue;
			}
			p.render(g2D, drawOffset);
		}
	}

	public static final int MAX_AGE = 1000;
	
	public static final boolean shouldRecycle(Particle particle, ArrayList<PointCharge> pointCharges, int width, int height) {
		if (particle.age > MAX_AGE) {
			return true;
		}
		if (particle.position.x < -2*width || particle.position.y < -2*height || particle.position.x > 3*width || particle.position.y > 3*height) {
			return true;
		}
		for (PointCharge pointCharge : pointCharges) {
			if ((pointCharge.location.x-particle.position.x)*(pointCharge.location.x-particle.position.x)+(pointCharge.location.y-particle.position.y)*(pointCharge.location.y-particle.position.y)<(pointCharge.radius+Particle.RADIUS)*(pointCharge.radius+Particle.RADIUS)) {
				return true;
			}
		}
		return false;
	}
	
	public void movementTick(ArrayList<PointCharge> charges, int width, int height) {
		PointChargeField chargeField = new PointChargeField(charges);
		for (int i = 0; i < particles.size(); i++) {
			Particle particle = particles.get(i);
			particle.applyAcceleration(chargeField.accelerationFor(particle));
			particle.movePosition();
			if (shouldRecycle(particle, charges, width, height)) {
				particles.remove(particle);
				i--;
			}
		}
	}

	public static final int NUMBER_TO_SPAWN = 12;
	
	public void spawnTick(ArrayList<PointCharge> charges) {
		for (PointCharge pointCharge : charges) {
			for (int i = 0; i < NUMBER_TO_SPAWN; i++) {
				float theta = (float) (((float)i)/((float)NUMBER_TO_SPAWN)*Math.PI*2);
				Vector2D delta = new Vector2D((float)(pointCharge.radius*1.5f*Math.sin(theta)),(float)(pointCharge.radius*1.5f*Math.cos(theta)));
				particles.add(new Particle(pointCharge.location.copy().plus(delta), delta.copy().normalize(), pointCharge.color, (float) Math.signum(pointCharge.magnitude)));
			}
		}
		
	}
	
}
