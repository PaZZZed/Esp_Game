package atl.esp.model;

import java.util.Set;

/**
 *
 * @author Paul et Léopold
 */
public class Match {

    private final Pair pair;
    private final Image image;

    /**
     * constructor of Match
     *
     * @param img
     */
    public Match(Image img) {
        pair = new Pair();
        image = img;
    }

    /**
     * check if the image has label
     *
     * @param str
     * @return
     */
    public boolean hasLabel(String str) {
        return image.hasLabel(str);
    }

    /**
     * if 2 words are the same the game is "over"
     *
     * @return
     */
    public boolean isOver() {
        return pair.getWords(1).disjoint(pair.getWords(2));
    }

    /**
     * get the mutual word
     *
     * @return
     */
    public Set<String> getMutual() {
        return pair.getMutual();
    }

    /**
     * et the current image
     *
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * check if the word has already been proposed
     *
     * @param str
     * @param i
     * @return
     */
    public boolean isPreviousProposal(String str, int i) {
        return pair.isPreviousProposal(str, i);
    }

    /**
     * propose a word
     *
     * @param str
     * @param i
     * @return
     */
    public boolean propose(String str, int i) {
        return pair.propose(str, i);
    }

    /**
     * get list of words from player 1 or 2
     *
     * @param i
     * @return
     */
    public Words words(int i) {
        return pair.getWords(i);
    }

    /*public void close() {
        if (isOver()) {
          //  exit(0);
        }
    }*/
}
