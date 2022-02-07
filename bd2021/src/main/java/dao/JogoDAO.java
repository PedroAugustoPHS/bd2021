package dao;

import model.Jogo;

import java.sql.SQLException;
import java.util.List;

public interface JogoDAO extends DAO<Jogo>{
    public List<Jogo> showImportant() throws SQLException;
}
