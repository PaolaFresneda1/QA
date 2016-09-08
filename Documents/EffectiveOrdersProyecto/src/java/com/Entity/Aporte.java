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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paola Fresneda
 */
@Entity
@Table(name = "aportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aporte.findAll", query = "SELECT a FROM Aporte a"),
    @NamedQuery(name = "Aporte.findByIdAporte", query = "SELECT a FROM Aporte a WHERE a.idAporte = :idAporte"),
    @NamedQuery(name = "Aporte.findByCantidadAportada", query = "SELECT a FROM Aporte a WHERE a.cantidadAportada = :cantidadAportada")})
public class Aporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAporte")
    private Integer idAporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadAportada")
    private int cantidadAportada;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedido idPedido;
    @JoinColumn(name = "idProducto", referencedColumnName = "codigoProducto")
    @ManyToOne(optional = false)
    private Producto idProducto;
    @JoinColumn(name = "cedulaProveedor", referencedColumnName = "cedulaProveedor")
    @ManyToOne(optional = false)
    private Proveedor cedulaProveedor;

    public Aporte() {
    }

    public Aporte(Integer idAporte) {
        this.idAporte = idAporte;
    }

    public Aporte(Integer idAporte, int cantidadAportada) {
        this.idAporte = idAporte;
        this.cantidadAportada = cantidadAportada;
    }

    public Integer getIdAporte() {
        return idAporte;
    }

    public void setIdAporte(Integer idAporte) {
        this.idAporte = idAporte;
    }

    public int getCantidadAportada() {
        return cantidadAportada;
    }

    public void setCantidadAportada(int cantidadAportada) {
        this.cantidadAportada = cantidadAportada;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
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
        hash += (idAporte != null ? idAporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aporte)) {
            return false;
        }
        Aporte other = (Aporte) object;
        if ((this.idAporte == null && other.idAporte != null) || (this.idAporte != null && !this.idAporte.equals(other.idAporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Aporte[ idAporte=" + idAporte + " ]";
    }
    
}
