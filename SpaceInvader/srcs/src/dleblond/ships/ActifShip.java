package dleblond.ships;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import dleblond.guns.Gun;

public class ActifShip extends Ship
{
	public int					dx;
	public int					dy;
	protected ArrayList<Gun>	guns;
	protected int				currentWeap;

	public ActifShip(String name, int maxhp, int maxep, int x, int y, int width, int height) throws SlickException
	{
		super(name, maxhp, maxep, x, y, width, height);
		this.guns = new ArrayList<Gun>();
		this.guns.add(new Gun("default", this.maxep, true));
		this.currentWeap = 0;
		this.dx = 1;
		this.dy = 1;
	}
	
	public Gun getGun()
	{
		return (this.guns.get(this.currentWeap));
	}
	
	public void	setGun(Gun gun)
	{
		this.guns.set(this.currentWeap, gun);
	}
	
	public void addGun(Gun newGun)
	{
		if (!this.alive)
			return ;
		this.guns.add(newGun);
	}
	
	public void	changeGun(int number)
	{
		if (!this.alive)
			return ;
		if (number <= 0 || number > this.guns.size())
			return ;
		this.currentWeap = number - 1;
	}
}
