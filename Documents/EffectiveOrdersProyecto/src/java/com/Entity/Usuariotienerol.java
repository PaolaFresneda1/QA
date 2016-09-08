/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paola Fresneda
 */
@Entity
@Table(name = "usuariotieneroles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariotienerol.findAll", query = "SELECT u FROM Usuariotienerol u"),
    @NamedQuery(name = "Usuariotienerol.findByIdUsuarioTieneRoles", query = "SELECT u FROM Usuariotienerol u WHERE u.usuariotienerolPK.idUsuarioTieneRoles = :idUsuarioTieneRoles"),
    @NamedQuery(name = "Usuariotienerol.findByIdRol", query = "SELECT u FROM Usuariotienerol u WHERE u.usuariotienerolPK.idRol = :idRol"),
    @NamedQuery(name = "Usuariotienerol.findByIdUsuario", query = "SELECT u FROM Usuariotienerol u WHERE u.usuariotienerolPK.idUsuario = :idUsuario")})
public class Usuariotienerol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariotienerolPK usuariotienerolPK;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idRol", referencedColumnName = "idRol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public Usuariotienerol() {
    }

    public Usuariotienerol(UsuariotienerolPK usuariotienerolPK) {
        this.usuariotienerolPK = usuariotienerolPK;
    }

    public Usuariotienerol(int idUsuarioTieneRoles, int idRol, int idUsuario) {
        this.usuariotienerolPK = new UsuariotienerolPK(idUsuarioTieneRoles, idRol, idUsuario);
    }

    public UsuariotienerolPK getUsuariotienerolPK() {
        return usuariotienerolPK;
    }

    public void setUsuariotienerolPK(UsuariotienerolPK usuariotienerolPK) {
        this.usuariotienerolPK = usuariotienerolPK;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariotienerolPK != null ? usuariotienerolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariotienerol)) {
            return false;
        }
        Usuariotienerol other = (Usuariotienerol) object;
        if ((this.usuariotienerolPK == null && other.usuariotienerolPK != null) || (this.usuariotienerolPK != null && !this.usuariotienerolPK.equals(other.usuariotienerolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Usuariotienerol[ usuariotienerolPK=" + usuariotienerolPK + " ]";
    }
    
}
