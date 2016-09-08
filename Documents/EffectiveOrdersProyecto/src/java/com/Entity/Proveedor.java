/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByCedulaProveedor", query = "SELECT p FROM Proveedor p WHERE p.cedulaProveedor = :cedulaProveedor"),
    @NamedQuery(name = "Proveedor.findByEmpresa", query = "SELECT p FROM Proveedor p WHERE p.empresa = :empresa"),
    @NamedQuery(name = "Proveedor.findByNit", query = "SELECT p FROM Proveedor p WHERE p.nit = :nit"),
    @NamedQuery(name = "Proveedor.findByEstado", query = "SELECT p FROM Proveedor p WHERE p.estado = :estado")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cedulaProveedor")
    private Integer cedulaProveedor;
    @Size(max = 30)
    @Column(name = "empresa")
    private String empresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "cedulaProveedor")
    private Collection<Oferta> ofertaCollection;
    @OneToMany(mappedBy = "idProveedor")
    private Collection<Vehiculo> vehiculoCollection;
    @JoinColumn(name = "cedulaProveedor", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "cedulaProveedor")
    private Collection<Conductor> conductorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedulaProveedor")
    private Collection<Aporte> aporteCollection;
    @OneToMany(mappedBy = "cedulaProveedor")
    private Collection<Envio> envioCollection;

    public Proveedor() {
    }

    public Proveedor(Integer cedulaProveedor) {
        this.cedulaProveedor = cedulaProveedor;
    }

    public Proveedor(Integer cedulaProveedor, String nit, String estado) {
        this.cedulaProveedor = cedulaProveedor;
        this.nit = nit;
        this.estado = estado;
    }

    public Integer getCedulaProveedor() {
        return cedulaProveedor;
    }

    public void setCedulaProveedor(Integer cedulaProveedor) {
        this.cedulaProveedor = cedulaProveedor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Oferta> getOfertaCollection() {
        return ofertaCollection;
    }

    public void setOfertaCollection(Collection<Oferta> ofertaCollection) {
        this.ofertaCollection = ofertaCollection;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Conductor> getConductorCollection() {
        return conductorCollection;
    }

    public void setConductorCollection(Collection<Conductor> conductorCollection) {
        this.conductorCollection = conductorCollection;
    }

    @XmlTransient
    public Collection<Aporte> getAporteCollection() {
        return aporteCollection;
    }

    public void setAporteCollection(Collection<Aporte> aporteCollection) {
        this.aporteCollection = aporteCollection;
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
        hash += (cedulaProveedor != null ? cedulaProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.cedulaProveedor == null && other.cedulaProveedor != null) || (this.cedulaProveedor != null && !this.cedulaProveedor.equals(other.cedulaProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Proveedor[ cedulaProveedor=" + cedulaProveedor + " ]";
    }
    
}
