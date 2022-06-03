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
import org.gerdoc.dao.Producto;
import org.gerdoc.dao.Proveedor;
/**
 *
 * @author gerdoc
 */
public class ProductoService implements Serializable
{
    
    public static List<Producto> getProductoList( )
    {
        List<Producto>productoList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Producto producto = null;
        
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
            resultSet = statement.executeQuery( "SELECT ID, TBL_PRODUCTO.NOMBRE, DESCRIPCION, URL, PRECIO, COSTO, TBL_PROV.NOMBRE, TBL_MARCA.MARCA FROM TBL_PRODUCTO INNER JOIN TBL_PROV ON TBL_PRODUCTO.TBL_PROV_ID_CATPROV = TBL_PROV.ID_CATPROV INNER JOIN TBL_MARCA ON TBL_PRODUCTO.TBL_MARCA_ID_CATMARCA = TBL_MARCA.ID_CATMARCA" );
            if( resultSet == null )
            {
                return null;
            }
            productoList = new ArrayList<>();
            while( resultSet.next() )
            {
                producto = new Producto( new Marca( ) , new Proveedor( ) );
                producto.setId( resultSet.getInt(1) );
                producto.setNombre( resultSet.getString(2) );
                producto.setDescripcion( resultSet.getString(3) );
                producto.setUrl( resultSet.getString(4) );
                producto.setPrecio( resultSet.getFloat(5) );
                producto.setCosto( resultSet.getFloat(6) );
                producto.getProveedor().setNombre( resultSet.getString(7) );
                producto.getMarca( ).setMarcaS( resultSet.getString(8) );
                productoList.add(producto);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return productoList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addProducto( Producto producto )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( producto == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "INSERT INTO TBL_PRODUCTO(NOMBRE,DESCRIPCION,URL,PRECIO,COSTO,TBL_PROV_ID_CATPROV,TBL_MARCA_ID_CATMARCA) VALUES(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getUrl());
            preparedStatement.setFloat(4, producto.getPrecio());
            preparedStatement.setFloat(5, producto.getCosto());
            preparedStatement.setInt(6, producto.getProveedor().getId());
            preparedStatement.setInt(7, producto.getMarca().getId_CatMarca());
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
    
    public static Producto getProductoById( Integer id )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Producto producto = null;
        String sql = "SELECT ID, TBL_PRODUCTO.NOMBRE, DESCRIPCION, URL, PRECIO, COSTO, TBL_PROV.NOMBRE, TBL_MARCA.MARCA FROM TBL_PRODUCTO INNER JOIN TBL_PROV ON TBL_PRODUCTO.TBL_PROV_ID_CATPROV = TBL_PROV.ID_CATPROV INNER JOIN TBL_MARCA ON TBL_PRODUCTO.TBL_MARCA_ID_CATMARCA = TBL_MARCA.ID_CATMARCA WHERE ID= ?";
        
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
                producto = new Producto( new Marca( ) , new Proveedor( ) );
                producto.setId( resultSet.getInt(1) );
                producto.setNombre( resultSet.getString(2) );
                producto.setDescripcion( resultSet.getString(3) );
                producto.setUrl( resultSet.getString(4) );
                producto.setPrecio( resultSet.getFloat(5) );
                producto.setCosto( resultSet.getFloat(6) );
                producto.getProveedor().setNombre( resultSet.getString(7) );
                producto.getMarca( ).setMarcaS( resultSet.getString(8) );  
            }
            preparedStatement.close();
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return producto;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateProducto( Producto producto )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( producto == null )
            {
                return false;
            }
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update TBL_PRODUCTO SET NOMBRE=?,DESCRIPCION=?,URL=?,PRECIO=?,COSTO=?,TBL_PROV_ID_CATPROV=?,TBL_MARCA_ID_CATMARCA=? WHERE ID= ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getUrl());
            preparedStatement.setFloat(4, producto.getPrecio());
            preparedStatement.setFloat(5, producto.getCosto());
            preparedStatement.setInt(6, producto.getProveedor().getId());
            preparedStatement.setInt(7, producto.getMarca().getId_CatMarca());
            preparedStatement.setInt(8, producto.getId());
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
    
    public static boolean deleteProducto( Integer id )
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
            sql = "DELETE FROM TBL_PRODUCTO WHERE ID = ?";
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
