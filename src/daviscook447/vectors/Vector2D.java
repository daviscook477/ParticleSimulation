package daviscook447.vectors;

public class Vector2D {

	public float x, y;
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D plus(Vector2D other) {
		return new Vector2D(x+other.x,y+other.y);
	}
	
	public Vector2D copy() {
		return new Vector2D(x, y);
	}
	
	public Vector2D mul(float coeff) {
		return new Vector2D(x*coeff,y*coeff);
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x*x+y*y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public Vector2D normalize() {
		return this.mul(1.0f/magnitude());
	}
	
}
