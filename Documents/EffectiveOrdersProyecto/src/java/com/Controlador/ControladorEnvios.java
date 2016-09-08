/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.Entity.Envio;
import com.Entity.Pedido;
import com.Entity.Proveedor;
import com.Entity.Usuario;
import com.Entity.Vehiculo;
import com.Facade.*;
import com.Facade.VehiculoFacade;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named(value = "controladorEnvios")
@SessionScoped
public class ControladorEnvios implements Serializable {
    
    private Usuario objUsuariosLogin;
    private int estados;
    private Envio tempEnvio = new Envio();
    List<Envio> listaEnvioGrafica;
    
    @Inject
    EnvioFacade envioFacade;
    
    @Inject
    VehiculoFacade vehiculoFacade;
    
    @Inject
    PedidoFacade pedidoFacade;
    
    @Inject
    ProveedorFacade proveedoresF;
    
    List<Pedido> pedidos = new ArrayList<Pedido>();
    ArrayList<Envio> fechas = new ArrayList<Envio>();
    
    public ControladorEnvios() {
//        listaEnvioGrafica = envioFacade.findAll();
        
    }
    
    public List<Envio> listarEnvios() {
        List<Envio> misEnvios = new ArrayList<Envio>();
        misEnvios = envioFacade.findAll();
        return misEnvios;
    }
    
    public String contarEnvios() {
        return "" + envioFacade.count();
    }
    
    public String envioNuevo1() throws ParseException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            Map params = externalContext.getRequestParameterMap();
            
            Envio miEnvio = new Envio();
            Proveedor miProveedor = new Proveedor();
            
            HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");
            
            miProveedor.setCedulaProveedor(Integer.parseInt("" + params.get("idProveedor")));

            //Encontrar Carro
            Vehiculo miVehiculo = new Vehiculo();
            List<Vehiculo> arrayVehiculo = vehiculoFacade.findAll();
            miVehiculo.setPlacaVehiculo("" + params.get("vehiculo"));
            for (int i = 0; i < arrayVehiculo.size(); i++) {
                if (arrayVehiculo.get(i).getPlacaVehiculo().equals(miVehiculo.getPlacaVehiculo())) {
                    miVehiculo = arrayVehiculo.get(i);
                }
            }

            //Hora Salida
            SimpleDateFormat objFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String hora = "" + params.get("horaSalida");
            String fechaSalida = "" + params.get("fechaSalida") + " " + hora + ":00";
            Date objDate = objFormat.parse(fechaSalida);
            miEnvio.setFechaSalida(objDate);

            //Fecha Aproximada Entrega
            SimpleDateFormat objFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            String fechaEntrega = "" + params.get("fechaEntrega");
            Date objDate1;
            objDate1 = objFormat1.parse(fechaEntrega);
            miEnvio.setFechaEntrega(objDate1);
            
            miEnvio.setIdVehiculo(miVehiculo);
            miEnvio.setEstado("Espera");
            miProveedor.setUsuario(this.objUsuariosLogin);
            miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
            miEnvio.setCedulaProveedor(this.objUsuariosLogin.getProveedor());
            miProveedor.setEmpresa("" + params.get("empresa"));
            miProveedor.setNit("" + params.get("nit"));
            miProveedor.setEstado("Aceptado");
            Date fecha = new Date();
            miEnvio.setFechaSalida(fecha);
            fechas.add(miEnvio);
            
            estados = 1;
            envioFacade.create(miEnvio);
            tempEnvio = miEnvio;
        } catch (Exception ex) {
            ex.printStackTrace();
            estados = 2;
        }
        return "tablaEnvioPedido.xhtml";
    }
    
    public String terminarEnvio() {
        if (pedidos.size() > 0) {
            for (int i = 0; i < pedidos.size(); i++) {
                pedidos.get(i).setIdEnvio(tempEnvio);
                pedidoFacade.edit(pedidos.get(i));
                
                estados = 10;
            }
            tempEnvio.setPedidoCollection(pedidos);
        } else {
            envioFacade.remove(tempEnvio);
            estados = 11;
        }
        return "tablaEnvios1.xhtml";
    }
    
    public String agregarPedido(Pedido tempPedidos) {
        if (tempPedidos.getIdEnvio() == null) {
            pedidos.add(tempPedidos);
            tempPedidos.setIdEnvio(tempEnvio);
            pedidoFacade.edit(tempPedidos);
            estados = 8;
        } else {
            estados = 9;
        }
        return "tablaEnvioPedido.xhtml";
    }
    
    public int validarPedido(Pedido pedido) {
        int var;
        if (pedido.getIdEnvio() == null) {
            var = 1;
        } else {
            var = 2;
        }
        return var;
    }
    
    public void limpiarEstados() {
        estados = 0;
    }
    
    public List<Pedido> desabilitarAgregar() {
        List<Pedido> miPedido = new ArrayList<Pedido>();
        miPedido = pedidoFacade.findAll();
        ArrayList<Pedido> arrayPedido = new ArrayList<Pedido>();
        try {
            
            for (int i = 0; i < miPedido.size(); i++) {
                if (miPedido.get(i).getIdEnvio() == null) {
                    arrayPedido.add(miPedido.get(i));
                } else {
                    arrayPedido.remove(miPedido.get(i));
                }
                
            }
            
        } catch (Exception e) {
            
        }
        return arrayPedido;
    }
    
    public void editarEnvio() throws ParseException {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        
        Proveedor miProveedor = new Proveedor();
        
        HttpServletRequest miSesion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.objUsuariosLogin = (Usuario) miSesion.getSession().getAttribute("usuariologin");
        miProveedor.setCedulaProveedor(Integer.parseInt("" + params.get("idProveedor")));
        
        Envio miEnvio = this.envioFacade.find(Integer.parseInt((String) params.get("idEnvio2")));

        //Encontrar Carro
        Vehiculo miVehiculo = new Vehiculo();
        List<Vehiculo> arrayVehiculo = vehiculoFacade.findAll();
        miVehiculo.setPlacaVehiculo("" + params.get("vehiculo"));
        for (int i = 0; i < arrayVehiculo.size(); i++) {
            if (arrayVehiculo.get(i).getPlacaVehiculo().equals(miVehiculo.getPlacaVehiculo())) {
                miVehiculo = arrayVehiculo.get(i);
            }
        }

        //Hora Salida
        SimpleDateFormat objFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String hora = "" + params.get("horaSalida");
        String fechaSalida = "" + params.get("fechaSalida") + " " + hora + ":00";
        Date objDate = objFormat.parse(fechaSalida);
        miEnvio.setFechaSalida(objDate);

        //Fecha Aproximada Entrega
        SimpleDateFormat objFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        String fechaEntrega = "" + params.get("fechaEntrega");
        Date objDate1;
        objDate1 = objFormat1.parse(fechaEntrega);
        miEnvio.setFechaEntrega(objDate1);
        
        miEnvio.setIdVehiculo(miVehiculo);
        miEnvio.setEstado("Espera");
        miProveedor.setUsuario(this.objUsuariosLogin);
        miProveedor.setCedulaProveedor(this.objUsuariosLogin.getIdUsuario());
        miEnvio.setCedulaProveedor(this.objUsuariosLogin.getProveedor());
        miProveedor.setEmpresa("" + params.get("empresa"));
        miProveedor.setNit("" + params.get("nit"));
        miProveedor.setEstado("Aceptado");
        Date fecha = new Date();
        miEnvio.setFechaSalida(fecha);
        fechas.add(miEnvio);
        
        
        envioFacade.edit(miEnvio);
    }

//    public String listarEnvioId(Pedido pedido) {
//        Envio objEnvio = new Envio();
//      String temp = "Publicado";
//        for (Envio envio : pedido.getEnvioCollection()) {
//            if (!envio.getEstado().equals("")) {
//                
//                envio = new Envio();
//                objEnvio = envio;                
//           objEnvio.setEstado("Espera");
//           temp = objEnvio.getEstado();
//           
//            }else{
//                objEnvio.setEstado("Publicado");
//                temp = objEnvio.getEstado();
//                
//            }
//                
//
//        }
//        return temp ;
//    }
    public String cambiarFechaFormato(Date temp) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDelTexto.format(temp);
    }
    
    public String cambiarHoraFormato(Date temp) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("hh:mm");
        return formatoDelTexto.format(temp);
    }
    
    public int terminarEnvio(Envio envio) {
        Date date = new Date();
        if (date.after(envio.getFechaEntrega())) {
            envio.setEstado("Entregado");
            envioFacade.edit(envio);
            return 1;
        }
        return 2;
    }
    
    public void cancelarEnvio(Envio temp) {
        try {
            Envio miEnvio = new Envio();
            miEnvio = temp;
            miEnvio.setEstado("Cancelado");
            envioFacade.edit(miEnvio);
            estados = 5;
        } catch (Exception e) {
            e.printStackTrace();
            estados = 6;
        }
        
    }
    
    public void compararHoras(Envio envio) {
        Calendar calendar = Calendar.getInstance();
        Envio miEnvio = new Envio();
        
        for (int i = 0; i < fechas.size(); i++) {
            if (fechas.get(i).getIdEnvio() == envio.getIdEnvio()) {
                calendar.setTime(fechas.get(i).getFechaSalida());
            }
        }
        
    }
    
    public void graficaPDF() throws ClassNotFoundException, SQLException, IOException, JRException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sipactne_effectiveorders", "root", "");
        //parametrospara enviar el reporte
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txt", "Effective Orders");
        //cargar el archivo jasper
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/reporteEnvios.jasper"));
        //Enviar el archivo Jasper
        JasperPrint jp = JasperFillManager.fillReport(archivo.getPath(), parametros, conexion);
        //Inicializar la descarga de el archivo
        HttpServletResponse sr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        sr.addHeader("content-disposition", "attachment; filename=Envios.pdf");

        ServletOutputStream stream = sr.getOutputStream();
        //descarga de el PDF
        JasperExportManager.exportReportToPdfStream(jp, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().getResponseComplete();

    }
    
    
    public int graficaEnvios(int mes) {
        
        Date objDate = new Date();
        Date objDate1 = new Date();
        int cantidad = 0;
        
        objDate.setDate(0);
        objDate.setMonth(mes);
        objDate.setHours(0);
        objDate.setMinutes(0);
        objDate.setSeconds(0);
        
        objDate1.setDate(31);
        objDate1.setMonth(mes);
        objDate1.setHours(0);
        objDate1.setMinutes(0);
        objDate1.setSeconds(0);
        
        for (Envio envio : listaEnvioGrafica) {
            if (envio.getEstado().equals("Entregado")) {
                
                if (envio.getFechaEntrega().before(objDate1) && objDate.before(envio.getFechaEntrega())) {
                    cantidad++;
                } else if (envio.getFechaEntrega().equals(objDate) || envio.getFechaEntrega().equals(objDate1)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
    
    public void setEstados(int estados) {
        this.estados = estados;
    }

    public int getEstados() {
        return estados;
    }
    
    
    public Envio getTempEnvio() {
        return tempEnvio;
    }
    
    public void setTempEnvio(Envio tempEnvio) {
        this.tempEnvio = tempEnvio;
    }
    
}
