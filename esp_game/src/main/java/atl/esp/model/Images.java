package atl.esp.model;

import atl.esp.exception.RepositoryException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paul et Léopold
 */
public class Images {

    private List<Image> images;
    private Image current;

    public Images() throws RepositoryException {
        images = new ArrayList<>();
        File[] dirImage = new File("src/main/resources/images").listFiles();//path "src/main/java/atl/esp/images"
        for (int i = 0; i < dirImage.length; i++) {
            images.add(new Image(dirImage[i], i));
        }

      
    }

    public void initialization() {
        current = images.get(0);

    }

    public Iterator<Image> iterator() {//pour next image faire iterator.next...
        return images.iterator();
    }

    public Image getCurrent() {
        return current;
    }

    public void next() {
        if (this.current.getId() + 1 <= 10) {
            this.current = this.images.get(this.current.getId() + 1);
        } else {
            this.current = this.images.get(0);
        }
        /*
        if (iterator().hasNext()) {
            current = iterator().next();
        } else {
            current = images.get(0);
        }*/
    
    }
}
