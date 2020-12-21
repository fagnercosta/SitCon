package br.com.cin.sitcon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "termoReferencia")
public class TermoRereferencia {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tr")
    private Integer id;
    @Column(name = "objeto",length = 50000)
    private String objetoContratacao;
    @Column(name = "justificativa", length = 5000)
    private String justificativa;
    
    @Column(name = "requisitos")
    private String requisitos;
    @Column(name = "descricaoSolucaoTic")
    private String descricaoSolucao;
    @Column(name = "status")
    private String situacao;
    
    @Transient
    private boolean objetoIspresent;
    @Transient
    private boolean justificativaIspresent;
    @Transient
    private boolean requisitosIspresent;
    @Transient
    private boolean descricaoSolucaoIspresent;
    
    @OneToMany(mappedBy = "tr", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ItemObjetivoEstrategico> objetivos = new ArrayList<ItemObjetivoEstrategico>();
    
    @OneToMany(mappedBy = "tr", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ItemNecessidadeEssencial> necessidades = new ArrayList<ItemNecessidadeEssencial>();
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getObjetoContratacao() {
		return objetoContratacao;
	}
	public void setObjetoContratacao(String objetoContratacao) {
		this.objetoContratacao = objetoContratacao;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public String getRequisitos() {
		return requisitos;
	}
	
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getSituacao() {
		return situacao;
	}
	
	public void setNecessidades(List<ItemNecessidadeEssencial> necessidades) {
		this.necessidades = necessidades;
	}
	
	public List<ItemNecessidadeEssencial> getNecessidades() {
		return necessidades;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}
	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}
	public List<ItemObjetivoEstrategico> getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(List<ItemObjetivoEstrategico> objetivos) {
		this.objetivos = objetivos;
	}
	
	public void adicionarObjetivos(List<ItemObjetivoEstrategico> itens) {
		this.objetivos = itens;
		this.objetivos.forEach(t-> t.setTr(this));
	}
	
	public void adicionarNecessidades(List<ItemNecessidadeEssencial> itens) {
		this.necessidades = itens;
		this.necessidades.forEach(t-> t.setTr(this));
	}
	
	
	
	public boolean isJustificativaIspresent() {
		try {
			return justificativa !=(null) || !justificativa.isEmpty() ?  true : false;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean isDescricaoSolucaoIspresent() {
		try {
			return descricaoSolucao !=(null) || !descricaoSolucao.isEmpty() ?  true : false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isObjetoIspresent() {
		try {
			return objetoContratacao !=(null) || !objetoContratacao.isEmpty() ?  true : false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isRequisitosIspresent() {
		try {
			return requisitos !=(null) || !requisitos.isEmpty() ?  true : false;
		} catch (Exception e) {
			return false;
		}
	}
	
	
    
    
    
    

}
