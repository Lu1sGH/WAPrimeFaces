/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.gerdoc.dao.User;
import org.gerdoc.dao.Uno;
import org.gerdoc.dao.User;
/**
 *
 * @author gerdoc
 */
public class UserService implements Serializable
{
    
    public static List<User> getUserList( )
    {
        List<User>userList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;
        
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT * FROM TBL_USER" );
            if( resultSet == null )
            {
                return null;
            }
            userList = new ArrayList<>();
            while( resultSet.next() )
            {
                user = new User();
                user.setUserS( resultSet.getString(1) );
                user.setNombre(resultSet.getString(2) );
                user.setCorreo(resultSet.getString(3) );
                user.setPassword(resultSet.getString(4) );
                userList.add(user);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return userList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addUser( User user )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( user == null || user.getUserS() == null || user.getNombre() == null || user.getCorreo() == null || user.getPassword() == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "insert into tbl_user(User, Nombre, Correo, Password) values( ? , ? , ? , ? )";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, user.getUserS() );
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getCorreo());
            preparedStatement.setString(4, user.getPassword());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            MySqlConnection.closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static User getUserById( String usuario )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        String sql = "SELECT * FROM TBL_USER where USER = ?";
        
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return null;
            }
            preparedStatement.setString(1, usuario);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                user = new User();
                user.setUserS( resultSet.getString(1) );
                user.setNombre(resultSet.getString(2) );
                user.setCorreo(resultSet.getString(3) );
                user.setPassword(resultSet.getString(4) );
            }
            preparedStatement.close();
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return user;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateUser( User user )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( user == null || user.getUserS() == null || user.getNombre() == null || user.getCorreo() == null || user.getPassword() == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update tbl_user set Nombre= ?, Correo= ?, Password= ? where User = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, user.getNombre() );
            preparedStatement.setString(2, user.getCorreo());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserS());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            MySqlConnection.closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteUser( String usuario )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( usuario == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "delete from tbl_user where User = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, usuario);
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            MySqlConnection.closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
}
