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

import br.com.cin.sitcon.avaliacao.AvaliacaoDOD;
import br.com.cin.sitcon.similarity.CosineSimilarity;


@Entity
@Table(name = "ItemObjetivoEstrategico")
public class ItemObjetivoEstrategico implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "descricao")
    private String descricaoObjetivo;
    
    @Transient
    public boolean descricaoObjetivoConsitente;
    
    @ManyToOne
	@JoinColumn(name = "id_demanda")
	private Demanda demanda;
    
    @ManyToOne
   	@JoinColumn(name = "id_tr")
   	private TermoRereferencia tr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDescricaoObjetivoConsitente(boolean descricaoObjetivoConsitente) {
		this.descricaoObjetivoConsitente = descricaoObjetivoConsitente;
	
	}
	
	public boolean isDescricaoObjetivoConsitente() {
		return this.descricaoObjetivoConsitente;
	}

	public String getDescricaoObjetivo() {
		return descricaoObjetivo;
	}

	public void setDescricaoObjetivo(String descricaoObjetivo) {
		this.descricaoObjetivo = descricaoObjetivo;
	}

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
	public void setTr(TermoRereferencia tr) {
		this.tr = tr;
	}
	
	public TermoRereferencia getTr() {
		return tr;
	}
    
    

}
