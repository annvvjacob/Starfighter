import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;

   	private AlienHorde horde;
	private Bullets shots;

	private boolean[] keys;
	private BufferedImage back;

	private int timer = 0;

	public OuterSpace() {
		setBackground(Color.black);

		keys = new boolean[5];
		ship = new Ship(300, 300, 40, 40, 3);
		horde = new AlienHorde(30);
		shots = new Bullets();

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   	public void update(Graphics window)
   	{	
		paint(window);
   	}

	public void paint( Graphics window )
	{

		Graphics2D twoDGraph = (Graphics2D)window;

		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);

		ship.draw(graphToBack);
		horde.drawEmAll(graphToBack);
		shots.drawEmAll(graphToBack);
		
		shots.moveEmAll();
		shots.cleanEmUp();
		horde.removeDeadOnes(shots.getList());
		horde.moveEmAll();

		if(keys[0] == true) {
			ship.move("WEST");
		}
		if(keys[1] == true) {
			ship.move("EAST");
		}
		if(keys[2] == true) {
			ship.move("NORTH");
		}
		if(keys[3] == true) {
			ship.move("SOUTH");
		}
		if (keys[4] == true) {
			if (timer > 90) {
				shots.add(new Ammo(ship.getX() + 17, ship.getY(), 2));
				timer = 0;
			}
		}

		timer++;
		twoDGraph.drawImage(back, null, 0, 0);
		back = null;
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
        }
    } catch(Exception e) {
		System.out.println("DIdn't work");
      }
  	}
}

