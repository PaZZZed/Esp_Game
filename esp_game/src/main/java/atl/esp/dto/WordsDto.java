package atl.esp.dto;

import java.util.Objects;

/**
 *
 * @author Paul et Léopold
 */
public class WordsDto extends Dto<Integer> {

    private String imageId;

    private String text;

    private String dateAdded;


    public WordsDto(Integer key, String imageId, String text, String dateAdded) {
        super(key);
        this.imageId = imageId;
        this.text = text;
        this.dateAdded = dateAdded;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.imageId);
        hash = 47 * hash + Objects.hashCode(this.text);
        hash = 47 * hash + Objects.hashCode(this.dateAdded);
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
        final WordsDto other = (WordsDto) obj;
        if (!Objects.equals(this.imageId, other.imageId)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return Objects.equals(this.dateAdded, other.dateAdded);
    }

    @Override
    public String toString() {
        return "WordsDto{" + "imageId=" + imageId + ", text=" + text + ", dateAdded=" + dateAdded + '}';
    }

}
