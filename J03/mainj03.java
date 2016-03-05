package j03.dleblon;

public class mainj03
{
	public static void main(String[] args)
	{
		FragTrap f00 = new FragTrap("F00");
	
		System.out.println("*********** Exo 1 ***********");
		f00.rangedAttack("f01");
		f00.meleeAttack("f02");
		f00.meleeAttack("f03");
		f00.meleeAttack("f04");
		f00.meleeAttack("f05");
		f00.takeDamage(10);
		f00.takeDamage(150);
		f00.takeDamage(10);
		f00.epRegain(100);
		f00.beRepair(100);
		f00.vaulthunter_dot_exe("Dylan");
		f00.vaulthunter_dot_exe("Mathieu");
		f00.epRegain(75);
		
		System.out.println("*********** Exo 2 ***********");
		
		ScavTrap s00 = new ScavTrap("S00");
		
		s00.rangedAttack("s01");
		s00.meleeAttack("s02");
		s00.meleeAttack("s03");
		s00.meleeAttack("s04");
		s00.meleeAttack("s05");
		s00.takeDamage(10);
		s00.takeDamage(150);
		s00.takeDamage(10);
		s00.epRegain(100);
		s00.beRepair(100);
		s00.challengeNewcomer();
		s00.challengeNewcomer();
		s00.challengeNewcomer();
		s00.epRegain(100);
		
		System.out.println("*********** Exo 3 ***********");
		
		ClapTrap c00 = new ClapTrap("C00", 100, 100, 100, 100, 1, 50, 20, 5);
		
		c00.rangedAttack("s01");
		c00.meleeAttack("s02");
		c00.meleeAttack("s03");
		c00.meleeAttack("s04");
		c00.meleeAttack("s05");
		c00.takeDamage(10);
		c00.takeDamage(150);
		c00.takeDamage(10);
		c00.beRepair(100);
		c00.epRegain(100);
		
		System.out.println("*********** Exo 3 ***********");
		
		NinjaTrap n00 = new NinjaTrap("N00");
		
		n00.ninjaShoebox(f00);
		n00.ninjaShoebox(s00);
		n00.takeDamage(60);
		n00.ninjaShoebox(n00);
		n00.ninjaShoebox(c00);
	}
}
