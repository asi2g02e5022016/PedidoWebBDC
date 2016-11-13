/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
//           Usuario usr = crud.buscarEntidad(Usuario.class, usuario);
//           if (usr != null) {
//               System.out.println("password.hashCode().." +password.hashCode());
//               System.out.println("usr.getClave().." +usr.getClave());
//                  if (!String.valueOf(
//                   password.hashCode()).equals(usr.getClave()) ) { 
//                      mensaje ="El usuario o clave son invalidos ";
//                alert(mensaje,FacesMessage.SEVERITY_INFO );
//               return null;
//               
//             }
                  if (sesion == null) {
               sesion = new SessionUsr();
           }
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
