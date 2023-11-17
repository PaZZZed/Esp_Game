package atl.esp.presenter;

import atl.esp.model.Facade;

/**
 * The presenter acts upon the model and the view. It retrieves data from
 * repositories (the model), and formats it for display in the view.
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">
 * Wikipedia</a>
 *
 * @author jlc
 */
public interface Presenter extends Observer {

    /**
     * Initialize the model and the view.
     */
    void initialize();

    /**
     * Quits the model (closes all connections, files,...) and the view.
     */
    void quit();

    /**
     * When a user press the send button, the send method is called. The send
     * method check the word given (is null, is empty) and asks the model if
     * this word is invalid (the word has already been send, the word is a
     * label) or valid.
     *
     * @param word the word given by the user.
     */
    void send();

    @Override
    void update(Facade observable);

}
