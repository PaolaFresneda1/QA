/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paola Fresneda
 */
@Entity
@Table(name = "internacionalizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Internacionalizacion.findAll", query = "SELECT i FROM Internacionalizacion i"),
    @NamedQuery(name = "Internacionalizacion.findByIdInter", query = "SELECT i FROM Internacionalizacion i WHERE i.idInter = :idInter"),
    @NamedQuery(name = "Internacionalizacion.findByPrefijo", query = "SELECT i FROM Internacionalizacion i WHERE i.prefijo = :prefijo"),
    @NamedQuery(name = "Internacionalizacion.findByPalabra", query = "SELECT i FROM Internacionalizacion i WHERE i.palabra = :palabra"),
    @NamedQuery(name = "Internacionalizacion.findByIdioma", query = "SELECT i FROM Internacionalizacion i WHERE i.idioma = :idioma")})
public class Internacionalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInter")
    private Integer idInter;
    @Size(max = 10)
    @Column(name = "prefijo")
    private String prefijo;
    @Size(max = 20)
    @Column(name = "palabra")
    private String palabra;
    @Size(max = 10)
    @Column(name = "idioma")
    private String idioma;

    public Internacionalizacion() {
    }

    public Internacionalizacion(Integer idInter) {
        this.idInter = idInter;
    }

    public Integer getIdInter() {
        return idInter;
    }

    public void setIdInter(Integer idInter) {
        this.idInter = idInter;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInter != null ? idInter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Internacionalizacion)) {
            return false;
        }
        Internacionalizacion other = (Internacionalizacion) object;
        if ((this.idInter == null && other.idInter != null) || (this.idInter != null && !this.idInter.equals(other.idInter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Internacionalizacion[ idInter=" + idInter + " ]";
    }
    
}
