package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PrecoData;


public class PgPrecoDataDAO implements PrecoDataDAO{

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO public.preco_data(data_registro, preco, porcentagem_promo, jogo_id, loja_id) " +
                    "VALUES(?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT data_registro, preco, porcentagem_promo " +
                    "FROM public.preco_data " +
                    "WHERE data_registro = ?, jogo_id = ?, loja_id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE public.preco_data " +
                    "SET preco = ?, porcentagem_promo = ? " +
                    "WHERE data_registro = ?, jogo_id = ?, loja_id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM public.preco_data " +
                    "WHERE data_registro = ?, jogo_id = ?, loja_id = ?;";

    private static final String ALL_QUERY =
            "SELECT data_registro, preco, porcentagem_promo, jogo_id, loja_id " +
                    "FROM public.preco_data " +
                    "ORDER BY data_registro;";

    public PgPrecoDataDAO(Connection connection) {
        this.connection = connection;
    }

    public void escreveAlgo(){
        System.out.println("precinhos");
    };

    @Override
    public void create(PrecoData preco_data) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setDate(1, preco_data.getData_registro());
            statement.setInt(2, preco_data.getPreco());
            statement.setInt(3, preco_data.getPorcentagem_promo());
            statement.setInt(4, preco_data.getJogo_id());
            statement.setInt(5, preco_data.getLoja_id());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgPrecoDataDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

        }
    }

    @Override
    public PrecoData read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public PrecoData readPrecoData(Date data_registro, Integer jogo_id, Integer loja_id) throws SQLException {
        PrecoData preco_data = new PrecoData();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setDate(1, data_registro);
            statement.setInt(2, jogo_id);
            statement.setInt(3, loja_id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    preco_data.setData_registro(result.getDate("data_registro"));
                    preco_data.setPreco(result.getInt("preco"));
                    preco_data.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                } else {
                    throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
                }
            }
            return preco_data;
        }
    }

    @Override
    public void update(PrecoData preco_data) throws SQLException {
        String query;
        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, preco_data.getPreco());
            statement.setInt(2, preco_data.getPorcentagem_promo());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: preco_data não encontrado.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao editar: preco_data não encontrado.")) {
                throw ex;
            }
            else {
                throw new SQLException("Erro ao editar preco_data.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        //não alterado
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: preco_data não encontrada.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao excluir: preco_data não encontradoa")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir preco_data.");
            }
        }
    }

    @Override
    public List<PrecoData> all() throws SQLException {
        List<PrecoData> precoDataList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                PrecoData preco_data = new PrecoData();
                preco_data.setData_registro(result.getDate("data_registro"));
                preco_data.setPreco(result.getInt("preco"));
                preco_data.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                preco_data.setJogo_id(result.getInt("jogo_id"));
                preco_data.setLoja_id(result.getInt("loja_id"));

                precoDataList.add(preco_data);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao listar lojas.");
        }

        return precoDataList;
    }
}
