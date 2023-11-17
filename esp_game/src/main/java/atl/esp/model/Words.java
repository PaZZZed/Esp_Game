package atl.esp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Paul et Léopold
 */
public class Words {

    private final List<String> words;

    /**
     * create a Words object with a list of strings
     */
    public Words() {
        words = new ArrayList();
    }

    /**
     * add string to wordds list
     *
     * @param str
     */
    public void addWord(String str) {
        words.add(str);
    }

    /**
     * get the list of words
     *
     * @return
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * check if the 2 words are diffrent.
     *
     * @param word
     * @return
     */
    public boolean disjoint(Words word) {
        for (String player1 : words) {
            for (String player2 : word.getWords()) {
                if (player1.equals(player2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check what is the same string with 2 words lists
     *
     * @param word
     * @return
     */
    public Set<String> getMutual(Words word) {
        Set set = new HashSet();
        for (String player1 : words) {
            for (String player2 : word.getWords()) {
                if (player1.equals(player2)) {
                    set.add(player2);
                    return set;
                }
            }
        }
        return null;
    }
}
