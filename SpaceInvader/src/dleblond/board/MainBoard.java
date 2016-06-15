package dleblond.board;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainBoard extends StateBasedGame
{
	public MainBoard()
	{
		super("Space Invader");
	}
	
	public void initStatesList(GameContainer container) throws SlickException
	{
		addState(new MenuBoard());
		addState(new GameBoard());
	}
	
	public static void main(String[] args) throws SlickException
	{
		new AppGameContainer(new MainBoard(), 1024, 640, false).start();
	}
}
