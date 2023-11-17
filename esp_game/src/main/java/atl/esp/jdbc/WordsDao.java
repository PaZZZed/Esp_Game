package atl.esp.jdbc;

import atl.esp.dto.WordsDto;
import atl.esp.exception.RepositoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul et Léopold
 */
public class WordsDao implements Dao<Integer, WordsDto> {

    private final Connection connect;

    private WordsDao() throws RepositoryException {
        connect = DBManager.getInstance().getConnection();
    }

    public static WordsDao getInstance() throws RepositoryException {
        return WordsDaoHolder.getInstance();
    }

    @Override
    public Integer insert(WordsDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        Integer id = 0;
        String sql = "INSERT INTO LABELS(imageId,text,dateAdded) values(?,?,?)";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, item.getImageId());
            ps.setString(2, item.getText());
            ps.setString(3, item.getDateAdded());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
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
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM LABELS WHERE id = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(WordsDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE LABELS SET imageId=? ,text=? , dateAdded=? where id=? ";
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, item.getImageId());
            pstmt.setString(2, item.getText());
            pstmt.setString(3, item.getDateAdded());
            pstmt.setInt(4, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<WordsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id,imageId,text,dateAdded FROM LABELS";
        List<WordsDto> dtoListe = new ArrayList<>();
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                WordsDto dto = new WordsDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                dtoListe.add(dto);

            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtoListe;
    }

    @Override
    public WordsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id,imageId,text,dateAdded FROM LABELS WHERE  id = ?";
        WordsDto dto = null;
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new WordsDto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
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

    public List<WordsDto> getDtos(String path) throws RepositoryException {
        String newPath = "'" + path + "'";
        String sql = "SELECT id,imageId,text,dateAdded FROM LABELS where imageId=" + newPath;
        List<WordsDto> dtos = new ArrayList<>();
        try (Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                WordsDto dto = new WordsDto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    private static class WordsDaoHolder {

        private static WordsDao getInstance() throws RepositoryException {
            return new WordsDao();
        }
    }

}
