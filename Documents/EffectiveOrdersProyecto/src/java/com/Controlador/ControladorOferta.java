package com.Controlador;

import com.Entity.Cliente;
import com.Facade.ProductoFacade;
import com.Entity.Oferta;
import com.Entity.Producto;
import com.Entity.Proveedor;
import com.Entity.Usuario;
import com.Facade.ClienteFacade;
import com.Facade.OfertaFacade;
import com.Facade.ProveedorFacade;
import com.Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.Entity.Mailer;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named(value = "controladorOferta")
@SessionScoped
public class ControladorOferta implements Serializable {

    private int estados;
    private Usuario objUsuariosLogin;

    @Inject
    OfertaFacade ofertasF;

    @Inject
    ProveedorFacade proveedoresF;

    @Inject
    ProductoFacade productosF;

    @Inject
    UsuarioFacade usuarioFacade;

    @Inject
    ClienteFacade clienteFacade;

    /**
     * Creates a new instance of ControladorOferta
     */
    public ControladorOferta() {
    }

    public List<Oferta> leerOfertas() {

        List<Oferta> arregloOfertas;
        arregloOfertas = ofertasF.findAll();
        return arregloOfertas;
    }

    public String contarOfertas() {
        return "" + ofertasF.count();
    }

    public List<Oferta> listarOfertas() {
        List<Oferta> misOfertas;
        misOfertas = ofertasF.findAll();

        return misOfertas;
    }

    public void ofertaNueva() throws ParseException {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            Map params = externalContext.getRequestParameterMap();

            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");

            Proveedor miProveedor = new Proveedor();
            Oferta miOferta = new Oferta();
            Producto miProducto = new Producto();

//          Encontrar Producto
            int codigoProducto = Integer.parseInt((String) params.get("codigoProducto"));
            miProducto.setCodigoProducto(codigoProducto);
            List<Producto> misProductos = productosF.findAll();
            for (int i = 0; i < misProductos.size(); i++) {
                if (misProductos.get(i).getCodigoProducto() == codigoProducto) {
                    miProducto = misProductos.get(i);
                }
            }
            miProducto.setCodigoProducto(codigoProducto);
            miProveedor.setCedulaProveedor(Integer.parseInt("" + params.get("idProveedor")));

            //Fecha Inicio
            SimpleDateFormat objFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicio = "" + params.get("fechaInicio");
            Date objDate;
            objDate = objFormat.parse(fechaInicio);
            miOferta.setFechaInicio(objDate);

            //Fecha Fin
            String fechaFin = "" + params.get("fechaFin");
            Date objDate1;
            objDate1 = objFormat.parse(fechaFin);
            miOferta.setFechaFin(objDate1);

            miOferta.setDescuento(Integer.parseInt("" + params.get("descuento")));
            miOferta.setCodigoProducto(miProducto);
            miOferta.setEstado("Pendiente");
            miProveedor.setUsuario(this.objUsuariosLogin);
            miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
            miOferta.setCedulaProveedor(this.objUsuariosLogin.getProveedor());
            miProveedor.setEmpresa("" + params.get("empresa"));
            miProveedor.setNit("" + params.get("nit"));
            miProveedor.setEstado("Aceptado");
            miOferta.setCedulaProveedor(miProveedor);

            estados = 1;
            ofertasF.create(miOferta);
            Mailer correos = new Mailer();
            List<Cliente> clientes = clienteFacade.findAll();
            for (int i = 0; i < clientes.size(); i++) {
                Mailer.send(clientes.get(i).getUsuario().getCorreo(), "EffectiveOrders", "<div style='display: block;'><img style='widht: 20px; height: 20px;' src='http://s33.postimg.org/c4cod87ov/logo_ultim.png' alt='Logo'></div>" + "<h3 style color: 2AB015; font-size: large;> El descuento de la oferta es: </h3>" + miOferta.getDescuento());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            estados = 2;
        }

    }

    public void editarOferta() throws ParseException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();

        Proveedor miProveedor = new Proveedor();
        Producto miProducto = new Producto();

        HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");
        miProveedor.setCedulaProveedor(Integer.parseInt("" + params.get("idProveedor")));        
        Oferta miOferta = this.ofertasF.find(Integer.parseInt((String) params.get("idOferta2")));
        miOferta.setDescuento(Integer.parseInt("" + params.get("descuento")));
        miProveedor.setUsuario(this.objUsuariosLogin);
        miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
        miOferta.setCedulaProveedor(this.objUsuariosLogin.getProveedor());
        miOferta.setCedulaProveedor(miProveedor);

        ofertasF.edit(miOferta);

    }

    public String cambiarFechaFormato(Date temp) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDelTexto.format(temp);
    }

    public void publicarOferta(Oferta temp) {
        Oferta miOferta = temp;
        miOferta.setEstado("Publicado");
        ofertasF.edit(miOferta);
    }

    public void cancelarOferta(Oferta temp) {
        try {
            Oferta miOferta = new Oferta();
            miOferta = temp;
            miOferta.setEstado("Cancelado");
            ofertasF.edit(miOferta);
            estados = 5;
        } catch (Exception e) {
            e.printStackTrace();
            estados = 6;
        }

    }  

    public int terminarOferta(Oferta oferta) {
        Date date = new Date();
        if (date.after(oferta.getFechaFin())) {
            oferta.setEstado("Finalizado");
            ofertasF.edit(oferta);
            return 1;
        }

        return 2;
    }
    
    public void ofertasPDF() throws ClassNotFoundException, SQLException, IOException, JRException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sipactne_effectiveorders", "root", "");
        //parametrospara enviar el reporte
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txt", "Effective Orders");
        //cargar el archivo jasper
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/reporteOfertas.jasper"));
        //Enviar el archivo Jasper
        JasperPrint jp = JasperFillManager.fillReport(archivo.getPath(), parametros, conexion);
        //Inicializar la descarga de el archivo
        HttpServletResponse sr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        sr.addHeader("content-disposition", "attachment; filename=Ofertas.pdf");

        ServletOutputStream stream = sr.getOutputStream();
        //descarga de el PDF
        JasperExportManager.exportReportToPdfStream(jp, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().getResponseComplete();

    }
    


    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

}
