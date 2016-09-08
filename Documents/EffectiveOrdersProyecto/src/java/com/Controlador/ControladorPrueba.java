/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Pedido;
import com.Entity.Pedidoproducto;
import com.Entity.PedidoproductoPK;
import com.Entity.Producto;
import com.Facade.PedidoproductoFacade;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@Named(value = "controladorPrueba")
@RequestScoped
public class ControladorPrueba {
@Inject
         PedidoproductoFacade pedidoProductof;
    /**
     * Creates a new instance of ControladorPrueba
     */
    public ControladorPrueba() {
    }
     public String actualizarPedido(Pedidoproducto temp){
         
         try{
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faces.getExternalContext();
        Map params = externalContext.getRequestParameterMap();

        Pedido objPedido = new Pedido();
        objPedido = temp.getPedido();
        PedidoproductoPK pedidoP = new PedidoproductoPK();
        Pedidoproducto pedidoProducto = new Pedidoproducto();
        Producto producto = new Producto();
        
        producto.setCodigoProducto(temp.getProducto().getCodigoProducto());
        producto.setNombreProducto(temp.getProducto().getNombreProducto());
        producto.setPrecio(temp.getProducto().getPrecio());
        producto.setEstado(temp.getProducto().getEstado());
        

        pedidoP.setCodigoProducto(temp.getProducto().getCodigoProducto());
        pedidoP.setIdPedido(objPedido.getIdPedido());

        int codigo = (Integer.parseInt("" + params.get("formulario" + objPedido.getIdPedido() + ":nombreProducto")));
        pedidoP.setCodigoProducto(codigo);
        pedidoP.setIdPedido(temp.getPedido().getIdPedido());
        pedidoP.setIdPedidoProducto(Integer.parseInt("" + params.get("formulario" + objPedido.getIdPedido() + ":idPedidoProducto")));
        pedidoProducto.setCantidad(Integer.parseInt("" + params.get("cantidadProducto")));
        pedidoProducto.setClasificacion("" + params.get("clasificacionProducto"));
        pedidoProducto.setPrecioTotal(Integer.parseInt("" + params.get("precioPedido")));
        pedidoProducto.setCantidadPedido(Integer.parseInt("" + params.get("cantidadProducto")));
        pedidoProducto.setPedidoproductoPK(pedidoP);
        pedidoProducto.setPedido(objPedido);
        pedidoProducto.setProducto(producto);
        pedidoProductof.edit(pedidoProducto);
        return "reportes.xhtml";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
