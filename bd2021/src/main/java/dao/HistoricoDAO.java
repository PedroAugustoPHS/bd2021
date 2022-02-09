package dao;

import model.Historico;

import java.sql.SQLException;

public interface HistoricoDAO extends DAO<Historico> {
    Historico readHist (Integer Jid, Integer Lid) throws SQLException;
    void deleteHist (Integer Jid, Integer Lid) throws SQLException;
}
