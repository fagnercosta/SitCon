package br.com.cin.sitcon.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ItemPAC")
public class ItemPAC {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
	@JoinColumn(name = "id_demanda")
	private Demanda demanda;
    
    @Column(name = "descricao")
    private String descricaoObjeto;
    @Column(name = "codigo")
    private Integer codigo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoObjeto() {
		return descricaoObjeto;
	}
	public void setDescricaoObjeto(String descricaoObjeto) {
		this.descricaoObjeto = descricaoObjeto;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
	public Demanda getDemanda() {
		return demanda;
	}
    
    
   

}
