package dleblond.board;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class MenuBoard extends BasicGameState
{
	private GameContainer			container;
	public final static int 		iD = 1;
	private StateBasedGame			state;
	private TiledMap 				map;
	private Image					start;
	private Music					music;

	public void init(GameContainer container, StateBasedGame state) throws SlickException
	{
		this.state = state;
		this.container = container;
		this.start = new Image("ressources/maps/start.png");
    	this.map = new TiledMap("ressources/maps/menu.tmx");
    	this.music = new Music("ressources/sounds/POPHT2.wav");
    	this.music.loop();
	}
	
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
		
	}
	
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{
		this.map.render(0, 0);
		this.start.draw(container.getWidth() / 2 - 205, container.getHeight() / 2 - 21);
	}
	
	public void keyPressed(int key, char c)
	{
		if (Input.KEY_ESCAPE == key)
    		this.container.exit();
		if (key == Input.KEY_ENTER)
			this.state.enterState(GameBoard.iD);
		if (Input.KEY_SUBTRACT == key && this.music.getVolume() > 0)
    		this.music.setVolume(this.music.getVolume() - 0.1f);
    	if (Input.KEY_ADD == key && this.music.getVolume() < 1)
    		this.music.setVolume(this.music.getVolume() + 0.1f);
    	if (Input.KEY_DIVIDE == key && this.music.getVolume() > 0)
    		this.music.setVolume(0);
	}
	
	public int getID()
	{
		return (iD);
	}
}
