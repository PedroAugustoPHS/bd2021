package dao;

import model.PrecoData;

import java.sql.Date;
import java.sql.SQLException;

public interface PrecoDataDAO extends DAO<PrecoData> {
    public void escreveAlgo();
    public PrecoData readPrecoData(Date data_registro, Integer jogo_id, Integer loja_id) throws SQLException;
}
