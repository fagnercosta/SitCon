/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cin.sitcon.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.thymeleaf.expression.Sets;

/**
 *
 * @author 2192525
 */
@Entity
@Table(name = "pac")
@NamedQueries({
    @NamedQuery(name = "Pac.findAll", query = "SELECT p FROM Pac p")})
public class Pac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "orgao")
    private String orgao;
    @Column(name = "uasg")
    private String uasg;
    @Column(name = "anoPlano")
    private Integer anoPlano;
    @Column(name = "tipoItem")
    private String tipoItem;
    @Column(name = "subTipo")
    private String subTipo;
    @Column(name = "codigoItem")
    private Integer codigoItem;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "descricaoDetalhada")
    private String descricaoDetalhada;
    @Column(name = "numero")
    private Integer numero;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public void setSubTipo(String subTipo) {
		this.subTipo = subTipo;
	}
	
	public String getSubTipo() {
		return subTipo;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public String getUasg() {
		return uasg;
	}

	public void setUasg(String uasg) {
		this.uasg = uasg;
	}

	public Integer getAnoPlano() {
		return anoPlano;
	}

	public void setAnoPlano(Integer anoPlano) {
		this.anoPlano = anoPlano;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Integer getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(Integer codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pac)) {
            return false;
        }
        Pac other = (Pac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sitcon.Pac[ id=" + id + " ]";
    }
    
}
