package dleblond.objects;

public interface IObjects
{
	public Boolean 			isAlive();
	public String			getName();
	public int 				getHp();
	public int				getMaxHp();
	public int 				getEp();
	public void 			checkCollision(Objects object);
	public void				stillAlive();
	public void 			takeDamage(int amount);
	public void 			beRepair(int amount);
	public void 			epRegain(int amount);
}
