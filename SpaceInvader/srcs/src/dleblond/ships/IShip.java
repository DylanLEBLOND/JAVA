package dleblond.ships;

public interface IShip
{
	public Boolean 			isAlive();
	public String			getName();
	public int 				getHp();
	public int				getMaxHp();
	public int 				getEp();
	public void 			checkCollision(Ship ship);
	public void				stillAlive();
	public void 			takeDamage(int amount);
	public void 			beRepair(int amount);
	public void 			epRegain(int amount);
}
