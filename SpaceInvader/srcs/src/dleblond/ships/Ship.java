package dleblond.ships;

public class Ship implements IShip
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
	
	public Ship(String name, int maxhp, int maxep, int x, int y, int width, int height)
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
	
	//Ship's Function
	
	public void checkCollision(Ship ship)
	{
		if (!this.alive || !ship.isAlive())
			return ;
		if (((this.x >= ship.x && this.x <= ship.x + ship.width) ||
				(this.x + this.width >= ship.x && this.x + this.width <= ship.x + ship.width))
			&& ((this.y >= ship.y && this.y <= ship.y + ship.height) ||
				(this.y + this.height >= ship.y && this.y + this.height <= ship.y + ship.height)))
		{
			this.takeDamage(ship.getMaxHp());
			ship.takeDamage(this.maxhp);
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
