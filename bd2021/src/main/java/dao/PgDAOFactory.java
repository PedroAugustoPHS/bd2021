/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;

/**
 *
 * @author dskaster
 */
public class PgDAOFactory extends DAOFactory {

    public PgDAOFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDAO getUserDAO() {
        return new PgUserDAO(this.connection);
    }

    @Override
    public JogoDAO getJogoDAO() { return new PgJogoDAO(this.connection); }

    @Override
    public LojaDAO getLojaDAO() { return new PgLojaDAO(this.connection); }

    @Override
    public PrecoDataDAO getPrecoDataDAO() { return new PgPrecoDataDAO(this.connection); }
    
}
