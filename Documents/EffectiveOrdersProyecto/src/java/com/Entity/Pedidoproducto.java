/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "pedidoproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidoproducto.findAll", query = "SELECT p FROM Pedidoproducto p"),
    @NamedQuery(name = "Pedidoproducto.findByIdPedidoProducto", query = "SELECT p FROM Pedidoproducto p WHERE p.pedidoproductoPK.idPedidoProducto = :idPedidoProducto"),
    @NamedQuery(name = "Pedidoproducto.findByCodigoProducto", query = "SELECT p FROM Pedidoproducto p WHERE p.pedidoproductoPK.codigoProducto = :codigoProducto"),
    @NamedQuery(name = "Pedidoproducto.findByIdPedido", query = "SELECT p FROM Pedidoproducto p WHERE p.pedidoproductoPK.idPedido = :idPedido"),
    @NamedQuery(name = "Pedidoproducto.findByCantidad", query = "SELECT p FROM Pedidoproducto p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pedidoproducto.findByClasificacion", query = "SELECT p FROM Pedidoproducto p WHERE p.clasificacion = :clasificacion"),
    @NamedQuery(name = "Pedidoproducto.findByPrecioTotal", query = "SELECT p FROM Pedidoproducto p WHERE p.precioTotal = :precioTotal"),
    @NamedQuery(name = "Pedidoproducto.findByCantidadPedido", query = "SELECT p FROM Pedidoproducto p WHERE p.cantidadPedido = :cantidadPedido"),
    @NamedQuery(name = "Pedidoproducto.findByDescuento", query = "SELECT p FROM Pedidoproducto p WHERE p.descuento = :descuento")})
public class Pedidoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoproductoPK pedidoproductoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Size(max = 5)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioTotal")
    private int precioTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadPedido")
    private int cantidadPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento")
    private int descuento;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "codigoProducto", referencedColumnName = "codigoProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public Pedidoproducto() {
    }

    public Pedidoproducto(PedidoproductoPK pedidoproductoPK) {
        this.pedidoproductoPK = pedidoproductoPK;
    }

    public Pedidoproducto(PedidoproductoPK pedidoproductoPK, int cantidad, int precioTotal, int cantidadPedido, int descuento) {
        this.pedidoproductoPK = pedidoproductoPK;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.cantidadPedido = cantidadPedido;
        this.descuento = descuento;
    }

    public Pedidoproducto(int idPedidoProducto, int codigoProducto, int idPedido) {
        this.pedidoproductoPK = new PedidoproductoPK(idPedidoProducto, codigoProducto, idPedido);
    }

    public PedidoproductoPK getPedidoproductoPK() {
        return pedidoproductoPK;
    }

    public void setPedidoproductoPK(PedidoproductoPK pedidoproductoPK) {
        this.pedidoproductoPK = pedidoproductoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(int cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoproductoPK != null ? pedidoproductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidoproducto)) {
            return false;
        }
        Pedidoproducto other = (Pedidoproducto) object;
        if ((this.pedidoproductoPK == null && other.pedidoproductoPK != null) || (this.pedidoproductoPK != null && !this.pedidoproductoPK.equals(other.pedidoproductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.Pedidoproducto[ pedidoproductoPK=" + pedidoproductoPK + " ]";
    }
    
}
