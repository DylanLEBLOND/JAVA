package j03.dleblon;

import java.util.Random;

public class FragTrap extends ClapTrap
{
		public FragTrap(String name)
	{
		super(name, 100, 100, 100, 100, 1, 30, 20, 5);
		System.out.println("FR4G-TP named " + this.name + " has been created.");
	}
	
	public void vaulthunter_dot_exe(final String target)
	{
		String[] attacks = {"Big Bang", "Draco Meteor", "Getsuga", "Shidori", "Trempette"};
		int[] damages = {0, 25, 50, 75, 100};
		Random 	rand = new Random();
		
		if (this.ep == 0 || this.ep - 25 < 0)
			System.out.println("FR4G-TP " + name + " is out of energy.");
		else
		{
			System.out.println("FR4G-TP " + this.name + " use " + attacks[rand.nextInt(5)] + " on " + target + " causing " + damages[rand.nextInt(5)]+ " points of damage !");
			this.ep -= 25;
			if (this.ep < 0)
				this.ep = 0;
		}
	}
}
