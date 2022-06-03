/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.helper;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.gerdoc.dao.Proveedor;
import org.gerdoc.service.ProveedorService;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class ProveedorHelper  implements Serializable
{
    private Proveedor proveedor;
    private boolean edit;

    public ProveedorHelper() 
    {
    }
    
    public boolean loadProveedor( )
    {
        if( proveedor == null )
        {
            proveedor = new Proveedor( );
        }
        return proveedor != null;
    }
    
    public void addProveedor( )
    {
        if( !ProveedorService.addProveedor(proveedor) )
        {
            System.out.println("Error");
        }
        else
        {
            proveedor = null;
        }
    }
    
    public void editProveedor( Integer id )
    {
        if( id == null || id == 0 )
        {
            return;
        }
        proveedor = ProveedorService.getProveedorById(id);
        if( proveedor == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<Proveedor> getProveedorList( )
    {
        return ProveedorService.getProveedorList();
    }
    
    public void updateProveedor()
    {
        if( !ProveedorService.updateProveedor(proveedor) )
        {
            System.out.println("Error");
        }
        else
        {
            proveedor = null;
            edit = false;
        }
    }
    
    public void deleteProveedor( Integer id )
    {
        if( !ProveedorService.deleteProveedor( id ) )
        {
            System.out.println("Error");
        }
        else
        {
            proveedor = null;
        }
    }
   
    public Proveedor getProveedor() 
    {
        if( proveedor == null )
        {
            if( !loadProveedor() )
            {
                return null;
            }
        }
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) 
    {
        this.proveedor = proveedor;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
