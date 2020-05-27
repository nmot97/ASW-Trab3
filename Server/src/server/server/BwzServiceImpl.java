package server.server;

import server.client.BwzService;
import server.game.Manager;
import server.game.Round;
import server.shared.Configs;
import server.shared.Puzzle;
import server.shared.Rank;
import server.shared.WWWordzException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class BwzServiceImpl extends RemoteServiceServlet implements BwzService {
	
	static {
	    Round.setJoinStageDuration(Configs.JOIN_STAGE_DURATION);
	    Round.setPlayStageDuration(Configs.PLAY_STAGE_DURATION);
	    Round.setReportStageDuration(Configs.REPORT_STAGE_DURATION);
	    Round.setRankingStageDuration(Configs.RANKING_STAGE_DURATION);
	}
	
	public BwzServiceImpl() {}
	
	/**
	 * Register user with nick and password for current round.
	 * @param nick - of user to register
	 * @param password - of user to register
	 * @return time in seconds for next found
	 * @throws WWWordzException
	 */
	public long register(java.lang.String nick, java.lang.String password) throws WWWordzException {
		return Manager.getInstance().register(nick,password);
	}
	
	/**
	 * Get table of current round.
	 * @return - return puzzle of current round if game has started 
	 * @throws WWWordzException - if game has not started 
	 */
	public Puzzle getPuzzle() throws WWWordzException { 
		return Manager.getInstance().getPuzzle();
	}
	
	/**
	 * Set number of points obtained by user in current round.
	 * @param nick - of user
	 * @param points - to set
	 * @throws WWWordzException - if game is not over or reporting has ended
	 */	
	public void setPoints(java.lang.String nick, int points) throws WWWordzException {
		Manager.getInstance().setPoints(nick,points);
	}
	
	/**
	 * Set number of points obtained by user in current round.
	 * @return list of ranks
	 * @throws WWWordzException - if players can still report values
	 */
	public java.util.List<Rank> getRanking() throws WWWordzException {
		return Manager.getInstance().getRanking();
	}	

}
