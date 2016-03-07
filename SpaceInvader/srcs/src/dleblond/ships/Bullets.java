package dleblond.ships;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bullets extends Ship
{
	private Animation anim;
	
	public Bullets(int x, int y) throws SlickException
	{
		super("Bullet", 1, 0, x, y, 10, 10);
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet("ressources/bullets/bullet.png", 13, 14, Color.white);
    	anim.addFrame(sprite.getSprite(0, 0), 100);
    	this.anim = anim;
	}
	
	public Animation getAnim()
	{
		return (this.anim);
	}
}
