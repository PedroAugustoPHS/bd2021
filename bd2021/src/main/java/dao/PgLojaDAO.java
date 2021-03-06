package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Loja;

public class PgLojaDAO implements LojaDAO{

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bd2021.loja(nome, loja_link, loja_img) " +
                    "VALUES(?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT id, nome, loja_link, loja_img " +
                    "FROM bd2021.loja " +
                    "WHERE id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bd2021.loja " +
                    "SET nome = ?, loja_link = ?, loja_img = ? " +
                    "WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bd2021.loja " +
                    "WHERE id = ?;";

    private static final String ALL_QUERY =
            "SELECT id, nome, loja_link, loja_img " +
                    "FROM bd2021.loja " +
                    "ORDER BY id;";

    public PgLojaDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Loja loja) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, loja.getNome());
            statement.setString(2, loja.getLoja_link());
            statement.setString(3, loja.getLoja_img());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgLojaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

        }
    }

    @Override
    public Loja read(Integer id) throws SQLException {
        Loja loja = new Loja();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    loja.setId(id);
                    loja.setNome(result.getString("nome"));
                    loja.setLoja_link(result.getString("loja_link"));
                    loja.setLoja_img(result.getString("loja_img"));
                } else {
                    throw new SQLException("Erro ao visualizar: loja n??o encontrado.");
                }
            }
        }
        return loja;
    }

    @Override
    public void update(Loja loja) throws SQLException {
        String query;
        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, loja.getNome());
            statement.setString(2, loja.getLoja_link());
            statement.setString(3, loja.getLoja_img());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: loja n??o encontrada.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao editar: loja n??o encontrada.")) {
                throw ex;
            }
            else {
                throw new SQLException("Erro ao editar loja.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: loja n??o encontrada.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao excluir: loja n??o encontradoa")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir loja.");
            }
        }
    }

    @Override
    public List<Loja> all() throws SQLException {
        List<Loja> lojaList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Loja loja = new Loja();
                loja.setId(result.getInt("id"));
                loja.setNome(result.getString("nome"));
                loja.setLoja_link(result.getString("loja_link"));
                loja.setLoja_img(result.getString("loja_img"));

                lojaList.add(loja);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao listar lojas.");
        }

        return lojaList;
    }

}
