package dleblond.guns;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import dleblond.ships.Bullets;
import dleblond.ships.Ship;

public class Gun implements IGuns
{
	public String 					name;
	protected ArrayList<Bullets>	ammo;
	protected int					current;
	protected int 					nb;
	protected boolean				ar;
	
	public 			Gun(String name, int nb, boolean ar) throws SlickException
	{
		int 	i = 0;
		Bullets bul;
		
		this.name = name;
		this.ammo = new ArrayList<Bullets>();
		while (i < nb)
		{
			bul = new Bullets(0, 0);
			bul.setActif(false);
			this.ammo.add(bul);
			++i;
		}
		this.nb = nb;
		this.ar = ar;
	}
	
	public String	getName()
	{
		return (this.name);
	}
	
	public int		getRest()
	{
		return (this.nb - this.current);
	}
	
	public ArrayList<Bullets> getAmmo()
	{
		return (this.ammo);
	}
	
	private	int		getBullet() throws SlickException
	{
		int 	i = 0;
		
		while (i < ammo.size())
		{
			if (!ammo.get(i).isActif() && ammo.get(i).isAlive() && this.current < this.nb)
				return (i);
			if (!ammo.get(i).isActif() && !ammo.get(i).isAlive() && this.ar && this.current < this.nb)
			{
				this.ammo.set(i, new Bullets(0, 0));
				return (i);
			}
			++i;
		}
		return (-1);
	}
	
	public void shoot(int x, int y) throws SlickException
	{
		int 	pos;
		Bullets bul; 
		
		if ((pos = this.getBullet()) == -1)
			return ;
		bul = this.ammo.get(pos);
		bul.x = x;
		bul.y = y;
		bul.setActif(true);
		this.current += 1;
		this.ammo.set(pos, bul);
	}

	
	public void	checkBulletCollision(Ship ship)
	{
		int 	i = 0;
		Bullets bul;
		
		while (i < this.ammo.size())
		{
			bul = this.ammo.get(i);
			if (bul.isActif())
			{
				bul.checkCollision(ship);
				if (!bul.isAlive())
					bul.setActif(false);
			}
			this.ammo.set(i, bul);
			++i;
		}
	}
	
	public void	mooveAll(int dx, int dy, int xmax, int ymax)
	{
		int 	i = 0;
		Bullets bul;
		
		while (i < this.ammo.size())
		{
			bul = this.ammo.get(i);
			if (bul.isActif())
			{
				if (bul.x + dx < 0 || bul.x + dx > xmax || bul.y + dy < 0 || bul.y + dy > ymax)
				{
					if (this.ar)
						this.current -= 1;
					bul.setActif(false);
				}
				else
					bul.x += dx;
				this.ammo.set(i, bul);
			}
			++i;
		}
	}
}
