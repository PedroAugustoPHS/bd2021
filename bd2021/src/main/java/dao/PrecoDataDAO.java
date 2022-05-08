package dao;

import model.PrecoData;
import model.PrecoJogo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PrecoDataDAO extends DAO<PrecoData> {
    List<PrecoData> readPreco(Integer jogo_id, Integer loja_id) throws SQLException;

    PrecoData readPrecoData(Date data_registro, Integer jogo_id, Integer loja_id) throws SQLException;

    void loadHist(String lojaname) throws SQLException, IOException, ClassNotFoundException;

    List<PrecoJogo> readTopBarato() throws SQLException;

    List<PrecoJogo> readTopCaro() throws SQLException;

    List<PrecoJogo> readTopPromo() throws SQLException;

    List<PrecoJogo> readTopDrip() throws SQLException;

    List<PrecoJogo> showAll() throws SQLException;
}
