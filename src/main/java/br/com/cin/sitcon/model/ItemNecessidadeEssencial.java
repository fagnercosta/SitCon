package br.com.cin.sitcon.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ItemNecessidadeEssencial")
public class ItemNecessidadeEssencial implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descricaoDetalhada")
    private String descricaoDetalhada;
    
    @Transient
    public boolean descricaoNecessidadeConsitente;
    
    @ManyToOne
	@JoinColumn(name = "id_demanda")
	private Demanda demanda;
    
    @Transient
    private boolean necessidadeInformada;
    @Transient
    private boolean necessidadeConsistente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}
	
	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
	public Demanda getDemanda() {
		return demanda;
	}
	
	public void setDescricaoNecessidadeConsitente(boolean descricaoNecessidadeConsitente) {
		this.descricaoNecessidadeConsitente = descricaoNecessidadeConsitente;
	}
	
	public boolean isDescricaoNecessidadeConsitente() {
		return descricaoNecessidadeConsitente;
	}
	public void setNecessidadeInformada(boolean necessidadeInformada) {
		this.necessidadeInformada = necessidadeInformada;
	}
	/***
	 * 
	 * @return
	 */
	public boolean isNecessidadeInformada() {
		if(this.descricaoDetalhada.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean isNecessidadeConsistente() {
		return necessidadeConsistente;
	}
	
	public void setNecessidadeConsistente(boolean necessidadeConsistente) {
		this.necessidadeConsistente = necessidadeConsistente;
	}
    
    

}
