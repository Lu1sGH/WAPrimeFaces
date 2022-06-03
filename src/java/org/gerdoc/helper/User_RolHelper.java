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
import org.gerdoc.dao.Rol;
import org.gerdoc.dao.User;
import org.gerdoc.dao.User_Rol;
import org.gerdoc.dao.User_Rol;
import org.gerdoc.service.User_RolService;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class User_RolHelper  implements Serializable
{
    private User_Rol user_Rol;
    private boolean edit;

    public User_RolHelper() 
    {
    }
    
    public boolean loadUser_Rol( )
    {
        if( user_Rol == null )
        {
            user_Rol = new User_Rol( new User(), new Rol() );
        }
        return user_Rol != null;
    }
    
    public void addUser_Rol( )
    {
        if( !User_RolService.addUser_Rol(user_Rol) )
        {
            System.out.println("Error");
        }
        else
        {
            user_Rol = null;
        }
    }
    
    public void editUser_Rol( String User_Rol )
    {
        if( User_Rol == null )
        {
            return;
        }
        user_Rol = User_RolService.getUser_RolById(User_Rol);
        if( user_Rol == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<User_Rol> getUser_RolList( )
    {
        return User_RolService.getUser_RolList();
    }
    
    public void updateUser_Rol()
    {
        if( !User_RolService.updateUser_Rol(user_Rol) )
        {
            System.out.println("Error");
        }
        else
        {
            user_Rol = null;
            edit = false;
        }
    }
    
    public void deleteUser_Rol( String User_Rol )
    {
        if( !User_RolService.deleteUser_Rol( User_Rol ) )
        {
            System.out.println("Error");
        }
        else
        {
            user_Rol = null;
        }
    }
   
    public User_Rol getUser_Rol() 
    {
        if( user_Rol == null )
        {
            if( !loadUser_Rol() )
            {
                return null;
            }
        }
        return user_Rol;
    }

    public void setUser_Rol(User_Rol user_Rol) 
    {
        this.user_Rol = user_Rol;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
