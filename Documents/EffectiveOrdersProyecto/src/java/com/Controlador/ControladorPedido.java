/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Calificacion;
import com.Entity.Cliente;
import com.Entity.Envio;
import com.Entity.Oferta;
import com.Entity.Pedido;
import com.Entity.Pedidoproducto;
import com.Entity.PedidoproductoPK;
import com.Entity.Producto;
import com.Facade.CalificacionFacade;
import com.Facade.PedidoFacade;
import com.Facade.PedidoproductoFacade;
import com.Facade.ProductoFacade;
import com.Modelo.ReportePedidos;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Named(value = "controladorPedido")
@SessionScoped
public class ControladorPedido implements Serializable {

    private int codigoProducto;
    private String nombreProducto;
    private Double precio;
    private List reportesPedidos;
    private int estados;
    private Oferta ofertas;

    @Inject
    ProductoFacade productof;

    @EJB
    private com.Facade.PedidoFacade pedidof;
    @EJB
    private com.Facade.PedidoproductoFacade pedidoProductof;
    @Inject
    CalificacionFacade calificacionf;

    @Inject
    PedidoFacade pedidoFacade;

    /**
     * Creates a new instance of ControladorPedido
     */
    public ControladorPedido() {
        estados = 0;
    }

    public String consultarProducto() {
        Producto objProducto;
        objProducto = productof.find(codigoProducto);
        precio = objProducto.getPrecio();
        nombreProducto = objProducto.getNombreProducto();

        return "";
    }

    public String generarPedido() throws ParseException {

        try {
            FacesContext objFaces = FacesContext.getCurrentInstance();
            ExternalContext objExternal = objFaces.getExternalContext();
            Map params = objExternal.getRequestParameterMap();

            Pedido pedido = new Pedido();
            Pedidoproducto pedidoProducto = new Pedidoproducto();
            PedidoproductoPK pedidoP = new PedidoproductoPK();

            Date fechaIn = new Date();

            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yy-MM-dd");
            String fechaEntrega = "" + params.get("fechaEntrega");
            Date fechaE = null;
            fechaE = formatoDeFecha.parse(fechaEntrega);

            Cliente objCliente = new Cliente();
            objCliente.setCedulaCliente(Integer.parseInt("" + params.get("cedulaCliente")));
            pedido.setCedulaCliente(objCliente);
            pedido.setFechaEntrega(fechaE);
            pedido.setFechaDeSolicitud(fechaIn);
            pedido.setEstado("Pendiente");
            pedido.setComentarios("" + params.get("comentariosPedido"));
            pedido.setDireccion("" + params.get("direccion"));
            pedidof.create(pedido);

            int codigo = (Integer.parseInt("" + params.get("nombreProducto")));

            pedidoP.setCodigoProducto(codigo);
            pedidoP.setIdPedido(pedido.getIdPedido());

            pedidoProducto.setCantidad(Integer.parseInt("" + params.get("cantidadProducto")));
            pedidoProducto.setClasificacion("" + params.get("clasificacionProducto"));
            pedidoProducto.setPrecioTotal(Integer.parseInt("" + params.get("precioPedido")));
            pedidoProducto.setDescuento(0);
            pedidoProducto.setPedidoproductoPK(pedidoP);

            pedidoProductof.create(pedidoProducto);

            estados = 1;
            return "solicitarPedido.xhtml";

        } catch (Exception e) {
            e.printStackTrace();
            estados = 2;
        }
        return "";
    }

    public String generarPedidoDescuento() throws ParseException {

        try {
            FacesContext objFaces = FacesContext.getCurrentInstance();
            ExternalContext objExternal = objFaces.getExternalContext();
            Map params = objExternal.getRequestParameterMap();

            Pedido pedido = new Pedido();
            Pedidoproducto pedidoProducto = new Pedidoproducto();
            PedidoproductoPK pedidoP = new PedidoproductoPK();

            Date fechaIn = new Date();

//            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
//            String fechaEntrega = "" + params.get("fechaEntrega");
//            Date fechaE;
//            fechaE = formatoDeFecha.parse(fechaEntrega);
            SimpleDateFormat objFormat1 = new SimpleDateFormat("yy-MM-dd");
            String fechaEntrega = "" + params.get("fechaE");
            Date fechaE;
            fechaE = objFormat1.parse(fechaEntrega);

            Cliente objCliente = new Cliente();
            objCliente.setCedulaCliente(Integer.parseInt("" + params.get("cedulaCliente")));

            pedido.setIdPedido(pedidoFacade.count() + 1);
            pedido.setCedulaCliente(objCliente);
            pedido.setFechaEntrega(fechaE);
            pedido.setFechaDeSolicitud(fechaIn);
            pedido.setEstado("Pendiente");
            pedido.setComentarios("" + params.get("comentariosPedido"));
            pedido.setDireccion("" + params.get("direccion"));

            pedidof.create(pedido);

            int codigo = (Integer.parseInt("" + params.get("codigoProducto")));

            pedidoP.setCodigoProducto(codigo);
            pedidoP.setIdPedido(pedido.getIdPedido());

            pedidoProducto.setCantidad(Integer.parseInt("" + params.get("cantidad")));
            pedidoProducto.setClasificacion("" + params.get("clasificacionProducto"));
            pedidoProducto.setPrecioTotal(Integer.parseInt("" + params.get("precioPedido"))*pedidoProducto.getCantidad());
            pedidoProducto.setDescuento(Integer.parseInt(""+params.get("descuento")));
            pedidoProducto.setPedidoproductoPK(pedidoP);
            pedidoProductof.create(pedidoProducto);
            return "ofertas.xhtml";
            
        } catch (Exception e) {
            e.printStackTrace();
            estados = 2;
        }
        return "";
    }

    public String calcularDescuento(int precio, int descuento) {
        String valor;
        precio = precio - ((precio * descuento) / 100);
        valor = "" + precio;
        return valor;
    }

    public List<Pedido> listarPedidos() {

        List<Pedido> pedidos = pedidof.findAll();
        return pedidos;
    }

    public String contarPedidos() {
        return "" + pedidoProductof.count();
    }

    public List<Pedidoproducto> listarPedidosProductos() {

        List<Pedidoproducto> pedidos = pedidoProductof.findAll();
        return pedidos;
    }

    public String generarCalificacionPedido() {

        try {
            FacesContext faces = FacesContext.getCurrentInstance();
            ExternalContext externalContext = faces.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            Calificacion objCalificacion = new Calificacion();

            objCalificacion.setIdPedido(Integer.parseInt("" + params.get("codigoP")));
            objCalificacion.setCalificación("" + params.get("demo"));
            objCalificacion.setSugerencias("" + params.get("sugerencias"));
            estados = 3;
            return "reportes.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            estados = 4;
        }
        return "";
    }

    public String actualizarPedido(Pedidoproducto temp) {

        try {
            FacesContext faces = FacesContext.getCurrentInstance();
            ExternalContext externalContext = faces.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            Pedido objPedido = new Pedido();
            objPedido = temp.getPedido();
            PedidoproductoPK pedidoP = new PedidoproductoPK();
            Pedidoproducto pedidoProducto = new Pedidoproducto();

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
            pedidoProductof.edit(pedidoProducto);
            estados = 7;
            return "reportes.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            estados = 8;
        }
        return "";
    }

    public String actualizarPedido1(Pedidoproducto temp) {

        try {
            FacesContext faces = FacesContext.getCurrentInstance();
            ExternalContext externalContext = faces.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            Pedido objPedido = new Pedido();
            objPedido = temp.getPedido();
            PedidoproductoPK pedidoP = new PedidoproductoPK();
            Pedidoproducto pedidoProducto = new Pedidoproducto();

            pedidoP.setCodigoProducto(temp.getProducto().getCodigoProducto());
            pedidoP.setIdPedido(objPedido.getIdPedido());

            Date fechaIn = new Date();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEntrega = "" + params.get("fechaEntrega");
            Date fechaE;
            fechaE = formatoDeFecha.parse(fechaEntrega);

            objPedido.setFechaEntrega(fechaE);
            objPedido.setFechaDeSolicitud(fechaIn);
            objPedido.setComentarios("" + params.get("comentariosPedido"));
            objPedido.setDireccion("" + params.get("direccion"));
            pedidof.edit(objPedido);

            int codigo = (Integer.parseInt("" + params.get("formulario" + objPedido.getIdPedido() + ":nombreProducto")));
            pedidoP.setCodigoProducto(codigo);
            pedidoP.setIdPedido(temp.getPedido().getIdPedido());
            pedidoP.setIdPedidoProducto(Integer.parseInt("" + params.get("formulario" + objPedido.getIdPedido() + ":idPedidoProducto")));

            pedidoProducto.setCantidad(Integer.parseInt("" + params.get("cantidadProducto")));
            pedidoProducto.setClasificacion("" + params.get("clasificacionProducto"));
            pedidoProducto.setPrecioTotal(Integer.parseInt("" + params.get("precioPedido")));
            pedidoProducto.setCantidadPedido(Integer.parseInt("" + params.get("cantidadProducto")));
            pedidoProducto.setPedidoproductoPK(pedidoP);
            pedidoProductof.edit(pedidoProducto);
            estados = 7;
            return "newxhtml.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            estados = 8;
        }
        return "";
    }

    public void cancelarPedido(Pedidoproducto temp) {
        try {
            Pedido miPedido = new Pedido();
            miPedido = temp.getPedido();
            miPedido.setEstado("Cancelado");
            pedidof.edit(miPedido);
            estados = 5;
        } catch (Exception e) {
            e.printStackTrace();
            estados = 6;
        }

    }

    public void publicarPedido(Pedidoproducto temp) {

        Pedido miPedido = new Pedido();
        miPedido = temp.getPedido();
        miPedido.setEstado("Publicado");
        pedidof.edit(miPedido);

    }

    public void cambiarEstadoEntregado(Pedidoproducto temp) {

        Pedido miPedido = new Pedido();
        miPedido = temp.getPedido();
        miPedido.setEstado("Entregado");
        pedidof.edit(miPedido);

    }

    public String cambiarFechaFormato(Date temp) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDelTexto.format(temp);
    }

    public void retornarValor() {
        estados = 0;
    }

    public String borrarEstados() {
        this.estados = 0;
        return "solicitarPedido.xhtml";
    }

    JasperPrint jasperPrint;

    public void init() throws JRException {
        List<Pedidoproducto> pedidos = pedidoProductof.findAll();

        ArrayList<ReportePedidos> misPedidos = new ArrayList<>();

        for (Pedidoproducto pedido : pedidos) {

            ReportePedidos miObj = new ReportePedidos();
            miObj.setIdPedido(pedido.getPedidoproductoPK().getIdPedido());
            miObj.setNombreProducto(pedido.getProducto().getNombreProducto());
            miObj.setCantidad((long) pedido.getCantidad());
            miObj.setClasificacion(pedido.getClasificacion());
            miObj.setDescuento(pedido.getDescuento());
            miObj.setPrecioTotal(pedido.getPrecioTotal());
            miObj.setFechaDeSolicitud(cambiarFechaFormato(pedido.getPedido().getFechaDeSolicitud()));
            miObj.setFechaEntrega(cambiarFechaFormato(pedido.getPedido().getFechaEntrega()));
            miObj.setEstado(pedido.getPedido().getEstado());
            miObj.setCedulaCliente(pedido.getPedido().getCedulaCliente());
            misPedidos.add(miObj);

        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reportesPedidos);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = (String) servletContext.getRealPath("Cliente/Reportes"); // Sustituye "/" por el directorio ej: "/upload"
        realPath += "/report1.jasper";
        jasperPrint = JasperFillManager.fillReport(realPath, new HashMap(), beanCollectionDataSource);
    }

    public void PDF() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream;
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

    }

    public void DOCX() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
        ServletOutputStream servletOutputStream;
        servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void XLSX() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void ODT() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.odt");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JROdtExporter docxExporter = new JROdtExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void PPT() throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pptx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRPptxExporter docxExporter = new JRPptxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

    public Oferta getOfertas() {
        return ofertas;
    }

    public void setOfertas(Oferta ofertas) {
        this.ofertas = ofertas;
    }

    public void setOfertas1(Oferta ofertas) {
        this.ofertas = ofertas;
    }

}
