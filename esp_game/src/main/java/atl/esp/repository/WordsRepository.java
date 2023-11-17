package atl.esp.repository;

import atl.esp.dto.WordsDto;
import atl.esp.exception.RepositoryException;
import atl.esp.jdbc.WordsDao;
import java.util.List;

/**
 *
 * @author Paul et Léopold
 */
public class WordsRepository implements Repository<Integer, WordsDto> {

    private final WordsDao dao;

    public WordsRepository() throws RepositoryException {
        this.dao = WordsDao.getInstance();
    }

    WordsRepository(WordsDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer add(WordsDto item) throws RepositoryException {
        return dao.insert(item);
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        this.dao.delete(key);
    }

    @Override
    public List<WordsDto> getAll() throws RepositoryException {
        return this.dao.selectAll();
    }

    @Override//with update
    public WordsDto get(Integer key) throws RepositoryException {
        WordsDto item = this.dao.select(key);
        return item;
    }

    @Override//update
    public boolean contains(Integer key) throws RepositoryException {
        WordsDto item = dao.select(key);
        return item != null;
    }

    public List<WordsDto> getDtos(String path) throws RepositoryException {
        return dao.getDtos(path);
    }

}
