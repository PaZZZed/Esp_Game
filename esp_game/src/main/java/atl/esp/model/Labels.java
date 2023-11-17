 package atl.esp.model;

import atl.esp.dto.WordsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Paul et Léopold
 */
public class Labels {

    private final List<String> labels;

    public Labels(List<WordsDto> wordsDtos) {

        this.labels = new ArrayList<>();
        wordsDtos.forEach((l) -> {
            this.labels.add(l.getText());
        });
    }

    public boolean add(String str) {
        labels.add(str);
        return true;
    }

    public List<String> getElements() {//getLabels

        return labels;
    }

    public boolean contains(String str) {
        return labels.contains(str);
    }

    /*public void addFresh(Set<String> set) {
        
    }

    public Set<String> validFresh() {

        return null;
    }*/
}
