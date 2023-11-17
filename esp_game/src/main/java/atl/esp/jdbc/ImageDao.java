package atl.esp.jdbc;

import atl.esp.dto.ImageDto;
import atl.esp.exception.RepositoryException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul et Léopold
 */
public class ImageDao implements Dao<Integer, ImageDto> {

    private final Connection connect;

    private ImageDao() throws RepositoryException {
        connect = DBManager.getInstance().getConnection();
    }

    public static ImageDao getInstance() throws RepositoryException {
        return ImageDaoHolder.getInstance();
    }

    @Override
    public Integer insert(ImageDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("element non present");
        }
        Integer id = 0;
        String sql = "INSERT INTO IMAGES(path) values(?)";
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return id;
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("pas d'element a supprimer");
        }
        String sql = "DELETE FROM IMAGES WHERE id = ?";
        try (Statement stmt = connect.createStatement()) {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(ImageDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("pas d'element a update");
        }
        String sql = "UPDATE IMAGES SET path=? where id=?";
        try (Statement stmt = connect.createStatement()) {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<ImageDto> selectAll() throws RepositoryException {
        List<ImageDto> dtoList = new ArrayList<>();
        String sql = "SELECT id,path FROM IMAGES";
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ImageDto dto = new ImageDto(rs.getInt(1), rs.getString(2));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtoList;
    }

    @Override
    public ImageDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id,path FROM IMAGES WHERE  id = ?";
        ImageDto dto = null;
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            while (rs.next()) {
                dto = new ImageDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class ImageDaoHolder {

        private static ImageDao getInstance() throws RepositoryException {
            return new ImageDao();
        }
    }
}
