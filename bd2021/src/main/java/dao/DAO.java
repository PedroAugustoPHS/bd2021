/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dskaster
 * @param <T>
 */
public interface DAO<T> {

    void create(T t) throws SQLException;
    T read(Integer id) throws SQLException;
    void update(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
    List<T> all() throws SQLException;
    
}
