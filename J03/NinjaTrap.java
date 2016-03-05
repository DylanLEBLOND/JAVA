package j03.dleblon;

public class NinjaTrap extends ClapTrap
{
	public NinjaTrap(String name)
	{
		super(name, 60, 60, 120, 120, 1, 60, 5, 0);
		System.out.println("NINJ4-TP named " + this.name + " has been created.");
	}
	
	public void ninjaShoebox(ClapTrap clap)
	{
		Object current = clap.getClass();
		
		if (current == FragTrap.class)
		{
			System.out.println("NINJ4-TRAP " + this.name + " recognized FR4G-TP !");
			clap.takeDamage(this.mad);
		}
		else if (current == ScavTrap.class)
		{
			System.out.println("NINJ4-TRAP " + this.name + " recognized SC4V-TP !");
			clap.takeDamage(this.mad + this.rad);
		}
		else if (current == NinjaTrap.class)
		{
			System.out.println("NINJ4-TRAP " + this.name + " recognized NINJ4-TP !");
			clap.beRepair(this.mad);
		}
		else
		{
			System.out.println("NINJ4-TRAP " + this.name + " recognized CL4P-TP !");
			clap.takeDamage(this.rad);
		}
	}
}
