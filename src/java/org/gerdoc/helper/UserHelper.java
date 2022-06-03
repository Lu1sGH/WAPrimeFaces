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
import org.gerdoc.dao.User;
import org.gerdoc.dao.User;
import org.gerdoc.service.UserService;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class UserHelper  implements Serializable
{
    private User user;
    private boolean edit;

    public UserHelper() 
    {
    }
    
    public boolean loadUser( )
    {
        if( user == null )
        {
            user = new User( );
        }
        return user != null;
    }
    
    public void addUser( )
    {
        if( !UserService.addUser(user) )
        {
            System.out.println("Error");
        }
        else
        {
            user = null;
        }
    }
    
    public void editUser( String usuario )
    {
        if( usuario == null )
        {
            return;
        }
        user = UserService.getUserById(usuario);
        if( user == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<User> getUserList( )
    {
        return UserService.getUserList();
    }
    
    public void updateUser()
    {
        if( !UserService.updateUser(user) )
        {
            System.out.println("Error");
        }
        else
        {
            user = null;
            edit = false;
        }
    }
    
    public void deleteUser( String usuario )
    {
        if( !UserService.deleteUser( usuario ) )
        {
            System.out.println("Error");
        }
        else
        {
            user = null;
        }
    }
   
    public User getUser() 
    {
        if( user == null )
        {
            if( !loadUser() )
            {
                return null;
            }
        }
        return user;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
