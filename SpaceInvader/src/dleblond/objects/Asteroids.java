package dleblond.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Asteroids extends Objects
{
	private Animation 	anim;
	private boolean		actif;
	
	public Asteroids(int x, int y, String image, int width, int height) throws SlickException
	{
		super("Asteroid", 1, 0, x, y, 10, 10);
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet(image, width, height);
    	anim.addFrame(sprite.getSprite(0, 0), 100);
    	this.anim = anim;
    	this.actif = false;
	}
	
	public Animation getAnim()
	{
		return (this.anim);
	}
	
	public void		setActif(boolean value)
	{
		this.actif = value;
	}
	
	public boolean 	isActif()
	{
		return (this.actif);
	}
}
