package atl.esp.model;

import atl.esp.dto.ImageDto;
import atl.esp.exception.RepositoryException;
import atl.esp.presenter.Observable;
import atl.esp.presenter.Observer;
import atl.esp.repository.ImageRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paul et Léopold
 */
public class Game implements Facade, Observable {

    private final List<Observer> observers;
    private final Images images;
    private Match match;

    //private ImageRepository imageRepository;
    public Game() throws RepositoryException {
        observers = new ArrayList();
        images = new Images();//path deja present

        //imageRepository = new ImageRepository();//inutile
    }

    @Override
    public void start() {
        images.initialization();
        match = new Match(images.getCurrent());
        notifyObservers();
    }

    @Override
    public ImageDto getImage() {
        return new ImageDto(this.images.getCurrent().getId(), images.getCurrent().getPath());
    }

    /*  public WordsDto getWords(int id) {
//pas qu'un seul mot!
        return new WordsDto(id,images.getCurrent().getPath(),match.getMutual().);
    }
//Integer key, String imageId, String text, String dateAdded
     */
    @Override
    public List<String> getWordss(int id) {//pour remplacer le dto ->
        Words words = match.words(id);
        List<String> labels = new ArrayList<>();
        for (String word : words.getWords()) {
            labels.add(word);
        }
        return labels;
    }

    @Override
    public List<String> getLabels() {
        List<String> labels = null;
        try {
            labels = images.getCurrent().getLabels();
        } catch (RepositoryException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);//
        }
        return labels;
    }

    @Override
    public boolean hasLabel(String word) {
        return images.getCurrent().hasLabel(word);
    }

    @Override
    public void propose(String word, int playerId) throws RepositoryException {
        match.propose(word, playerId);
        notifyObservers();
    }

    @Override
    public boolean isPreviousProposal(String word, int playerId) {
        return match.isPreviousProposal(word, playerId);
    }

    @Override
    public boolean isOver() {
        return match.isOver();

    }

    @Override
    public int nextPlayerId() {

        return 1;
    }

    @Override
    public void nextTurn() {
        if (!this.match.getMutual().isEmpty()) {
            try {
                this.images.getCurrent().save(this.match.getMutual().toString());
            } catch (RepositoryException e) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);//
            }
        }
        this.images.next();
        System.out.println(this.images.getCurrent());
        this.match = new Match(this.images.getCurrent());
        notifyObservers();
    }

  @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }

    }

    @Override
    public void notifyObservers() {
        observers.forEach((obs) -> {
            obs.update(this);
        });
    }

    @Override
    public void removeObserver(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }

    }

}
