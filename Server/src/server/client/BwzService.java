package server.client;

import server.shared.Puzzle;
import server.shared.Rank;
import server.shared.WWWordzException;

public interface BwzService {

	/**
	 * Register user with nick and password for current round.
	 * @param nick - of user to register
	 * @param password - of user to register
	 * @return time in seconds for next round
	 * @throws WWWordzException
	 */
	long register(java.lang.String nick, java.lang.String password) throws WWWordzException;

	/**
	 * Get table of current round.
	 * @return - return puzzle of current round if game has started 
	 * @throws WWWordzException - if game has not started 
	 */
	Puzzle getPuzzle() throws WWWordzException;

	/**
	 * Set number of points obtained by user in current round.
	 * @param nick - of user
	 * @param points - to set
	 * @throws WWWordzException - if game is not over or reporting has ended
	 */
	void setPoints(java.lang.String nick, int points) throws WWWordzException;

	/**
	 * Set number of points obtained by user in current round.
	 * @return list of ranks
	 * @throws WWWordzException - if players can still report values
	 */
	java.util.List<Rank> getRanking() throws WWWordzException;

}