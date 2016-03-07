package dleblond.guns;

public class Gun implements IGuns
{
	public String name;
	protected int range;
	protected int damage;
	protected int cost;
	
	public Gun(String name, int range, int damage, int cost)
	{
		this.name = name;
		this.range = range;
		this.damage = damage;
		this.cost = cost;
	}
	
	public int getRange()
	{
		return (this.range);
	}
	
	public int getDamage()
	{
		return (this.damage);
	}
	
	public int getCost()
	{
		return (this.cost);
	}
}
