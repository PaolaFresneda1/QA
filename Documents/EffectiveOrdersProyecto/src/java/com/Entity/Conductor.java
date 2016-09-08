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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "conductores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c"),
    @NamedQuery(name = "Conductor.findByIdCedulaConductor", query = "SELECT c FROM Conductor c WHERE c.idCedulaConductor = :idCedulaConductor"),
    @NamedQuery(name = "Conductor.findByNombre", query = "SELECT c FROM Conductor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Conductor.findByApellido", query = "SELECT c FROM Conductor c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Conductor.findByCorreo", query = "SELECT c FROM Conductor c WHERE c.correo = :correo"),
    @NamedQuery(name = "Conductor.findByTelefono", query = "SELECT c FROM Conductor c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Conductor.findByNumeroLicencia", query = "SELECT c FROM Conductor c WHERE c.numeroLicencia = :numeroLicencia"),
    @NamedQuery(name = "Conductor.findByNivelLicencia", query = "SELECT c FROM Conductor c WHERE c.nivelLicencia = :nivelLicencia")})
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCedulaConductor")
    private Integer idCedulaConductor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private int telefono;
    @Column(name = "numeroLicencia")
    private Integer numeroLicencia;
    @Size(max = 3)
    @Column(name = "nivelLicencia")
    private String nivelLicencia;
    @JoinColumn(name = "idVehiculo", referencedColumnName = "placaVehiculo")
    @ManyToOne
    private Vehiculo idVehiculo;
    @JoinColumn(name = "cedulaProveedor", referencedColumnName = "cedulaProveedor")
    @ManyToOne
    private Proveedor cedulaProveedor;

    public Conductor() {
    }

    public Conductor(Integer idCedulaConductor) {
        this.idCedulaConductor = idCedulaConductor;
    }

    public Conductor(Integer idCedulaConductor, String nombre, String apellido, String correo, int telefono) {
        this.idCedulaConductor = idCedulaConductor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Integer getIdCedulaConductor() {
        return idCedulaConductor;
    }

    public void setIdCedulaConductor(Integer idCedulaConductor) {
        this.idCedulaConductor = idCedulaConductor;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Integer getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(Integer numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getNivelLicencia() {
        return nivelLicencia;
    }

    public void setNivelLicencia(String nivelLicencia) {
        this.nivelLicencia = nivelLicencia;
    }

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Proveedor getCedulaProveedor() {
        return cedulaProveedor;
    }

    public void setCedulaProveedor(Proveedor cedulaProveedor) {
        this.cedulaProveedor = cedulaProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCedulaConductor != null ? idCedulaConductor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.idCedulaConductor == null && other.idCedulaConductor != null) || (this.idCedulaConductor != null && !this.idCedulaConductor.equals(other.idCedulaConductor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Conductor[ idCedulaConductor=" + idCedulaConductor + " ]";
    }
    
}
