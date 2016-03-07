package dleblond.board;

import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import dleblond.ships.BlackShip;
import dleblond.ships.Bullets;
import dleblond.ships.OrangeShip;
import dleblond.ships.Ship;
import dleblond.ships.WhiteShip;

public class GameBoard extends BasicGameState
{
	public static final int	iD = 2;
	private GameContainer 	container;
	private StateBasedGame	state;
	private Music			music;
	private TiledMap 		map;
	private Image			imgGO;
	private Bullets			bullet;
	private WhiteShip		wship;
	private ArrayList<Ship>	enemies;
	private Date			tic;
	private int				[]dep;
	private boolean			move = false;
	
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
    	this.wship = new WhiteShip(0, this.container.getHeight() / 2 - 64);
    	this.bullet = new Bullets(64, this.container.getHeight() / 2 - 32);
    	this.enemies = new ArrayList<Ship>();
    	this.enemies.add(new BlackShip(this.container.getWidth() - 64, this.container.getHeight() / 2 - 64));
    }
    
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
    {
    	Date current = new Date();
    	
    	if (current.getTime() - this.tic.getTime() > 50)
    	{
    		if (this.move)
    		{
    			if (this.dep[3] == 1 && this.wship.x > 0)
    				this.wship.x -= 32;
    			if (this.dep[2] == 1 && this.wship.x < this.container.getWidth() - 64)
    				this.wship.x += 32;
    			if (this.dep[0] == 1 && this.wship.y > 0)
    				this.wship.y -= 32;
    			if (this.dep[1] == 1 && this.wship.y < this.container.getHeight() - 64)
    				this.wship.y += 32;
    			this.checkCollisions();
    		}
    		this.tic = current;
    	}
    }
    
    private void checkCollisions()
    {
    	int 	i = 0;
    	Ship	tmp;
    	
    	while (i < this.enemies.size())
    	{
    		tmp = this.enemies.get(i);
    		System.out.println("W: x = " + this.wship.x + " | y = " + this.wship.y + " | width = " + this.wship.width + " | height = " + this.wship.height);
    		System.out.println("E: x = " + tmp.x + " | y = " + tmp.y + " | width = " + tmp.width + " | height = " + tmp.height);
    		this.wship.checkCollision(tmp);
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
    	g.drawAnimation(this.wship.getAnim(), this.wship.x, this.wship.y);
    	g.drawAnimation(this.bullet.getAnim(), this.bullet.x, this.bullet.y);
    	drawAllObjects(g);
    	if (!this.wship.IsAlive())
    		gameover(g, this.container.getWidth() / 2, this.container.getHeight() / 2);
    }
    
    private void drawAllObjects(Graphics g) throws SlickException
    {
    	int 	i = 0;
    	Ship	tmp;
    	
    	while (i < this.enemies.size())
    	{
    		tmp = this.enemies.get(i);
    		if (tmp.IsAlive() && tmp instanceof BlackShip)
    			g.drawAnimation(((BlackShip)(tmp)).getAnim(), tmp.x, tmp.y);
    		else if (tmp.IsAlive() && tmp instanceof OrangeShip)
    			g.drawAnimation(((OrangeShip)(tmp)).getAnim(), tmp.x, tmp.y);
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
    	if (this.dep[0] == 0 && this.dep[1] == 0
    		&& this.dep[2] == 0 && this.dep[3] == 0)
    		this.move = false;
    }
    
    public int getID()
    {
        return (iD);
    }
}