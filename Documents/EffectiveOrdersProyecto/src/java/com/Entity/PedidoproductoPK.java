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
public class PedidoproductoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPedidoProducto")
    private int idPedidoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoProducto")
    private int codigoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPedido")
    private int idPedido;

    public PedidoproductoPK() {
    }

    public PedidoproductoPK(int idPedidoProducto, int codigoProducto, int idPedido) {
        this.idPedidoProducto = idPedidoProducto;
        this.codigoProducto = codigoProducto;
        this.idPedido = idPedido;
    }

    public int getIdPedidoProducto() {
        return idPedidoProducto;
    }

    public void setIdPedidoProducto(int idPedidoProducto) {
        this.idPedidoProducto = idPedidoProducto;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPedidoProducto;
        hash += (int) codigoProducto;
        hash += (int) idPedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoproductoPK)) {
            return false;
        }
        PedidoproductoPK other = (PedidoproductoPK) object;
        if (this.idPedidoProducto != other.idPedidoProducto) {
            return false;
        }
        if (this.codigoProducto != other.codigoProducto) {
            return false;
        }
        if (this.idPedido != other.idPedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Entity.PedidoproductoPK[ idPedidoProducto=" + idPedidoProducto + ", codigoProducto=" + codigoProducto + ", idPedido=" + idPedido + " ]";
    }
    
}
