package atl.esp.model;

import atl.esp.dto.WordsDto;
import atl.esp.exception.RepositoryException;
import atl.esp.repository.WordsRepository;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Paul et Léopold
 */
public class Image {

    private final String path;
    private Labels label;
    private final int id;
    private final WordsRepository wordRepository;

    public Image(File file, int id) throws RepositoryException {
        path = file.getPath();
        wordRepository = new WordsRepository();
        label = new Labels(wordRepository.getDtos(path));
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Labels getLabel() {
        return label;
    }

    public List<String> getLabels() throws RepositoryException {

        return label.getElements();//getLabels
    }

    public void addLabel(String str) throws RepositoryException {
        label.add(str);
        save(str);//va apres ajout save le nouveau word dans la db
    }

    public boolean hasLabel(String str) {
        return label.contains(str);
    }

    /* public void addFreshLabels(Set<String> set) {
        label.addFresh(set);
    }*/
    //hash + equals 
    /* public void save(String word) throws RepositoryException {
        String date = new Date().toString();
        WordsDto wordsDto = new WordsDto(label.getElements().size()-1, path, word, date);
        Integer add = wordRepository.add(wordsDto);
        //addRefresh();
    }*/
    public void save(String str) throws RepositoryException {
        String date = new Date().toString();
        WordsDto labelDto = new WordsDto(label.getElements().size() - 1, path, str, date);
        wordRepository.add(labelDto);
        addRefresh();//update et mets que les label specifique a l'image 
    }

    public void addRefresh() throws RepositoryException {
        label = new Labels(wordRepository.getDtos(path));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.path);
        hash = 73 * hash + Objects.hashCode(this.label);
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.wordRepository);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Image other = (Image) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.wordRepository, other.wordRepository)) {
            return false;
        }
        return true;
    }

}
