package dleblond.board;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuBoard extends BasicGameState
{
	public final static int 		iD = 1;
	private StateBasedGame			state;
	private Image					screen;

	public void init(GameContainer container, StateBasedGame state) throws SlickException
	{
		this.state = state;
		this.screen = new Image("ressources/maps/menu.png");
	}
	
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
		
	}
	
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{
		this.screen.draw(0, 0, container.getWidth(), container.getHeight());
	}
	
	public void keyPressed(int key, char c)
	{
		if (key == Input.KEY_ENTER)
			this.state.enterState(GameBoard.iD);
	}
	
	public int getID()
	{
		return (iD);
	}
}
