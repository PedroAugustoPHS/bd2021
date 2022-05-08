package dao;

import model.Historico;
import model.PrecoData;
import model.PrecoJogo;

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

    private static final String READ2_QUERY =
            "SELECT data_registro, preco, porcentagem_promo " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ? " +
                    "ORDER BY data_registro DESC ;";

    private static final String UPDATE_QUERY =
            "UPDATE bd2021.preco_data " +
                    "SET preco = ?, porcentagem_promo = ? " +
                    "WHERE data_registro = ? AND jogo_id = ? AND loja_id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bd2021.preco_data " +
                    "WHERE data_registro = ? AND jogo_id = ? AND loja_id = ?;";

    private static final String ALL_QUERY =
            "SELECT data_registro, preco, porcentagem_promo, jogo_id, loja_id " +
                    "FROM bd2021.preco_data " +
                    "ORDER BY data_registro;";

    private static final String MIN_PRICE_QUERY =
            "SELECT MIN(preco) AS minprice " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ?;";

    private static final String HIST_QUERY =
            "SELECT data_registro, porcentagem_promo " +
                    "FROM bd2021.preco_data " +
                    "WHERE preco = ( " +
                    "SELECT MIN(preco) " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ?) AND loja_id = ? " +
                    "ORDER BY bd2021.preco_data.data_registro fetch first 1 rows only";

//            "SELECT MIN(preco) AS minprice " +
//                    "FROM bd2021.preco_data " +
//                    "WHERE jogo_id = ? AND loja_id = ?;" +
//            "SELECT data_registro, porcentagem_promo " +
//                    "FROM bd2021.preco_data " +
//                    "WHERE jogo_id = ? AND loja_id = ? AND minprice " +
//                    "ORDER BY bd2021.preco_data.data_registro fetch first 1 rows only;";

    private static final String AVG_PRICE_QUERY =
            "SELECT AVG(preco) AS avgprice " +
                    "FROM bd2021.preco_data " +
                    "WHERE jogo_id = ? AND loja_id = ?;";

    private static final String GET_TOP_ALL =
            "SELECT * " +
                    "FROM bd2021.preco_data " +
                    "INNER JOIN bd2021.jogo ON preco_data.jogo_id = jogo.id " +
                    "WHERE data_registro = (SELECT MAX(data_registro) FROM bd2021.preco_data) " +
                    "ORDER BY jogo_id;";

    private static final String GET_TOP_BARATO =
            "SELECT * " +
                    "FROM bd2021.preco_data INNER JOIN bd2021.jogo ON preco_data.jogo_id = jogo.id " +
                    "ORDER BY data_registro DESC,preco ASC LIMIT 12;";

    private static final String GET_TOP_CARO =
            "SELECT * " +
                    "FROM bd2021.preco_data INNER JOIN bd2021.jogo ON preco_data.jogo_id = jogo.id " +
                    "ORDER BY data_registro DESC,preco DESC LIMIT 12;";

    //fazer uma query bacana aqui (ou nao)
    private static final String GET_TOP_DRIP =
            "SELECT * " +
                    "FROM bd2021.preco_data INNER JOIN bd2021.jogo ON preco_data.jogo_id = jogo.id " +
                    "ORDER BY data_registro DESC,preco DESC LIMIT 12;";

    private static final String GET_TOP_PROMO =
            "SELECT * " +
                    "FROM bd2021.preco_data INNER JOIN bd2021.jogo ON preco_data.jogo_id = jogo.id " +
                    "ORDER BY porcentagem_promo DESC LIMIT 12;";


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
    public List<PrecoData> readPreco(Integer jogo_id, Integer loja_id) throws SQLException {
        List<PrecoData> listP = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(READ2_QUERY)) {
            statement.setInt(1, jogo_id);
            statement.setInt(2, loja_id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    PrecoData preco_data = new PrecoData();
                    preco_data.setData_registro(result.getDate("data_registro"));
                    preco_data.setPreco(result.getFloat("preco"));
                    preco_data.setPorcentagem_promo(result.getInt("porcentagem_promo"));

                    listP.add(preco_data);
                }
            }
            return listP;
        }
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
    public void loadHist(String filename) throws SQLException, IOException, ClassNotFoundException {

        Integer jogo_id = 30;
        Integer loja_id = 0;
        Integer j;
        Float min_preco;
        java.sql.Date data_reg;
        Integer promo;
        Float avg_price;
        Historico hist = new Historico();

        switch (filename) {
            case "epic": {
                loja_id = 1;
                break;
            }
            case "steam": {
                loja_id = 2;
                break;
            }
            case "nuuvem": {
                loja_id = 3;
                break;
            }
        }


        for (j = 1; j <= jogo_id; j++) {
            hist.setJogo_id(j);
            hist.setLoja_id(loja_id);
            try (PreparedStatement statement = connection.prepareStatement(MIN_PRICE_QUERY)) {
                statement.setInt(1, j);
                statement.setInt(2, loja_id);

                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        min_preco = result.getFloat("minprice");
                        hist.setMenor_preco(min_preco);
                    } else {
                        throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
                    }
                } catch (SQLException ex) {
                    continue;
                }
            } catch (SQLException ex) {
                throw new SQLException("Erro ao editar preco_data.");
            }

            try (PreparedStatement statement = connection.prepareStatement(HIST_QUERY)) {
                statement.setInt(1, j);
                statement.setInt(2, loja_id);
                statement.setInt(3, loja_id);
                try (ResultSet result = statement.executeQuery()) {
                    System.out.println("state:" + statement);
                    if (result.next()) {
                        System.out.println(result.getDate("data_registro"));
                        System.out.println(result.getInt("porcentagem_promo"));
                        data_reg = result.getDate("data_registro");
                        promo = result.getInt("porcentagem_promo");
                        hist.setData_menor_preco(data_reg);
                        hist.setMaior_promo(promo);
                    } else {
                    }
                } catch (SQLException ex) {
                    continue;
                }
            } catch (SQLException ex) {
                throw new SQLException("Erro ao editar preco_data.");
            }

            try (PreparedStatement statement = connection.prepareStatement(AVG_PRICE_QUERY)) {
                statement.setInt(1, j);
                statement.setInt(2, loja_id);

                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        avg_price = result.getFloat("avgprice");
                        hist.setMedia_preco(avg_price);
                    } else {
                        throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
                    }
                } catch (SQLException ex) {
                    continue;
                }
            } catch (SQLException ex) {
                throw new SQLException("Erro ao editar preco_data.");
            }

            HistoricoDAO dao;
            try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                dao = daoFactory.getHistoricoDAO();
                try {
                    dao.deleteHist(j, loja_id);
                    System.out.println("Hist excluido");
                } catch (SQLException e) {
                    System.out.println("Historico não existia :p");
                }
                try {
                    dao.create(hist);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<PrecoJogo> readTopBarato() throws SQLException {
        List<PrecoJogo> precoJogoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TOP_BARATO);
            ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    PrecoJogo preco_jogo = new PrecoJogo();
                    preco_jogo.setData_registro(result.getDate("data_registro"));
                    preco_jogo.setPreco(result.getFloat("preco"));
                    preco_jogo.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                    preco_jogo.setImage(result.getString("image"));
                    preco_jogo.setTitulo(result.getString("titulo"));
                    preco_jogo.setJogo_id(result.getInt("jogo_id"));
                    preco_jogo.setLoja_id(result.getInt("loja_id"));
                    precoJogoList.add(preco_jogo);
                }
                } catch (SQLException ex) {
                    throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
            }
            return precoJogoList;
    }

    @Override
    public List<PrecoJogo> readTopCaro() throws SQLException {
        List<PrecoJogo> precoJogoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TOP_CARO);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                PrecoJogo preco_jogo = new PrecoJogo();
                preco_jogo.setData_registro(result.getDate("data_registro"));
                preco_jogo.setPreco(result.getFloat("preco"));
                preco_jogo.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                preco_jogo.setImage(result.getString("image"));
                preco_jogo.setTitulo(result.getString("titulo"));
                preco_jogo.setJogo_id(result.getInt("jogo_id"));
                preco_jogo.setLoja_id(result.getInt("loja_id"));
                precoJogoList.add(preco_jogo);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
        }
        return precoJogoList;
    }

    @Override
    public List<PrecoJogo> readTopPromo() throws SQLException {
        List<PrecoJogo> precoJogoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TOP_PROMO);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                PrecoJogo preco_jogo = new PrecoJogo();
                preco_jogo.setData_registro(result.getDate("data_registro"));
                preco_jogo.setPreco(result.getFloat("preco"));
                preco_jogo.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                preco_jogo.setImage(result.getString("image"));
                preco_jogo.setTitulo(result.getString("titulo"));
                preco_jogo.setJogo_id(result.getInt("jogo_id"));
                preco_jogo.setLoja_id(result.getInt("loja_id"));
                precoJogoList.add(preco_jogo);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
        }
        return precoJogoList;
    }

    @Override
    public List<PrecoJogo> readTopDrip() throws SQLException {
        List<PrecoJogo> precoJogoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TOP_DRIP);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                PrecoJogo preco_jogo = new PrecoJogo();
                preco_jogo.setData_registro(result.getDate("data_registro"));
                preco_jogo.setPreco(result.getFloat("preco"));
                preco_jogo.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                preco_jogo.setImage(result.getString("image"));
                preco_jogo.setTitulo(result.getString("titulo"));
                preco_jogo.setJogo_id(result.getInt("jogo_id"));
                preco_jogo.setLoja_id(result.getInt("loja_id"));
                precoJogoList.add(preco_jogo);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
        }
        return precoJogoList;
    }

    @Override
    public List<PrecoJogo> showAll() throws SQLException {
        List<PrecoJogo> precoJogoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_TOP_ALL);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                PrecoJogo preco_jogo = new PrecoJogo();
                preco_jogo.setData_registro(result.getDate("data_registro"));
                preco_jogo.setPreco(result.getFloat("preco"));
                preco_jogo.setPorcentagem_promo(result.getInt("porcentagem_promo"));
                preco_jogo.setImage(result.getString("image"));
                preco_jogo.setTitulo(result.getString("titulo"));
                preco_jogo.setJogo_id(result.getInt("jogo_id"));
                preco_jogo.setLoja_id(result.getInt("loja_id"));
                precoJogoList.add(preco_jogo);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao visualizar: preco_data não encontrado.");
        }
        return precoJogoList;
    }
}
