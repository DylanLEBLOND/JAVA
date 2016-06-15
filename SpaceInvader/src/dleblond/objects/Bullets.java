package dleblond.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bullets extends Objects
{
	private Animation 	anim;
	private boolean		actif;
	
	public Bullets(int x, int y) throws SlickException
	{
		super("Bullet", 1, 0, x, y, 10, 10);
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet("ressources/bullets/bullet.png", 13, 14);
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
