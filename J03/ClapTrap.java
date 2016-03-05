package j03.dleblon;

public class ClapTrap
{
	int 		hp;
	int 		ep;
	int			lv;
	protected int		maxhp;
	protected int		maxep;
	protected String 	name;
	protected int		mad;
	protected int		rad;
	protected int		adr;
	
	public ClapTrap(String name, int hp, int maxhp, int ep, int maxep, int lv, int mad, int rad, int adr)
	{
		this.name = name;
		this.hp = hp;
		this.maxhp = maxhp;
		this.ep = ep;
		this.maxep = maxep;
		this.lv = lv;
		this.mad = mad;
		this.rad = rad;
		this.adr = adr;
		System.out.println("CL4P-TP named " + this.name + " has been created.");
	}
	
	public void rangedAttack(final String target)
	{
		if (this.ep == 0 || this.ep - 25 < 0)
			System.out.println("CL4P-TP " + name + " is out of energy.");
		else
		{
			System.out.println("CL4P-TP " + this.name + " attacks " + target + " at range, causing " + this.rad + " points of damage !");
			this.ep -= 25;
			if (this.ep < 0)
				this.ep = 0;
		}
	}
	
	public void meleeAttack(String target)
	{
		if (this.ep == 0 || this.ep - 25 < 0)
			System.out.println("CL4P-TP " + name + " is out of energy.");
		else
		{
			System.out.println("CL4P-TP " + this.name + " attacks " + target + " at melee, causing " + this.mad + " points of damage !");
			this.ep -= 25;
			if (this.ep < 0)
				this.ep = 0;
		}
	}
	
	public void takeDamage(int amount)
	{
		if (amount <= 0 || amount - this.adr <= 0)
			return ;
		if (this.hp == 0)
			System.out.println("CL4P-TP " + this.name + " is already dead");
		else
		{
			this.hp -= (amount - this.adr);
			if (this.hp < 0)
				this.hp = 0;
			if (this.hp == 0)
				System.out.println("CL4P-TP " + this.name + " took " + (amount - this.adr) + " points of damage and die !");
			else
				System.out.println("CL4P-TP " + this.name + " took " + (amount - this.adr) + " points of damage !");
		}
	}
	
	public void beRepair(int amount)
	{
		if (this.ep == 0 || this.ep - 25 < 0)
			System.out.println("CL4P-TP " + name + " is out of energy.");
		else
		{
			if (amount <= 0)
				return ;
			if (this.hp == this.maxhp)
				System.out.println("CL4P-TP " + this.name + " have already full HP");
			else
			{
				this.hp += amount;
				if (this.hp > this.maxhp)
					this.hp = this.maxhp;
				System.out.println("CL4P-TP " + this.name + " gain " + amount + " HP.");
			}
			this.ep -= 25;
			if (this.ep < 0)
				this.ep = 0;
		}
	}
	
	public void epRegain(int amount)
	{
		if (amount <= 0)
			return ;
		if (this.ep == this.maxep)
			System.out.println("CL4P-TP " + this.name + " have already full EP");
		else
		{
			this.ep += amount;
			if (this.ep > this.maxep)
				this.ep = this.maxep;
			System.out.println("CL4P-TP " + this.name + " gain " + amount + " EP.");
		}
	}

}
