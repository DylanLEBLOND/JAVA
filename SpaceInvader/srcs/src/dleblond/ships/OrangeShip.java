package dleblond.ships;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class OrangeShip extends Ship
{
	private Animation anim;
	
	public OrangeShip(int x, int y) throws SlickException
	{
		super("OrangeShip", 5, 100, x, y, 30, 26);
		
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet("ressources/ships/spaceship1-Orange.png", 64, 64);
    	anim.addFrame(sprite.getSprite(0, 0), 100);
    	this.anim = anim;
	}
	
	public Animation getAnim()
	{
		return (this.anim);
	}
}
