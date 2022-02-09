/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import model.User;

/**
 *
 * @author dskaster
 */
public interface UserDAO extends DAO<User> {

    void authenticate(User usuario) throws SQLException, SecurityException;
    User getByLogin(String login) throws SQLException;
    
}
