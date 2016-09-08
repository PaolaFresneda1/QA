/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;



import com.Entity.Cliente;
import com.Entity.Pedido;
import com.Entity.Pedidoproducto;
import com.Entity.PedidoproductoPK;
import com.Entity.Producto;
import com.Facade.PedidoFacade;
import com.Facade.PedidoproductoFacade;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


@Named(value = "excelControlador")
@SessionScoped
public class ExcelControlador implements Serializable {
    
    @EJB
    private PedidoproductoFacade PedidoproductoFacade;
    
    @EJB
    private PedidoFacade PedidoFacade;
    
    Workbook archivoExcel;
    private Sheet mihoja;
    
    private Integer idPedido;
    private Date fechaDeSolicitud;
    private Date fechaEntrega;
    private String estado;
    private String comentarios;
    private String direccion;
    private Cliente cedulaCliente;
    private int cantidad;
    private String clasificacion;
    private int precioTotal;
    private int cantidadPedido;
    private int descuento;
    private int codigoProducto;
    private int idPedidoProducto;

    /**
     * Creates a new instance of exelControlador
     */
    public ExcelControlador() {
        
    }
    
    public int cantidadHojas(String ruta) throws IOException, BiffException {
        archivoExcel = Workbook.getWorkbook(new File(ruta));
        mihoja = archivoExcel.getSheet(0);
        return archivoExcel.getNumberOfSheets();
    }
    
    public boolean ingresarPedido() {
        Pedidoproducto miPedido = new Pedidoproducto();
        Pedido pedido= new Pedido();
        PedidoproductoPK pedidop= new PedidoproductoPK();
        
        pedido.setCedulaCliente(cedulaCliente);
        pedido.setIdPedido(idPedido);
        pedido.setFechaDeSolicitud(fechaDeSolicitud);
        pedido.setFechaEntrega(fechaEntrega);
        pedido.setDireccion(direccion);
        pedido.setEstado(estado);
        pedido.setComentarios(comentarios);
       
        miPedido.setCantidad(cantidad);
        miPedido.setCantidadPedido(cantidad);
        miPedido.setClasificacion(clasificacion);
        miPedido.setDescuento(descuento);
        miPedido.setPrecioTotal(precioTotal);
        
        pedidop.setIdPedido(idPedido);
        pedidop.setCodigoProducto(codigoProducto);
        pedidop.setIdPedidoProducto(idPedidoProducto);
        
        miPedido.setPedidoproductoPK(pedidop);
        
        try {
            PedidoproductoFacade.create(miPedido);
            PedidoFacade.create(pedido);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public Workbook getArchivoExcel() {
        return archivoExcel;
    }
    
    public void setArchivoExcel(Workbook archivoExcel) {
        this.archivoExcel = archivoExcel;
    }
    
    public Sheet getMihoja() {
        return mihoja;
    }
    
    public void setMihoja(Sheet mihoja) {
        this.mihoja = mihoja;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaDeSolicitud() {
        return fechaDeSolicitud;
    }

    public void setFechaDeSolicitud(Date fechaDeSolicitud) {
        this.fechaDeSolicitud = fechaDeSolicitud;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(Cliente cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
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
    
    
 
}
