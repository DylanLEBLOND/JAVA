package dleblond.objects;

public class Objects implements IObjects
{
	public int					x;
	public int					y;
	public int 					height;
	public int					width;
	protected int				hp;
	protected int 				ep;
	protected Boolean			alive;
	protected int				maxhp;
	protected int				maxep;
	protected String 			name;
	
	public Objects(String name, int maxhp, int maxep, int x, int y, int width, int height)
	{
		this.alive = true;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.hp = maxhp;
		this.maxhp = maxhp;
		this.ep = maxep;
		this.maxep = maxep;
	}
	
	//Getters
	public Boolean isAlive()
	{
		return (this.alive);
	}
	
	public String getName()
	{
		return (this.name);
	}
	
	public int getHp()
	{
		return (this.hp);
	}
	
	public int getMaxHp()
	{
		return (this.maxhp);
	}
	
	public int getEp()
	{
		return (this.ep);
	}
	
	//object's Function
	
	public void checkCollision(Objects object)
	{
		if (!this.alive || !object.isAlive())
			return ;
		if (((this.x >= object.x && this.x <= object.x + object.width) ||
				(this.x + this.width >= object.x && this.x + this.width <= object.x + object.width))
			&& ((this.y >= object.y && this.y <= object.y + object.height) ||
				(this.y + this.height >= object.y && this.y + this.height <= object.y + object.height)))
		{
			this.takeDamage(object.getMaxHp());
			object.takeDamage(this.maxhp);
		}	
	}
	
	public void	stillAlive()
	{
		if (!this.alive)
			return ;
		if (this.hp <= 0)
			this.alive = false;
	}
	
	public void takeDamage(int amount)
	{
		if (!this.alive)
			return ;
		this.hp -= amount;
		this.stillAlive();
	}
	
	public void beRepair(int amount)
	{
		if (!this.alive)
			return ;
		this.hp += amount;
		if (this.hp > this.maxhp)
			this.hp = this.maxhp;
	}
	
	public void epRegain(int amount)
	{
		if (!this.alive)
			return ;
		this.ep += amount;
		if (this.ep > this.maxep)
			this.ep = this.maxep;
	}
}
