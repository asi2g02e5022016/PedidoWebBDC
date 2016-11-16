/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.controller;

import com.asi.pedidoweb.modelo.Cliente;
import com.asi.pedidoweb.modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author samael lopez
 */
@Named(value = "sessionUsr")
@SessionScoped
public class SessionUsr implements Serializable {
    private Usuario usuario;
    private String token;
    private String userCliente;
    private Date fecha = new Date();
    private String url;
    private Integer codCliente;
     private Cliente cliente;



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getUserCliente() {
        return userCliente;
    }

    public void setUserCliente(String userCliente) {
        this.userCliente = userCliente;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.usuario);
        hash = 37 * hash + Objects.hashCode(this.token);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionUsr other = (SessionUsr) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        return true;
    }
 
}
