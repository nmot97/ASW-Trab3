package server.game;

import server.game.Round;
import server.game.Round.Stage;
import server.shared.Puzzle;
import server.shared.Rank;
import server.shared.WWWordzException;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is a singleton and acts as a facade for other classes in this package. Methods in this class are delegated in instances of these classes.
 * @author José Paulo Leal
 *
 */
public class Manager extends java.lang.Object implements Runnable {
	
	static final java.util.concurrent.ScheduledExecutorService worker = Executors.newScheduledThreadPool(1);
	static final long INITIAL_TIME = 0L;
	static Manager manager = null;
	Round round;
	
	/** 
	 * Single instance of Manager;
	 * @return <
	 */
	public static Manager getInstance() {
		return manager;
	}
	
	/* (non-Javadoc)
	 * @see wwwordz.game.BwzService#run()
	 */
	public void run() {
		round = new Round();
	}
	
	/* (non-Javadoc)
	 * @see wwwordz.game.BwzService#timeToNextPlay()
	 */
	public long timeToNextPlay() {
		return round.getTimetoNextPlay();
	}
	
	/* (non-Javadoc)
	 * @see wwwordz.game.BwzService#register(java.lang.String, java.lang.String)
	 */
	public long register(java.lang.String nick, java.lang.String password) throws WWWordzException {
		worker.scheduleAtFixedRate(this,100,100,TimeUnit.MILLISECONDS);
		int index = nick.indexOf(' ');
		if ( round.roundPlayers.containsKey(nick) || index > -1 || nick.length() == 0 )
			throw new WWWordzException("Error: Invalid User");
		else
			return round.register(nick,password);
	}
	
	/* (non-Javadoc)
	 * @see wwwordz.game.BwzService#getPuzzle()
	 */
	public Puzzle getPuzzle() throws WWWordzException { 
		if ( round.getStatus() == Stage.play  )
			throw new WWWordzException("Error: Game Not Started");
		else
			return round.getPuzzle();
	}
	
	/* (non-Javadoc)
	 * @see wwwordz.game.BwzService#setPoints(java.lang.String, int)
	 */	
	public void setPoints(java.lang.String nick, int points) throws WWWordzException {
		if ( round.getStatus() == Stage.report )
			throw new WWWordzException("Error: Game Not Ended/Reporting Ended.");
		else
			round.setPoints(nick,points);
	}
	
	/* (non-Javadoc)
	 * @see wwwordz.game.BwzService#getRanking()
	 */
	public java.util.List<Rank> getRanking() throws WWWordzException {
		if ( round.getStatus() == Stage.ranking )
			throw new WWWordzException("Error: Players Can Still Report.");
		else
			return round.getRanking();
	}	
}
