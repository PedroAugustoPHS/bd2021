package dao;

import model.Jogo;

import java.sql.SQLException;
import java.util.List;

public interface JogoDAO extends DAO<Jogo> {
    List<Jogo> showImportant() throws SQLException;

    List<Jogo> searchGames(String gameName) throws SQLException;

    List<Jogo> searchCategory(String gameCategory) throws SQLException;
}
