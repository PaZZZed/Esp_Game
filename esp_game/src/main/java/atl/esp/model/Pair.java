package atl.esp.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Paul et Léopold
 */
public class Pair {

    private final Words player1;
    private final Words player2;

    /**
     * constructor of pair with the 2 list of words for each player
     */
    public Pair() {
        player1 = new Words();
        player2 = new Words();

    }

    /**
     * check if the 2 players have diffrent lists
     *
     * @return if diffrent return true
     */
    public boolean disjoint() {
        for (String player1 : player1.getWords()) {
            for (String player2 : player2.getWords()) {
                if (player1.equals(player2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * check if the 2 player have the same string in their lists
     *
     * @return if yes return the string in common
     */
    public Set<String> getMutual() {
        Set set = new HashSet();
        for (String player1word : this.player1.getWords()) {
            for (String player2word : this.player2.getWords()) {
                if (player1word.equals(player2word)) {
                    set.add(player1word);
                    return set;
                }
            }
        }
        return null;
    }

    /**
     * check if the 2 players have already the proposed word in their list
     *
     * @param word
     * @param id
     * @return
     */
    public boolean isPreviousProposal(String word, int id) {
        if (id == 1) {
            return player1.getWords().contains(word);
        } else if (id == 2) {
            return player2.getWords().contains(word);

        }
        return false;

    }

    /**
     * propose to add a string to the list of the player
     *
     * @param word
     * @param id
     * @return
     */
    public boolean propose(String word, int id) {
        if (id == 1 && !isPreviousProposal(word, id)) {
            player1.addWord(word);
            return true;
        } else if (id == 2 && !isPreviousProposal(word, id)) {
            player2.addWord(word);
            return true;
        }
        return false;

    }

    /**
     * get the Words of the player
     *
     * @param id of the player
     * @return words of player
     */
    public Words getWords(int id) {
        if (id == 1) {
            return player1;
        } else if (id == 2) {
            return player2;
        }
        return null;
    }

}
