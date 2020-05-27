package server.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import server.shared.Puzzle;
import server.shared.Rank;
import server.shared.WWWordzException;

public interface BwzServiceAsync {

	/**
	 * Register user with nick and password for current round.
	 * @param nick - of user to register
	 * @param password - of user to register
	 * @return time in seconds for next found
	 * @throws WWWordzException
	 */
	void register(java.lang.String nick, java.lang.String password, AsyncCallback<Long> callback) throws WWWordzException;

	/**
	 * Get table of current round.
	 * @return - return puzzle of current round if game has started 
	 * @throws WWWordzException - if game has not started 
	 */
	void getPuzzle(AsyncCallback<Puzzle> callback) throws WWWordzException;

	/**
	 * Set number of points obtained by user in current round.
	 * @param nick - of user
	 * @param points - to set
	 * @throws WWWordzException - if game is not over or reporting has ended
	 */
	void setPoints(java.lang.String nick, int points, AsyncCallback<Void> callback) throws WWWordzException;

	/**
	 * Set number of points obtained by user in current round.
	 * @return list of ranks
	 * @throws WWWordzException - if players can still report values
	 */
	void getRanking(AsyncCallback<java.util.List<Rank>> callback) throws WWWordzException;

}