package dao;

import model.PrecoData;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PrecoDataDAO extends DAO<PrecoData> {
    List<PrecoData> readPreco(Integer jogo_id, Integer loja_id) throws SQLException;

    PrecoData readPrecoData(Date data_registro, Integer jogo_id, Integer loja_id) throws SQLException;

    void loadHist(String lojaname) throws SQLException, IOException, ClassNotFoundException;
}
