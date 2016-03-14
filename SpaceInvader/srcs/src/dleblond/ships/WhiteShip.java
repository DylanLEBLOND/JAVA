package dleblond.ships;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class WhiteShip extends ActifShip
{
	private Animation 	anim;
	private int			score;
	
	public WhiteShip(int x, int y) throws SlickException
	{
		super("WhiteShip", 100, 100, x, y, 28, 24);
		
		Animation anim = new Animation();
		SpriteSheet sprite = new SpriteSheet("ressources/ships/spaceship1-White.png", 28, 24);
    	anim.addFrame(sprite.getSprite(0, 0), 100);
    	this.anim = anim;
    	this.score = 0;
	}
	
	public Animation getAnim()
	{
		return (this.anim);
	}
	
	public int 		getScore()
	{
		return (this.score);
	}
	
	public void		addScore(int amount)
	{
		this.score += amount;
	}
}
