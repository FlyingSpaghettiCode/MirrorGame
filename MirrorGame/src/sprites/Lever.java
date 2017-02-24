package sprites;

import java.lang.reflect.Field;

import images.ImageLoader;

public class Lever extends Sprite implements Collidable {

	boolean on = false;
	Sprite target;
	Field field;
	double TOGGLE_INTERVAL = 20;
	double lastToggle = -10000;
	Object onVal;
	Object offVal;

	public Lever(double x, double y, double width, double height, boolean red, boolean green, boolean blue, Sprite target,
			String clazz, String field, Object onVal, Object offVal) {
		super(x, y, width, height);

		this.red = red;
		this.green = green;
		this.blue = blue;

		this.update();

		this.onVal = onVal;
		this.offVal = offVal;

		this.target = target;

		try {
			this.field = Class.forName(clazz).getDeclaredField(field);
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		this.setImage(ImageLoader.getImage((on ? "right" : "left") + "Lever"));
	}

	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		MoveableSprite s = (MoveableSprite) otherSprite;

		s.setxPosition(s.getxPosition() + mtv[0]);
		s.setyPosition(s.getyPosition() + mtv[1]);

		if (this.getLevel().getFrame() - lastToggle >= TOGGLE_INTERVAL) {
			lastToggle = this.getLevel().getFrame();

			on = !on;

			this.update();

			try {
				field.set(target, on ? onVal : offVal);
				target.update();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
