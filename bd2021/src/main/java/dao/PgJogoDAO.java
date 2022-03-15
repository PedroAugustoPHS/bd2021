package dao;

import model.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgJogoDAO implements JogoDAO{

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bd2021.jogo(titulo, desenvolvedora, categoria, descricao, publicadora, ano_publicacao, cpu, gpu, memoria_ram, so, armazenamento, image) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT id, titulo, desenvolvedora, categoria, descricao, publicadora, ano_publicacao, cpu, gpu, memoria_ram, so, armazenamento, image " +
                    "FROM bd2021.jogo " +
                    "WHERE id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bd2021.jogo " +
                    "SET titulo = ?, desenvolvedora = ?, categoria = ?, descricao = ?, publicadora = ?, ano_publicacao = ?, cpu = ?, gpu = ?, memoria_ram = ?, so = ?, armazenamento = ?, image = ? " +
                    "WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bd2021.jogo " +
                    "WHERE id = ?;";

    private static final String ALL_QUERY =
            "SELECT id, titulo, desenvolvedora, categoria, descricao, publicadora, ano_publicacao, cpu, gpu, memoria_ram, so, armazenamento, image " +
                    "FROM bd2021.jogo " +
                    "ORDER BY id;";

    private static final String IMPORTANT_QUERY =
            "SELECT id, titulo, image " +
                    "FROM bd2021.jogo " +
                    "ORDER BY id;";

    private static final String SEARCH_GAME =
            "SELECT id,titulo,image " +
                    "FROM bd2021.jogo " +
                    "WHERE titulo ILIKE ?;";


    public PgJogoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Jogo jogo) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, jogo.getTitulo());
            statement.setString(2, jogo.getDesenvolvedora());
            statement.setString(3, jogo.getCategoria());
            statement.setString(4, jogo.getDescricao());
            statement.setString(5, jogo.getPublicadora());
            statement.setString(6, jogo.getAno_publicacao());
            statement.setString(7, jogo.getCpu());
            statement.setString(8, jogo.getGpu());
            statement.setString(9, jogo.getMemoria_ram());
            statement.setString(10, jogo.getSo());
            statement.setString(11, jogo.getArmazenamento());
            statement.setString(12, jogo.getImage());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgJogoDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

        }
    }

    @Override
    public Jogo read(Integer id) throws SQLException {
        Jogo jogo = new Jogo();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    jogo.setId(id);
                    jogo.setTitulo(result.getString("titulo"));
                    jogo.setDesenvolvedora(result.getString("desenvolvedora"));
                    jogo.setCategoria(result.getString("categoria"));
                    jogo.setDescricao(result.getString("descricao"));
                    jogo.setPublicadora(result.getString("publicadora"));
                    jogo.setAno_publicacao(result.getString("ano_publicacao"));
                    jogo.setCpu(result.getString("cpu"));
                    jogo.setGpu(result.getString("gpu"));
                    jogo.setMemoria_ram(result.getString("memoria_ram"));
                    jogo.setSo(result.getString("so"));
                    jogo.setArmazenamento(result.getString("armazenamento"));
                    jogo.setImage(result.getString("image"));
                } else {
                    throw new SQLException("Erro ao visualizar: jogo não encontrado.");
                }
            }
        }
        return jogo;
    }

    @Override
    public void update(Jogo jogo) throws SQLException {
        String query;
        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, jogo.getTitulo());
            statement.setString(2, jogo.getDesenvolvedora());
            statement.setString(3, jogo.getCategoria());
            statement.setString(4, jogo.getDescricao());
            statement.setString(5, jogo.getPublicadora());
            statement.setString(6, jogo.getAno_publicacao());
            statement.setString(7, jogo.getCpu());
            statement.setString(8, jogo.getGpu());
            statement.setString(9, jogo.getMemoria_ram());
            statement.setString(10, jogo.getSo());
            statement.setString(11, jogo.getArmazenamento());
            statement.setString(12, jogo.getImage());
            statement.setInt(13, jogo.getId());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: jogo não encontrado.");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Erro ao editar: jogo não encontrado.")) {
                throw ex;
            }
            else {
                throw new SQLException("Erro ao editar jogo.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
                statement.setInt(1, id);

                if (statement.executeUpdate() < 1) {
                    throw new SQLException("Erro ao excluir: jogo não encontrado.");
                }
            } catch (SQLException ex) {
                if (ex.getMessage().equals("Erro ao excluir: jogo não encontrado.")) {
                    throw ex;
                } else {
                    throw new SQLException("Erro ao excluir jogo.");
                }
            }
    }

    @Override
    public List<Jogo> all() throws SQLException {
            List<Jogo> jogoList = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                 ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Jogo jogo = new Jogo();
                    jogo.setId(result.getInt("id"));
                    jogo.setTitulo(result.getString("titulo"));
                    jogo.setDesenvolvedora(result.getString("desenvolvedora"));
                    jogo.setCategoria(result.getString("categoria"));
                    jogo.setDescricao(result.getString("descricao"));
                    jogo.setPublicadora(result.getString("publicadora"));
                    jogo.setAno_publicacao(result.getString("ano_publicacao"));
                    jogo.setCpu(result.getString("cpu"));
                    jogo.setGpu(result.getString("gpu"));
                    jogo.setMemoria_ram(result.getString("memoria_ram"));
                    jogo.setSo(result.getString("so"));
                    jogo.setArmazenamento(result.getString("armazenamento"));
                    jogo.setImage(result.getString("image"));

                    jogoList.add(jogo);
                }
            } catch (SQLException ex) {
                throw new SQLException("Erro ao listar jogos.");
            }

            return jogoList;
    }

    @Override
    public List<Jogo> showImportant() throws SQLException {
        List<Jogo> jogoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(IMPORTANT_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Jogo jogo = new Jogo();
                jogo.setId(result.getInt("id"));
                jogo.setTitulo(result.getString("titulo"));
                jogo.setImage(result.getString("image"));

                jogoList.add(jogo);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao listar jogos.");
        }

        return jogoList;
    }

    @Override
    public List<Jogo> searchGames(String gameName) throws SQLException {
        List<Jogo> jogoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SEARCH_GAME)) {
            statement.setString(1, "%" + gameName + "%");
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Jogo jogo = new Jogo();
                    jogo.setId(result.getInt("id"));
                    jogo.setTitulo(result.getString("titulo"));
                    jogo.setImage(result.getString("image"));

                    jogoList.add(jogo);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }

        return jogoList;
    }


}
