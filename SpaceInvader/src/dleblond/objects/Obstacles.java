package dleblond.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Obstacles extends Objects
{
	private Animation anim;
	
	public Obstacles(int x, int y) throws SlickException
	{
		super("obstacles", 1, 0, x, y, 10, 10);
		
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet("ressources/ships/obstacles.png", 64, 64);
    	anim.addFrame(sprite.getSprite(0, 0), 100);
    	this.anim = anim;
	}
	
	public Animation getAnim()
	{
		return (this.anim);
	}
}
