package com.asi.pedidoweb.controller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.asi.pedidoweb.modelo.Sucursal;
import com.asi.pedidoweb.modelo.Vwproductos;
import com.asi.pedidoweb.negocio.ConsumerWSLocal;
import com.asi.pedidoweb.negocio.GestorEmailLocal;
import com.asi.pedidoweb.negocio.ProcesosVentasLocal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "pedidoWEb")
@ViewScoped
public class PedidoWeb implements Serializable{

    @EJB
    private ConsumerWSLocal consumerWS;

    @EJB
    private ProcesosVentasLocal procesosVentas;

    @EJB
    private GestorEmailLocal gestorEmail;
    @Inject
    private SessionUsr sesion;

    private int codsucursal;
    private List<Sucursal> lstSucursal;
    private String descripcionProducto;

    private List< Vwproductos> lstProducto = new ArrayList<>();

    /**
     * Creates a new instance of PedidoWEb
     */
    public PedidoWeb() {
    }

        @PostConstruct
     public void postContruction() {
        try {
            /**Creamos un Objeto de tipo Properties*/
   Properties propiedades = new Properties();
    
   /**Cargamos el archivo desde la ruta especificada*/
   propiedades
     .load(getClass().getResourceAsStream("server.properties"));
 
   /**Obtenemos los parametros definidos en el archivo*/
   String serverPrincipal = propiedades.getProperty("serverPrincipal");
            System.out.println("entro al manage" + serverPrincipal);
            if (serverPrincipal == null) {
                alert("No se encontro la configuracion de servidor",
                        FacesMessage.SEVERITY_INFO);
            }
                     
            String jsonRetur = this.consumerWS.consumirWebservices(
                    sesion.getUserCliente(), "",
                    "http://localhost:8080/RestaurantBDC/webresources/SucursalWS");
            System.out.println("jsonRetur... " + jsonRetur);
            lstSucursal = new Gson().fromJson(jsonRetur,
                    new TypeToken<ArrayList<Sucursal>>() {
            }.getType());
            System.out.println("lstSucursal..." + lstSucursal);
            if (lstSucursal == null || lstSucursal.isEmpty()) {
                alert("No se encontraron resultados de sucursales.", 
                        FacesMessage.SEVERITY_INFO);
            }
             
        }catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    public void mostrarDialogProd() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoProducto').show();");
    }

    public void mostrarProducto() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoProducto').show();");
    }

    public void buscarProducto() {
        try {

            Map filtro = new HashMap();
            if (descripcionProducto != null) {
                filtro.put("producto", descripcionProducto.trim());
            }
//            filtro.put("activo", 1);
//            filtro.put("tipo", 1);
            String json = new Gson().toJson(filtro);
            String jsonRetur = this.consumerWS.consumirWebservices(
                    sesion.getUserCliente(), json,
                    "http://localhost:8080/RestaurantBDC/webresources/Maestros");
            System.out.println("jsonRetur... " + jsonRetur);
            lstProducto = new Gson().fromJson(jsonRetur,
                    new TypeToken<ArrayList<Vwproductos>>() {
            }.getType());
            // lstProducto = ejbBusProd.buscarProducto(filtro);

            System.out.println("lstProducto..." + lstProducto);

            System.out.println("lstProducto.." + lstProducto);
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }

    public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void purebaEnviarcorreo() {
        try {
            List< String> lst = new ArrayList<>();
            lst.add("gerencia@bodeguitadelcerdito.com");
            gestorEmail.enviarEmail("Correo de prueba", "Prueba", "pso", lst, "sa");
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void purebaStrin() {
        try {
            procesosVentas.pruebaCola();
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vwproductos> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Vwproductos> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public int getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(int codsucursal) {
        this.codsucursal = codsucursal;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }


        
}
