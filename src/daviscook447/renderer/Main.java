package daviscook447.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import daviscook447.particles.ParticleSystem;
import daviscook447.vectors.PointCharge;
import daviscook447.vectors.Vector2D;

public class Main {

	public static final Random rng = new Random();
	
	public static final float randomFloatInRange(float min, float max) {
		float delta = max - min;
		return min + rng.nextFloat() * delta;
	}
	
	public static ArrayList<PointCharge> getRandomlyPlacedCharges(int width, int height, float radius, int count, float minMag, float maxMag) {
		ArrayList<PointCharge> pointCharges = new ArrayList<PointCharge>(count);
		for (int i = 0; i < count; i++) {
			Color color = ColorPalette.VIBRANT_COLORS.colors[i%ColorPalette.VIBRANT_COLORS.colors.length];
			float x = randomFloatInRange(0+width*0.25f,width-width*0.25f);
			float y = randomFloatInRange(0+height*0.25f,height-height*0.25f);
			float magnitude = randomFloatInRange(minMag, maxMag);
			if (rng.nextBoolean()) {
				magnitude=-1.0f;
			}
			pointCharges.add(new PointCharge(x, y, magnitude, color, radius));
		}
		return pointCharges;
	}
	
	public static final void drawAllPointCharges(Graphics2D g2D, Vector2D drawOffset, ArrayList<PointCharge> pointCharges) {
		for(PointCharge pointCharge : pointCharges) {
			g2D.setColor(pointCharge.color);
			g2D.fillOval((int)(pointCharge.location.x-pointCharge.radius), (int)(pointCharge.location.y-pointCharge.radius), (int)pointCharge.radius*2, (int)pointCharge.radius*2);
		}
	}
	
	public static void main(String[] args) {
		int width = 1200, height = 800;
		JFrame frame = new JFrame();
		frame.setTitle("Partices!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArrayList<PointCharge> charges = getRandomlyPlacedCharges(width, height, 10.0f, 9, 2.0f, 8.0f);
		ParticleSystem particleSystem = new ParticleSystem();
		
		Vector2D drawOffset = new Vector2D(0,0);
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2D = (Graphics2D) g;
				particleSystem.drawAll(g2D, drawOffset);
				drawAllPointCharges(g2D, drawOffset, charges);
			}
		};
		panel.setBackground(new Color(30,30,30));
		panel.setPreferredSize(new Dimension(width, height));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < 5; i++) {
						particleSystem.movementTick(charges, width, height);
						panel.repaint();
						try {
							sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // 50 Hz
					}
					particleSystem.spawnTick(charges);
				}
			}
		};
		thread.start();
	}
	
}
