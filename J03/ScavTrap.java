package j03.dleblon;

import java.util.Random;

public class ScavTrap extends ClapTrap
{	
	public ScavTrap(String name)
	{
		super(name, 100, 100, 50, 50, 1, 20, 15, 3);
		System.out.println("SC4V-TP named " + this.name + " has been created.");
	}
	
	public void challengeNewcomer()
	{
		String[] challenges = {"Hahahaha", "Hohohoho", "Hihihihi", "Hehehehe", "Huhuhuhu"};
		Random 	rand = new Random();
		
		if (this.ep == 0 || this.ep - 25 < 0)
			System.out.println("SC4V-TP " + name + " is out of energy.");
		else
		{
			System.out.println("SC4V-TP " + this.name + " succesfully releave " + challenges[rand.nextInt(5)] + " challenge!");
			this.ep -= 25;
			if (this.ep < 0)
				this.ep = 0;
		}
	}
}
