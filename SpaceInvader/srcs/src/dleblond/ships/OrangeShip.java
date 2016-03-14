package dleblond.ships;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class OrangeShip extends ActifShip
{
	private Animation 	anim;
	
	public OrangeShip(int x, int y) throws SlickException
	{
		super("OrangeShip", 5, 100, x, y, 28, 24);
		
		Random rand = new Random();
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet("ressources/ships/spaceship1-Orange.png", 28, 24);
    	anim.addFrame(sprite.getSprite(0, 0), 100);
    	this.anim = anim;
    	if (rand.nextBoolean())
    		this.dx = Math.negateExact(this.dx);
    	if (rand.nextBoolean())
    		this.dy = Math.negateExact(this.dy);
	}
	
	public Animation getAnim()
	{
		return (this.anim);
	}
}
