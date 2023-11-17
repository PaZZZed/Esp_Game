package atl.esp.dto;

import java.util.Objects;

/**
 *
 * @author Paul et Léopold
 */
public class ImageDto extends Dto<Integer> {

    private String path;

    public ImageDto(Integer key) {
        super(key);
    }

    public ImageDto(Integer key, String path) {
        super(key);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.path);
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
        final ImageDto other = (ImageDto) obj;
        return Objects.equals(this.path, other.path);
    }

    @Override
    public String toString() {
        return "ImageDto: " + path;
    }

}
