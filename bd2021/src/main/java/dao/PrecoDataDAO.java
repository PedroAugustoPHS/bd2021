package dao;

import model.PrecoData;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public interface PrecoDataDAO extends DAO<PrecoData> {
    PrecoData readPrecoData(Date data_registro, Integer jogo_id, Integer loja_id) throws SQLException;

    void loadHist() throws SQLException, IOException, ClassNotFoundException;
}
