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
import org.gerdoc.dao.Marca;
/**
 *
 * @author gerdoc
 */
public class MarcaService implements Serializable
{
    
    public static List<Marca> getMarcaList( )
    {
        List<Marca>marcaList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Marca marca = null;
        
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
            resultSet = statement.executeQuery( "SELECT * FROM TBL_MARCA" );
            if( resultSet == null )
            {
                return null;
            }
            marcaList = new ArrayList<>();
            while( resultSet.next() )
            {
                marca = new Marca();
                marca.setId_CatMarca(resultSet.getInt(1) );
                marca.setMarcaS(resultSet.getString(2) );
                marcaList.add(marca);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return marcaList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addMarca( Marca marca )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( marca == null || marca.getMarcaS()== null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "INSERT INTO TBL_MARCA(MARCA) VALUES(?)";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, marca.getMarcaS());
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
    
    public static Marca getMarcaById( Integer id )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Marca marca = null;
        String sql = "SELECT * FROM TBL_MARCA WHERE ID_CATMARCA= ?";
        
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
                marca = new Marca();
                marca.setId_CatMarca(resultSet.getInt(1) );
                marca.setMarcaS(resultSet.getString(2) );
            }
            preparedStatement.close();
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return marca;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateMarca( Marca marca )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( marca == null || marca.getId_CatMarca()== null || marca.getMarcaS()== null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update TBL_MARCA SET MARCA=? WHERE ID_CATMARCA= ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, marca.getMarcaS());
            preparedStatement.setInt(2, marca.getId_CatMarca());
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
    
    public static boolean deleteMarca( Integer id )
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
            sql = "DELETE FROM TBL_MARCA WHERE ID_CATMARCA = ?";
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
