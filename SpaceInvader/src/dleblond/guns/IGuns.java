package dleblond.guns;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import dleblond.objects.Bullets;

public interface IGuns
{
	public String				getName();
	public int					getRest();
	public ArrayList<Bullets> 	getAmmo();
	public void 				shoot(int x, int y) throws SlickException;
	public void					mooveAll(int dx, int dy, int xmax, int ymax);
}
