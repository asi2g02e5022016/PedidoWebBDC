/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "medida")
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m")})
public class Medida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmedida")
    private Integer idmedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "medida")
    private String medida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "referencia")
    private String referencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "conversion")
    private BigDecimal conversion;
    @OneToMany(mappedBy = "idmedidabase")
    private List<Medida> medidaList;
    @JoinColumn(name = "idmedidabase", referencedColumnName = "idmedida")
    @ManyToOne
    private Medida idmedidabase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedida")
    private List<Producto> productoList;

    public Medida() {
    }

    public Medida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public Medida(Integer idmedida, String medida, String referencia, BigDecimal conversion) {
        this.idmedida = idmedida;
        this.medida = medida;
        this.referencia = referencia;
        this.conversion = conversion;
    }

    public Integer getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public List<Medida> getMedidaList() {
        return medidaList;
    }

    public void setMedidaList(List<Medida> medidaList) {
        this.medidaList = medidaList;
    }

    public Medida getIdmedidabase() {
        return idmedidabase;
    }

    public void setIdmedidabase(Medida idmedidabase) {
        this.idmedidabase = idmedidabase;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedida != null ? idmedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.idmedida == null && other.idmedida != null) || (this.idmedida != null && !this.idmedida.equals(other.idmedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.pedidoweb.modelo.Medida[ idmedida=" + idmedida + " ]";
    }
    
}
