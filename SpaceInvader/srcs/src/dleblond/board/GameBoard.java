package dleblond.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import dleblond.guns.Gun;
import dleblond.ships.ActifShip;
import dleblond.ships.BlackShip;
import dleblond.ships.Bullets;
import dleblond.ships.OrangeShip;
import dleblond.ships.Ship;
import dleblond.ships.WhiteShip;

public class GameBoard extends BasicGameState
{
	public static final int			iD = 2;
	private GameContainer 			container;
	private StateBasedGame			state;
	private Music					music;
	private TiledMap 				map;
	private Image					imgGO;
	private WhiteShip				wship;
	private ArrayList<ActifShip>	enemies;
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
    	Random	rand = new Random();
    	int 	i = 1;
    	
    	this.tic = new Date();
    	this.dep = new int[4];
    	this.wship = new WhiteShip(0, this.container.getHeight() / 2 - 24);
    	this.enemies = new ArrayList<ActifShip>();
    	while (i < 4)
    		this.enemies.add(new BlackShip(this.container.getWidth() - 28, (this.container.getHeight() / 4 * i++) - 24));
    	while (i++ < 10)
    		this.enemies.add(new OrangeShip(rand.nextInt(this.container.getWidth()) - 28, rand.nextInt(this.container.getHeight()) - 24));
    }
    
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
    {
    	Date current = new Date();
    	
    	if (current.getTime() - this.tic.getTime() > 50)
    	{
    		Gun gun;
    		
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
    	ActifShip 	tmp;
    	Gun 		gun = new Gun("null", 0, false);
    	
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
    }
    
    private void checkCollisions() throws SlickException
    {
    	int 		i = 0;
    	ActifShip	tmp;
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
    		}
    		else
    			this.enemies.set(i, tmp);
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
    	Ship				tmp;
    	ActifShip			enemy;
    	ArrayList<Bullets> 	buls;
    	
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
    		container.exit();
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