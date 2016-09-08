/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Paola Fresneda
 */
@Embeddable
public class UsuariotienerolPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idUsuarioTieneRoles")
    private int idUsuarioTieneRoles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRol")
    private int idRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;

    public UsuariotienerolPK() {
    }

    public UsuariotienerolPK(int idUsuarioTieneRoles, int idRol, int idUsuario) {
        this.idUsuarioTieneRoles = idUsuarioTieneRoles;
        this.idRol = idRol;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuarioTieneRoles() {
        return idUsuarioTieneRoles;
    }

    public void setIdUsuarioTieneRoles(int idUsuarioTieneRoles) {
        this.idUsuarioTieneRoles = idUsuarioTieneRoles;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuarioTieneRoles;
        hash += (int) idRol;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariotienerolPK)) {
            return false;
        }
        UsuariotienerolPK other = (UsuariotienerolPK) object;
        if (this.idUsuarioTieneRoles != other.idUsuarioTieneRoles) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.UsuariotienerolPK[ idUsuarioTieneRoles=" + idUsuarioTieneRoles + ", idRol=" + idRol + ", idUsuario=" + idUsuario + " ]";
    }
    
}
