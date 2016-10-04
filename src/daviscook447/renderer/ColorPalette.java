package daviscook447.renderer;

import java.awt.Color;

public class ColorPalette {

	public static final Color[] GET_VIBRANT_COLORS() {
		Color[] vibrants = new Color[9];
		for (int i = 0; i < 9; i++) {
			float hue = ((float) i) / 9.0f;
			float sat = 1.0f;
			float val = 1.0f;
			vibrants[i] = Color.getHSBColor(hue, sat, val);
		}
		return vibrants;
	}
	
	public static final ColorPalette VIBRANT_COLORS = new ColorPalette(GET_VIBRANT_COLORS());
	
	public Color[] colors;
	
	public ColorPalette(Color c0, Color c1, int numColors) {
		if (numColors < 2) {
			throw new IllegalArgumentException("numColors must be >= 2");
		}
		colors = new Color[numColors];
		for (int i = 0; i < numColors; i++) {
			float percent0 = ((float) i) / (numColors - 1);
			float percent1 = ((float) (numColors - 1 - i)) / (numColors - 1);
			System.out.println(percent0 + ":" + percent1);
			colors[i] = new Color((int)(c0.getRed()*percent0+c1.getRed()*percent1),
					(int)(c0.getGreen()*percent0+c1.getGreen()*percent1),
					(int)(c0.getBlue()*percent0+c1.getBlue()*percent1));
		}
	}
	
	public ColorPalette(Color[] colors) {
		this.colors = colors;
	}
	
}
