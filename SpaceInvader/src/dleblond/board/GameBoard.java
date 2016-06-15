package dleblond.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import dleblond.guns.Gun;
import dleblond.objects.Asteroids;
import dleblond.objects.BlackShip;
import dleblond.objects.Bullets;
import dleblond.objects.GreyAsteroid;
import dleblond.objects.Objects;
import dleblond.objects.OrangeShip;
import dleblond.objects.Ship;
import dleblond.objects.WhiteShip;

public class GameBoard extends BasicGameState
{
	public static final int			iD = 2;
	private GameContainer 			container;
	private StateBasedGame			state;
	private Music					music;
	private TiledMap 				map;
	private WhiteShip				wship;
	private ArrayList<Ship>			enemies;
	private ArrayList<Asteroids>	obstacles;
	private Date					tic;
	private int						[]dep;
	private boolean					move = false;
	private boolean					shoot = false;
	private boolean					eshoot = true;

    public void init(GameContainer container, StateBasedGame state) throws SlickException
    {
    	this.container = container;
    	this.state = state;
    	this.music = new Music("ressources/sounds/POPHT2.wav");
    	this.music.loop();
    	this.map = new TiledMap("ressources/maps/map1.tmx");
    	//this.imgGO = new Image("ressources/maps/gameover.png");
    	initships();
    }
    
    private void initships() throws SlickException
    {
    	this.tic = new Date();
    	this.dep = new int[4];
    	this.wship = new WhiteShip(0, this.container.getHeight() / 2 - 24);
    	this.enemies = new ArrayList<Ship>();
    	this.obstacles = new ArrayList<Asteroids>();
    }
    
    private boolean isPrem(int nb)
    {
    	int i = 2;
    	
    	while (i < Math.sqrt(nb))
    	{
    		if ((nb / i) * i == nb)
    			return (false);
    		++i;
    	}
    	return (true);
    }
    
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
    {
    	Date current = new Date();
    	
    	if (current.getTime() - this.tic.getTime() > 50)
    	{
    		int i = 0;
    		int nb;
    		Random rand = new Random();
    		Gun gun;

    		nb = rand.nextInt(5);
    		while (this.obstacles.size() < 10 && this.isPrem(rand.nextInt(100) + 2) && i++ < nb)
    			this.obstacles.add(new GreyAsteroid(this.container.getWidth() - 32, rand.nextInt(this.container.getHeight() - 30)));
    		while (this.enemies.size() < 10 && this.isPrem(rand.nextInt(1000000) + 2) && i++ < nb)
    		{
    			if (rand.nextBoolean())
    				this.enemies.add(new BlackShip(this.container.getWidth() - 28, rand.nextInt(this.container.getHeight() - 24)));
    			else
    				this.enemies.add(new OrangeShip(this.container.getWidth() - 28, rand.nextInt(this.container.getHeight() - 24)));
    		}
    		gun = this.wship.getGun();
    		if (this.shoot)
    			gun.shoot(this.wship.x + 28, this.wship.y + 4);
    		gun.mooveAll(16, 16, this.container.getWidth(), this.container.getHeight());
    		this.mooveEnemy(16, 16);
    		this.wship.setGun(gun);
    		if (this.move)
    		{
    			if (this.dep[3] == 1 && this.wship.x > 0)
    				this.wship.x -= 16;
    			if (this.dep[2] == 1 && this.wship.x < this.container.getWidth() - 28)
    				this.wship.x += 16;
    			if (this.dep[0] == 1 && this.wship.y > 0)
    				this.wship.y -= 16;
    			if (this.dep[1] == 1 && this.wship.y < this.container.getHeight() - 24)
    				this.wship.y += 16;
    		}
    		this.checkCollisions();
    		this.tic = current;
    		if (this.eshoot)
    			this.eshoot = false;
    		else
    			this.eshoot = true;
    	}
    }
    
    private	void mooveEnemy(int x, int y) throws SlickException
    {
    	int 		i = 0;
    	Ship 		tmp;
    	Gun 		gun = new Gun("null", 0, false);
    	Asteroids 	aste;

    	while (i < this.enemies.size())
    	{
    		tmp = this.enemies.get(i);
    		gun = tmp.getGun();
    		if (tmp.isAlive())
    		{
    			if(tmp instanceof BlackShip)
    			{
    				if (tmp.dy == 1)
    					tmp.y += y;
    				else
    					tmp.y -= y;
    				if (tmp.y - y < 0)
    					tmp.dy = 1;
    				if (tmp.y + y > this.container.getHeight() - 24)
    					tmp.dy = -1;
    			}
    			if (tmp instanceof OrangeShip)
    			{
    				if (tmp.dx == 1)
    					tmp.x += x;
    				else
    					tmp.x -= x;
    				if (tmp.x - x < this.container.getWidth() / 2)
						tmp.dx = 1;
					if (tmp.x + x > this.container.getWidth() - 28)
						tmp.dx = -1;
    				if (tmp.dy == 1)
    					tmp.y += y;
    				else
    					tmp.y -= y;
    				if (tmp.y - y < 0)
    					tmp.dy = 1;
    				if (tmp.y + y > this.container.getHeight() - 24)
    					tmp.dy = -1;
    			}
    			if (this.eshoot)
    				gun.shoot(tmp.x, tmp.y + 4);
    		}
    		gun.mooveAll(-16, -16, this.container.getWidth(), this.container.getHeight());
			tmp.setGun(gun);
    		this.enemies.set(i, tmp);
    		++i;
    	}
    	i = 0;
    	while (i < this.obstacles.size())
    	{
    		aste = this.obstacles.get(i);
    		aste.x -= x;
    		if (aste.x < 0)
    			this.obstacles.remove(i--);
    		else
    			this.obstacles.set(i, aste);
    		++i;
    	}
    }
    
    private void checkCollisions() throws SlickException
    {
    	int 		i = 0;
    	Ship		tmp;
    	Asteroids	aste;
    	Gun			gun1;
    	Gun			gun2;

    	while (i < this.enemies.size())
    	{
    		tmp = this.enemies.get(i);
    		gun1 = this.wship.getGun();
    		gun2 = tmp.getGun();
    		this.wship.checkCollision(tmp);
    		gun1.checkBulletCollision(tmp);
    		gun2.checkBulletCollision(this.wship);
    		this.wship.setGun(gun1);
    		tmp.setGun(gun2);
    		if (!tmp.isAlive())
    		{
    			this.wship.addScore(20);
    			this.enemies.remove(i);
    			i = 0;
    		}
    		else
    			this.enemies.set(i, tmp);
    		++i;
    	}
    	i = 0;
    	while (i < this.obstacles.size())
    	{
    		aste = this.obstacles.get(i);
    		gun1 = this.wship.getGun();
    		this.wship.checkCollision(aste);
    		gun1.checkBulletCollision(aste);
    		aste.checkCollision(this.wship);
    		if (!aste.isAlive())
    		{
    			this.wship.addScore(10);
    			this.obstacles.remove(i);
    			i = 0;
    		}
    		else
    			this.obstacles.set(i, aste);
    		++i;
    	}
    }

    private void gameover(Graphics g, int x, int y) throws SlickException
    {
    	//g.drawImage(this.imgGO, (float)x, (float)y);
    	this.initships();
    	this.state.enterState(MenuBoard.iD);
    }
    
    public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
    {
    	this.map.render(0, 0);
    	g.drawString("HP : " + this.wship.getHp() + " SCORE : " + this.wship.getScore(), 100, 10);
    	g.drawAnimation(this.wship.getAnim(), this.wship.x, this.wship.y);
    	drawAllObjects(g);
    	if (!this.wship.isAlive())
    		gameover(g, this.container.getWidth() / 2, this.container.getHeight() / 2);
    }
    
    private void drawAllObjects(Graphics g) throws SlickException
    {
    	int 				i = 0, j;
    	Objects				tmp;
    	Ship				enemy;
    	ArrayList<Bullets> 	buls;
    	Asteroids			aste;
    	
    	buls = this.wship.getGun().getAmmo();
    	while (i < buls.size())
    	{
    		tmp = buls.get(i);
    		if (tmp.isAlive() && ((Bullets)(tmp)).isActif())
    			g.drawAnimation(((Bullets)(tmp)).getAnim(), tmp.x, tmp.y);
    		++i;
    	}
    	i = 0;
    	while (i < this.enemies.size())
    	{
    		enemy = this.enemies.get(i);
    		if (enemy.isAlive() && enemy instanceof BlackShip)
    			g.drawAnimation(((BlackShip)(enemy)).getAnim(), enemy.x, enemy.y);
    		else if (enemy.isAlive() && enemy instanceof OrangeShip)
    			g.drawAnimation(((OrangeShip)(enemy)).getAnim(), enemy.x, enemy.y);
    		j = 0;
    		buls = enemy.getGun().getAmmo();
    		while (j < buls.size())
        	{
        		tmp = buls.get(j);
        		if (tmp.isAlive() && ((Bullets)(tmp)).isActif())
        			g.drawAnimation(((Bullets)(tmp)).getAnim(), tmp.x, tmp.y);
        		++j;
        	}
    		++i;
    	}
    	i = 0;
    	while (i < this.obstacles.size())
    	{
    		aste = this.obstacles.get(i);
    		g.drawAnimation(aste.getAnim(), aste.x, aste.y);
    		++i;
    	}
    }
    
    public void keyPressed(int key, char c)
    {
    	if (Input.KEY_UP == key)
    		this.dep[0] = 1;
    	if (Input.KEY_DOWN == key)
    		this.dep[1] = 1;
    	if (Input.KEY_RIGHT == key)
    		this.dep[2] = 1;
    	if (Input.KEY_LEFT == key)
    		this.dep[3] = 1;
    	if (!this.shoot && Input.KEY_SPACE == key)
    		this.shoot = true;
    	if (!this.move && Input.KEY_LEFT == key || Input.KEY_RIGHT == key
    		|| Input.KEY_UP == key || Input.KEY_DOWN == key)
    		this.move = true;
    	if (Input.KEY_SUBTRACT == key && this.music.getVolume() > 0)
    		this.music.setVolume(this.music.getVolume() - 0.1f);
    	if (Input.KEY_ADD == key && this.music.getVolume() < 1)
    		this.music.setVolume(this.music.getVolume() + 0.1f);
    	if (Input.KEY_DIVIDE == key && this.music.getVolume() > 0)
    		this.music.setVolume(0);
    }
    
    public void keyReleased(int key, char c)
    {
    	if (Input.KEY_ESCAPE == key)
    		this.container.exit();
    	if (Input.KEY_UP == key)
    		this.dep[0] = 0;
    	if (Input.KEY_DOWN == key)
    		this.dep[1] = 0;
    	if (Input.KEY_RIGHT == key)
    		this.dep[2] = 0;
    	if (Input.KEY_LEFT == key)
    		this.dep[3] = 0;
    	if (Input.KEY_SPACE == key)
    		this.shoot = false;
    	if (this.dep[0] == 0 && this.dep[1] == 0
    		&& this.dep[2] == 0 && this.dep[3] == 0)
    		this.move = false;
    }
    
    public int getID()
    {
        return (iD);
    }
}