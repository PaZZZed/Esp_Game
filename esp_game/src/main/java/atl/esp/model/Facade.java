package atl.esp.model;

//import atl.esp.dto.ImageDto;
//import atl.esp.dto.WordsDto;
import atl.esp.dto.ImageDto;
import atl.esp.exception.RepositoryException;
import atl.esp.presenter.Observer;
import java.util.List;

/**
 * Facade of the Extrasensory perception game.
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/ESP_game"> Wikipedia</a>
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Facade"> Link to a description of the
 * design pattern facade  </a>
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">
 * Wikipedia</a>
 *
 * @author jlc
 */
public interface Facade {

    /**
     * Starts the first match of the game. The first image is selected.
     */
    void start();

    /**
     * Return the player id. The first player has 1 for id and the second player
     * has 2 for id. The method can be overwrote to change this logic.
     *
     * @return the player id.
     */
    int nextPlayerId();

    /**
     * Returns the list of words send by the user for the current image.
     *
     * @param playerId player's id.
     * @return the list of words send by the user for the current image.
     */
    //WordsDto getWords(int playerId);
    /**
     * Return the path of the current image.
     *
     * @return the path of the current image.
     */
    //ImageDto getImage();
    /**
     * Return true if the word given is a label for the current image and false
     * otherwise.
     *
     * @param word word given by the player.
     * @return true if the word given is a label for the current image and false
     * otherwise.
     */
    boolean hasLabel(String word);

    /**
     * Sends the word given by the player of the given id.
     *
     * @param word word to check.
     * @param playerId player's id.
     * @throws RepositoryException if the database can't process a query.
     */
    void propose(String word, int playerId) throws RepositoryException;

    /**
     * Return true if the word is already proposed by the user for the current
     * image and false otherwise.
     *
     * @param word word to check.
     * @param playerId player's id.
     * @return true if the word is already proposed by the user for the current
     * image and false otherwise.
     */
    boolean isPreviousProposal(String word, int playerId);

    /**
     * Return true if there is no more image to display and the last match is
     * over.
     *
     * @return true if there is no more image to display and the last match is
     * over.
     */
    boolean isOver();

    /**
     * Adds an observer to the set of observers for this object, provided that
     * it is not the same as some observer already in the set. The order in
     * which notifications will be delivered to multiple observers is not
     * specified. See the class comment.
     *
     * @param observer an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    void addObserver(Observer observer);

    /**
     * Notify all of its observers.
     * <p>
     * Each observer has its {@code update} method called with two arguments:
     * this observable object and {@code null}. In other words, this method is
     * equivalent to:
     * <blockquote>{@code
     * notifyObservers(null)}</blockquote>
     *
     */
    void notifyObservers();

    /**
     * Notify all of its observers.
     * <p>
     * Each observer has its {@code update} method called with two arguments:
     * this observable object and the {@code arg} argument.
     *
     * @param arg any object.
     */
    //void notifyObservers(Object arg);

    public void nextTurn();

    public List<String> getWordss(int id);

    public List<String> getLabels();
      public ImageDto getImage();
}
