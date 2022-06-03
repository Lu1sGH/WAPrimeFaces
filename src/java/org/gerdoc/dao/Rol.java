/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.dao;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Rol implements Serializable{
    
    private String RolS;
    private String Descripcion;

    public Rol() {
    }

    public String getRolS() {
        return RolS;
    }

    public void setRolS(String RolS) {
        this.RolS = RolS;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    
    
}
