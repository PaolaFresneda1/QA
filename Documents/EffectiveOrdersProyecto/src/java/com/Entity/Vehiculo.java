/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paola Fresneda
 */
@Entity
@Table(name = "vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByPlacaVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.placaVehiculo = :placaVehiculo"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByTipoVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.tipoVehiculo = :tipoVehiculo"),
    @NamedQuery(name = "Vehiculo.findByCapacidad", query = "SELECT v FROM Vehiculo v WHERE v.capacidad = :capacidad")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "placaVehiculo")
    private String placaVehiculo;
    @Size(max = 15)
    @Column(name = "marca")
    private String marca;
    @Size(max = 7)
    @Column(name = "tipoVehiculo")
    private String tipoVehiculo;
    @Column(name = "capacidad")
    private Integer capacidad;
    @JoinColumn(name = "IdProveedor", referencedColumnName = "cedulaProveedor")
    @ManyToOne
    private Proveedor idProveedor;
    @OneToMany(mappedBy = "idVehiculo")
    private Collection<Conductor> conductorCollection;
    @OneToMany(mappedBy = "idVehiculo")
    private Collection<Envio> envioCollection;

    public Vehiculo() {
    }

    public Vehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @XmlTransient
    public Collection<Conductor> getConductorCollection() {
        return conductorCollection;
    }

    public void setConductorCollection(Collection<Conductor> conductorCollection) {
        this.conductorCollection = conductorCollection;
    }

    @XmlTransient
    public Collection<Envio> getEnvioCollection() {
        return envioCollection;
    }

    public void setEnvioCollection(Collection<Envio> envioCollection) {
        this.envioCollection = envioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placaVehiculo != null ? placaVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.placaVehiculo == null && other.placaVehiculo != null) || (this.placaVehiculo != null && !this.placaVehiculo.equals(other.placaVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Vehiculo[ placaVehiculo=" + placaVehiculo + " ]";
    }
    
}
