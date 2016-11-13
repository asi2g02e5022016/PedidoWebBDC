package com.asi.pedidoweb.controller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.asi.pedidoweb.modelo.Vwproductos;
import com.asi.pedidoweb.negocio.ConsumerWSLocal;
import com.asi.pedidoweb.negocio.GestorEmailLocal;
import com.asi.pedidoweb.negocio.ProcesosVentasLocal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
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
//        @Inject
//    private SessionUsr sesion;
    
  
      private String descripcionProducto;
      
      
    
  private List < Vwproductos > lstProducto =  new ArrayList<>();
    /**
     * Creates a new instance of PedidoWEb
     */
    public PedidoWeb() {
    }
    
    public void mostrarDialogProd() {
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
                    "lportillo", 
                    "obtenerProductos",
                    "ventasWS", json, 
                    "http://localhost:8080/RestaurantBDC/webresources/");
            System.out.println("jsonRetur... " +jsonRetur);
         lstProducto =  new Gson().fromJson(jsonRetur, 
                            new TypeToken<ArrayList<ArrayList<Vwproductos>>>() {}.getType());
           // lstProducto = ejbBusProd.buscarProducto(filtro);
            System.out.println("lstProducto.." +lstProducto);
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

    
    
    
    
    
    
    
    public  void purebaEnviarcorreo() {
        try {
            List < String > lst = new ArrayList<>();
            lst.add("gerencia@bodeguitadelcerdito.com");
            gestorEmail.enviarEmail("Correo de prueba", "Prueba", "pso", lst, "sa");
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        public  void purebaStrin() {
        try {
            procesosVentas.pruebaCola();
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
        
        
}
