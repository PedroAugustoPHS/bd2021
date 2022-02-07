package dao;

import model.Historico;

import java.sql.SQLException;

public interface HistoricoDAO extends DAO<Historico> {
    public Historico readHist (Integer Jid, Integer Lid) throws SQLException;
    public void deleteHist (Integer Jid, Integer Lid) throws SQLException;
}
