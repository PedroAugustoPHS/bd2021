package dao;

import model.Jogo;

import java.sql.SQLException;
import java.util.List;

public interface JogoDAO extends DAO<Jogo> {
    List<Jogo> showImportant() throws SQLException;

    List<Jogo> searchGames(String gameName, String[] category) throws SQLException;

    //List<Jogo> searchCategory(String gameCategory, String[] category) throws SQLException;
}
