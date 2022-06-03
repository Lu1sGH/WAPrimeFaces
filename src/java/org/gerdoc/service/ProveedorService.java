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
import org.gerdoc.dao.Proveedor;
/**
 *
 * @author gerdoc
 */
public class ProveedorService implements Serializable
{
    
    public static List<Proveedor> getProveedorList( )
    {
        List<Proveedor>proveedorList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Proveedor proveedor = null;
        
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
            resultSet = statement.executeQuery( "SELECT * FROM TBL_PROV" );
            if( resultSet == null )
            {
                return null;
            }
            proveedorList = new ArrayList<>();
            while( resultSet.next() )
            {
                proveedor = new Proveedor();
                proveedor.setId(resultSet.getInt(1) );
                proveedor.setNombre(resultSet.getString(2) );
                proveedorList.add(proveedor);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return proveedorList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addProveedor( Proveedor proveedor )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( proveedor == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "INSERT INTO TBL_PROV(NOMBRE) VALUES(?)";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, proveedor.getNombre());
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
    
    public static Proveedor getProveedorById( Integer id )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Proveedor proveedor = null;
        String sql = "SELECT * FROM TBL_PROV WHERE ID_CATPROV= ?";
        
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
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                proveedor = new Proveedor();
                proveedor.setId(resultSet.getInt(1) );
                proveedor.setNombre(resultSet.getString(2) );
            }
            preparedStatement.close();
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return proveedor;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateProveedor( Proveedor proveedor )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( proveedor == null || proveedor.getId()== null || proveedor.getNombre()== null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update TBL_PROV SET NOMBRE=? WHERE ID_CATPROV= ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, proveedor.getNombre());
            preparedStatement.setInt(2, proveedor.getId());
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
    
    public static boolean deleteProveedor( Integer id )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( id == null || id == 0 )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "DELETE FROM TBL_PROV WHERE ID_CATPROV = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, id);
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
