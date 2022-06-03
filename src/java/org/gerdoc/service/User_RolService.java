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
import org.gerdoc.dao.Rol;
import org.gerdoc.dao.User_Rol;
import org.gerdoc.dao.Uno;
import org.gerdoc.dao.User;
import org.gerdoc.dao.User_Rol;
/**
 *
 * @author gerdoc
 */
public class User_RolService implements Serializable
{
    
    public static List<User_Rol> getUser_RolList( )
    {
        List<User_Rol>user_RolList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User_Rol user_Rol = null;
        
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
            resultSet = statement.executeQuery( "SELECT User, Nombre, Correo, Password, Rol, Descripcion, Inicio, Fin FROM tbl_user_has_tbl_rol inner join tbl_user on tbl_user.user = tbl_user_has_tbl_rol.tbl_user_User inner join tbl_rol on tbl_rol.rol = tbl_user_has_tbl_rol.tbl_rol_Rol" );
            if( resultSet == null )
            {
                return null;
            }
            user_RolList = new ArrayList<>();
            while( resultSet.next() )
            {
                user_Rol = new User_Rol( new User(), new Rol());
                user_Rol.getUser().setUserS(resultSet.getString(1) );
                user_Rol.getUser().setNombre(resultSet.getString(2) );
                user_Rol.getUser().setCorreo(resultSet.getString(3) );
                user_Rol.getUser().setPassword(resultSet.getString(4) );
                user_Rol.getRol().setRolS(resultSet.getString(5) );
                user_Rol.getRol().setDescripcion(resultSet.getString(6) );
                user_Rol.setInicio( invert( resultSet.getDate(7) ) );
                user_Rol.setFin( invert( resultSet.getDate(8) ) );
                user_RolList.add(user_Rol);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return user_RolList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addUser_Rol( User_Rol user_Rol )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( user_Rol == null || user_Rol.getUser() == null || user_Rol.getRol() == null || user_Rol.getInicio() == null || user_Rol.getFin() == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "insert into tbl_user_has_tbl_rol(tbl_user_User, tbl_rol_Rol, Inicio, Fin) values( ? , ? , ? , ? )";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, user_Rol.getUser().getUserS() );
            preparedStatement.setString(2, user_Rol.getRol().getRolS() );
            preparedStatement.setDate(3, convert( user_Rol.getInicio() ) );
            preparedStatement.setDate(4, convert( user_Rol.getFin() ) );
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
    
    public static User_Rol getUser_RolById( String User )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User_Rol user_Rol = null;
        String sql = "SELECT User, Nombre, Correo, Password, Rol, Descripcion, Inicio, Fin FROM tbl_user_has_tbl_rol inner join tbl_user on tbl_user.user = tbl_user_has_tbl_rol.tbl_user_User inner join tbl_rol on tbl_rol.rol = tbl_user_has_tbl_rol.tbl_rol_Rol where tbl_user_User = ?";
        
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
            preparedStatement.setString(1, User);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                user_Rol = new User_Rol( new User(), new Rol());
                user_Rol.getUser().setUserS(resultSet.getString(1) );
                user_Rol.getUser().setNombre(resultSet.getString(2) );
                user_Rol.getUser().setCorreo(resultSet.getString(3) );
                user_Rol.getUser().setPassword(resultSet.getString(4) );
                user_Rol.getRol().setRolS(resultSet.getString(5) );
                user_Rol.getRol().setDescripcion(resultSet.getString(6) );
                user_Rol.setInicio( invert( resultSet.getDate(7) ) );
                user_Rol.setFin( invert( resultSet.getDate(8) ) );
            }
            preparedStatement.close();
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return user_Rol;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateUser_Rol( User_Rol user_Rol )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( user_Rol == null || user_Rol.getUser() == null || user_Rol.getRol() == null || user_Rol.getInicio() == null || user_Rol.getFin() == null)
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update tbl_user_has_tbl_rol set Inicio = ?, Fin = ? where tbl_user_User = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setDate(1, convert( user_Rol.getInicio() ) );
            preparedStatement.setDate(2, convert( user_Rol.getFin() ) );
            preparedStatement.setString(3, user_Rol.getUser().getUserS() );
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
    
    public static boolean deleteUser_Rol( String User )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( User == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "delete from tbl_user_has_tbl_rol where tbl_user_User = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, User);
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
    
    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    private static java.util.Date invert(java.sql.Date uDate){
        java.util.Date iDate = new java.util.Date( uDate.getTime() );
        return iDate;
    }
    
}
