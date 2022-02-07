package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Historico;

public class PgHistoricoDAO implements HistoricoDAO{

        private final Connection connection;


        private static final String CREATE_QUERY =
                "INSERT INTO public.historico(data_menor_preco, menor_preco, media_preco, maior_promo, jogo_id, loja_id) " +
                        "VALUES(?, ?, ?, ?, ?, ?);";

        private static final String READ_QUERY =
                "SELECT data_menor_preco, menor_preco, media_preco, maior_promo, jogo_id, loja_id " +
                        "FROM public.historico " +
                        "WHERE jogo_id = ? AND loja_id = ?;";

        private static final String UPDATE_QUERY =
                "UPDATE public.historico " +
                        "SET data_menor_preco = ?, menor_preco = ?, media_preco = ?, maior_promo = ? " +
                        "WHERE jogo_id = ? AND loja_id = ?;";

        private static final String DELETE_QUERY =
                "DELETE FROM public.historico " +
                        "WHERE jogo_id = ? AND loja_id = ?;";

        private static final String ALL_QUERY =
                "SELECT data_menor_preco, menor_preco, media_preco, maior_promo, jogo_id, loja_id " +
                        "FROM public.historico " +
                        "ORDER BY jogo_id;";

       public PgHistoricoDAO(Connection connection) {
            this.connection = connection;
        }

        public void escreveAlgo(){
            System.out.println("historico");
        };

    @Override
    public void create(Historico historico) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setDate(1, historico.getData_menor_preco());
            statement.setInt(2, historico.getMenor_preco());
            statement.setInt(3, historico.getMedia_preco());
            statement.setInt(4, historico.getMaior_promo());
            statement.setInt(5, historico.getJogo_id());
            statement.setInt(6, historico.getLoja_id());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgHistoricoDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

        }
    }

    @Override
    public Historico read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public  Historico readHist(Integer Jid, Integer Lid) throws SQLException {
        Historico historico = new Historico();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, Jid);
            statement.setInt(2, Lid);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    historico.setData_menor_preco(result.getDate("data_registro"));
                    historico.setMenor_preco(result.getInt("menor_preco"));
                    historico.setMedia_preco(result.getInt("media_preco"));
                    historico.setMaior_promo(result.getInt("maior_promo"));
                    historico.setJogo_id(result.getInt("jogo_id"));
                    historico.setLoja_id(result.getInt("loja_id"));
                } else {
                    throw new SQLException("Erro ao visualizar: historico não encontrado.");
                }
            }
            return historico;
        }
    }

    @Override
    public void update(Historico historico) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setDate(1, historico.getData_menor_preco());
            statement.setInt(2, historico.getMenor_preco());
            statement.setInt(3, historico.getMedia_preco());
            statement.setInt(4, historico.getMaior_promo());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: historico não encontrado.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao editar: historico não encontrado.")) {
                throw ex;
            }
            else {
                throw new SQLException("Erro ao editar historico.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        return;
    }

    @Override

    public void deleteHist (Integer Jid, Integer Lid) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, Jid);
            statement.setInt(1, Lid);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: histórico não encontrado.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao excluir: histórico não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir histórico.");
            }
        }
    }

    @Override
    public List<Historico> all() throws SQLException {
    return null;
    }



}
