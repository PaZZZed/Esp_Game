package atl.esp.repository;

import atl.esp.dto.ImageDto;
import atl.esp.exception.RepositoryException;
import atl.esp.jdbc.ImageDao;
import java.util.List;

/**
 *
 * @author paul et Léopold
 */
public class ImageRepository implements Repository<Integer, ImageDto> {

    private final ImageDao dao;

    public ImageRepository() throws RepositoryException {
        this.dao = ImageDao.getInstance();
    }

    ImageRepository(ImageDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer add(ImageDto item) throws RepositoryException {
        Integer key = item.getKey();
        if (contains(item.getKey())) {
            dao.update(item);
        } else {
            key = dao.insert(item);
        }
        return key;
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        this.dao.delete(key);
    }

    @Override
    public List<ImageDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public ImageDto get(Integer key) throws RepositoryException {
        ImageDto dto = this.dao.select(key);
        return dto;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        ImageDto dto = dao.select(key);
        return dto != null;
    }

}
