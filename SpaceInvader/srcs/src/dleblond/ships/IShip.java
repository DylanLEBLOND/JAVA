package dleblond.ships;

import java.util.ArrayList;

import dleblond.guns.Gun;

public interface IShip
{
	public Boolean 			IsAlive();
	public String			getName();
	public int 				getHp();
	public int				getMaxHp();
	public int 				getEp();
	public ArrayList<Gun> 	getGuns();
	public void 			checkCollision(Ship ship);
	public void				stillAlive();
	public void 			takeDamage(int amount);
	public void 			beRepair(int amount);
	public void 			epRegain(int amount);
	public void 			addGun(Gun newGun);
	public void				changeGun(int number);
}
