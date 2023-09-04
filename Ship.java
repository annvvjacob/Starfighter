import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {
	private int speed;
	private Image image;

	public Ship() {
		this(10,10,10,10,10);
	}

	public Ship(int x, int y) {
		super(x, y);
		speed = 10;
	}

	public Ship(int x, int y, int s) {
		super(x, y);
		speed = s;
	}

	public Ship(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		speed=s;
		try
		{
			URL url = getClass().getResource("ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println("did not work");
		}
	}


	public void setSpeed(int s) {
		speed = s;
	}

	public int getSpeed() {
	   return speed;
	}

	public void move(String direction) {
		if (direction.equals("NORTH")) {
			setY(getY() - speed);
		}
		if (direction.equals("SOUTH")) {
			setY(getY() + speed);
		}
		if (direction.equals("EAST")) {
			setX(getX() + speed);
		}
		if (direction.equals("WEST")) {
			setX(getX() - speed);
		}
	}

	public void draw( Graphics window ) {
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString() {
		return super.toString() + getSpeed();
	}
}
