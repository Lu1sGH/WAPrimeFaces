/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.dao;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alumno
 */
public class User_Rol implements Serializable {
    
    private User user;
    private Rol rol;
    private Date Inicio;
    private Date Fin;

    public User_Rol(User user, Rol rol) {
        this.user = user;
        this.rol = rol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getInicio() {
        return Inicio;
    }

    public void setInicio(Date Inicio) {
        this.Inicio = Inicio;
    }

    public Date getFin() {
        return Fin;
    }

    public void setFin(Date Fin) {
        this.Fin = Fin;
    }
    
    
    
}
