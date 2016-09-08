/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Pedido;
import com.Entity.Pedidoproducto;
import com.Entity.Producto;
import com.Facade.ProductoFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "controladorProducto")
@ApplicationScoped
public class ControladorProducto implements Serializable {

    ArrayList<Pedidoproducto> objArrayPedidoProducto = new ArrayList<>();

    @Inject
    ProductoFacade productosf;

    /**
     * Creates a new instance of ControladorProducto
     */
    public ControladorProducto() {
    }

    public void carritoProductos() {
        FacesContext objFaces = FacesContext.getCurrentInstance();
        ExternalContext objExternal = objFaces.getExternalContext();
        Map parametros = objExternal.getRequestParameterMap();

        Pedidoproducto objPedidoProducto = new Pedidoproducto();
        Producto objProducto = new Producto();
        objProducto.setCodigoProducto(Integer.parseInt(""+parametros.get("idProductoOculto")));
        objPedidoProducto.setProducto(objProducto);
        objPedidoProducto.setCantidad(Integer.parseInt(""+parametros.get("cantidadProducto")));
        objPedidoProducto.setClasificacion(""+parametros.get("clasificacionProducto"));
        objPedidoProducto.setPrecioTotal(Integer.parseInt(""+parametros.get("precioPedido")));
        objArrayPedidoProducto.add(objPedidoProducto);
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = productosf.findAll();
        return productos;
    }

    public ArrayList<Pedidoproducto> leerCarrito() 
    {
        return this.objArrayPedidoProducto;
    }

    public String contarProductos() {

        return "" + productosf.count();
    }
}
