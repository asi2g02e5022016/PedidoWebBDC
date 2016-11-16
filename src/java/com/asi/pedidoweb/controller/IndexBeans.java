/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.controller;

import com.asi.pedidoweb.modelo.Cliente;
import com.asi.pedidoweb.negocio.ConsumerWSLocal;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
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
@ManagedBean(name = "indexBeans")
@ViewScoped
public class IndexBeans implements  Serializable {

    @EJB
    private ConsumerWSLocal consumerWS;
    
    
   
   @Inject
   SessionUsr sesion;
    /**
     * Creates a new instance of IndexBeans
     */
    public IndexBeans() {
    }

    
    
    
       private String usuario;
   private String password;
   
    public String ingresarSistema() {
        System.out.println("entroo...");
       try {
           
           if (usuario == null
                   || usuario.trim().equals("")) {
              
               System.out.println("usr.." + usuario);
                 alert("El codigo de usuario es obligatorio.",
                         FacesMessage.SEVERITY_INFO );
               return null;
               
           }
           if (password == null
                   || password.trim().equals("")) {
               
                alert( "El password de usuario es obligatorio.",FacesMessage.SEVERITY_INFO );
               return null;
               
           }
           System.out.println(" password.hashCode().. "
                   +  password.hashCode());
             Properties propiedades = new Properties();
    
   /**Cargamos el archivo desde la ruta especificada*/
   propiedades
     .load(getClass().getResourceAsStream("server.properties"));
 
   /**Obtenemos los parametros definidos en el archivo*/
       String URLBASE = propiedades.getProperty("serverPrincipal");
           Cliente cli = new Cliente();
           cli.setUsuario(usuario);
           cli.setPassword(String.valueOf(password.trim().hashCode()));
           System.out.println("URLBASE.. " +URLBASE);
       String json =  new Gson().toJson(cli);
            String jsonRetur = this.consumerWS.consumirWebservices(
                    usuario, json, 
                    URLBASE + "UsuarioWS");
            cli = new Gson().fromJson(jsonRetur, Cliente.class);
                  if (sesion == null) {
               sesion = new SessionUsr();
           }
                  sesion.setCliente(cli);
                  
                  String token = 
               usuario+ password + new Date().toString();
               sesion.setUserCliente(usuario);
               sesion.setFecha(new Date());
               sesion.setToken(String.valueOf(token.hashCode()));
           
           return "/PedidoBDC?faces-redirect=true";
      
       } catch (Exception ex) {
           Logger.getLogger(IndexBeans.class.getName()).log(Level.SEVERE, null, ex);
           alert(ex.getMessage(),FacesMessage.SEVERITY_INFO );
           return null;
              
       }
    }  
    
        
                 public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
                 
                 

}
