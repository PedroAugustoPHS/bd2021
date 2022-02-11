package dao;

import model.Historico;
import model.PrecoData;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PgPrecoDataDAO implements PrecoDataDAO{

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bd2021.preco_data(data_registro, preco, porcentagem_promo, jogo_id, loja_id) " +
                    "VALUES(?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT data_registro, preco, porcentagem_promo " +
                    "FROM bd2021.preco_data " +
                    "WHERE data_registro = ?, jogo_id = ?, loja_id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bd2021.preco_data " +
                    "SET preco = ?, porcentagem_promo = ? " +
                    "WHERE data_registro = ?, jogo_id = ?, loja_id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bd2021.preco_data " +
                    "WHERE data_registro = ?, jogo_id = ?, loja_id = ?;";

    private static final String ALL_QUERY =
            "SELECT data_registro, preco, porcentagem_promo, jogo_id, loja_id " +
                    "FROM bd2021.preco_data " +
                    "ORDER BY data_registro;";

    private static final String MIN_PRICE_QUERY =
            "SELECT MIN(bd2021.preco_data.preco) " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ?";

    private static final String HIST_QUERY =
            "SELECT bd2021.preco_data.data_registro,bd2021.preco_data.porcentagem_promo " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ? AND preco = ?";

    private static final String AVG_PRICE_QUERY =
            "SELECT AVG(bd2021.preco_data.preco) " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ?";

    public PgPrecoDataDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(PrecoData preco_data) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setDate(1, preco_data.getData_registro());
            statement.setFloat(2, preco_data.getPreco());
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
                    preco_data.setPreco(result.getFloat("preco"));
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
            statement.setFloat(1, preco_data.getPreco());
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
                preco_data.setPreco(result.getFloat("preco"));
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

    @Override
    public void loadHist() throws SQLException, IOException, ClassNotFoundException {

        System.out.println("entrou");
        Integer jogo_id = 30;
        Integer loja_id = 3;
        Integer i, j;
        Float min_preco;
        Date data_reg;
        Integer promo;
        Float avg_price;

        for (i = 0; i > loja_id; i++) {
            for (j = 0; j > jogo_id; j++) {
                try (PreparedStatement statement = connection.prepareStatement(MIN_PRICE_QUERY)) {
                    statement.setInt(1, j);
                    statement.setInt(2, i);

                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            min_preco = result.getFloat(1);
                        } else {
                            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
                        }
                    }
                } catch (SQLException ex) {
                    throw new SQLException("Erro ao editar preco_data.");
                }
                try (PreparedStatement statement = connection.prepareStatement(HIST_QUERY)) {
                    statement.setInt(1, j);
                    statement.setInt(2, i);
                    statement.setFloat(3, min_preco);

                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            data_reg = result.getDate("data_registro");
                            promo = result.getInt("porcentagem_promo");

                        } else {
                            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
                        }
                    }
                } catch (SQLException ex) {
                    throw new SQLException("Erro ao editar preco_data.");
                }
                try (PreparedStatement statement = connection.prepareStatement(AVG_PRICE_QUERY)) {
                    statement.setInt(1, j);
                    statement.setInt(2, i);

                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            avg_price = result.getFloat(1);
                        } else {
                            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
                        }
                    }
                } catch (SQLException ex) {
                    throw new SQLException("Erro ao editar preco_data.");
                }
                System.out.println("minprice:" + min_preco);
                System.out.println("data_reg:" + data_reg);
                System.out.println("promo:" + promo);
                System.out.println("avg_price:" + avg_price);

                Historico hist = new Historico();
                hist.setData_menor_preco(data_reg);
                hist.setJogo_id(j);
                hist.setLoja_id(i);
                hist.setMenor_preco(min_preco);
                hist.setMedia_preco(avg_price);
                hist.setMaior_promo(promo);
                HistoricoDAO dao;

                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getHistoricoDAO();
                    try {
                        dao.create(hist);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
