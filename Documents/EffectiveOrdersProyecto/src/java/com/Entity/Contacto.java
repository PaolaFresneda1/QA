/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paola Fresneda
 */
@Entity
@Table(name = "contactenos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
    @NamedQuery(name = "Contacto.findByIdContactenos", query = "SELECT c FROM Contacto c WHERE c.idContactenos = :idContactenos"),
    @NamedQuery(name = "Contacto.findByNombre", query = "SELECT c FROM Contacto c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Contacto.findByApellido", query = "SELECT c FROM Contacto c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Contacto.findByCorreo", query = "SELECT c FROM Contacto c WHERE c.correo = :correo"),
    @NamedQuery(name = "Contacto.findByAsunto", query = "SELECT c FROM Contacto c WHERE c.asunto = :asunto"),
    @NamedQuery(name = "Contacto.findByContactenos", query = "SELECT c FROM Contacto c WHERE c.contactenos = :contactenos"),
    @NamedQuery(name = "Contacto.findByFechaEnvio", query = "SELECT c FROM Contacto c WHERE c.fechaEnvio = :fechaEnvio")})
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContactenos")
    private Integer idContactenos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 600)
    @Column(name = "contactenos")
    private String contactenos;
    @Column(name = "fechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    public Contacto() {
    }

    public Contacto(Integer idContactenos) {
        this.idContactenos = idContactenos;
    }

    public Contacto(Integer idContactenos, String nombre, String apellido, String correo, String asunto, String contactenos) {
        this.idContactenos = idContactenos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.asunto = asunto;
        this.contactenos = contactenos;
    }

    public Integer getIdContactenos() {
        return idContactenos;
    }

    public void setIdContactenos(Integer idContactenos) {
        this.idContactenos = idContactenos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContactenos() {
        return contactenos;
    }

    public void setContactenos(String contactenos) {
        this.contactenos = contactenos;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContactenos != null ? idContactenos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.idContactenos == null && other.idContactenos != null) || (this.idContactenos != null && !this.idContactenos.equals(other.idContactenos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Contacto[ idContactenos=" + idContactenos + " ]";
    }
    
}
