package dao;

import model.Jogo;

import java.sql.SQLException;
import java.util.List;

public interface JogoDAO extends DAO<Jogo>{
    public void escreveAlgo();
    public List<Jogo> showImportant() throws SQLException;
}
