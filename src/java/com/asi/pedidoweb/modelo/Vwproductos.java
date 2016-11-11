/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "vwproductos")
@NamedQueries({
    @NamedQuery(name = "Vwproductos.findAll", query = "SELECT v FROM Vwproductos v")})
public class Vwproductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto")
    private int idproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "producto")
    private String producto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmarcaproducto")
    private int idmarcaproducto;
    @Size(max = 50)
    @Column(name = "marcaproducto")
    private String marcaproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idgrupoproducto")
    private int idgrupoproducto;
    @Size(max = 50)
    @Column(name = "grupoproducto")
    private String grupoproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoproducto")
    private int idtipoproducto;
    @Size(max = 100)
    @Column(name = "tipoproducto")
    private String tipoproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmedida")
    private int idmedida;
    @Size(max = 2)
    @Column(name = "medida")
    private String medida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "idusuario")
    private String idusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preciocompra")
    private double preciocompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioventa")
    private double precioventa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vendible")
    private boolean vendible;
    @Column(name = "excento")
    private Boolean excento;

    public Vwproductos() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getIdmarcaproducto() {
        return idmarcaproducto;
    }

    public void setIdmarcaproducto(int idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }

    public String getMarcaproducto() {
        return marcaproducto;
    }

    public void setMarcaproducto(String marcaproducto) {
        this.marcaproducto = marcaproducto;
    }

    public int getIdgrupoproducto() {
        return idgrupoproducto;
    }

    public void setIdgrupoproducto(int idgrupoproducto) {
        this.idgrupoproducto = idgrupoproducto;
    }

    public String getGrupoproducto() {
        return grupoproducto;
    }

    public void setGrupoproducto(String grupoproducto) {
        this.grupoproducto = grupoproducto;
    }

    public int getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(int idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public int getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(int idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public boolean getVendible() {
        return vendible;
    }

    public void setVendible(boolean vendible) {
        this.vendible = vendible;
    }

    public Boolean getExcento() {
        return excento;
    }

    public void setExcento(Boolean excento) {
        this.excento = excento;
    }
    
}
