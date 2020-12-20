package br.com.cin.sitcon.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.lang.NonNull;
import org.thymeleaf.expression.Sets;




@Entity
@Table(name = "demanda")
public class Demanda implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_demanda")
    private Integer id;
    
    @Column(name = "objeto")
    private String objeto;
    @Transient
    private String necessidade;
    @Column(name = "situacao")
    private String situacao;
    
    @Column(name = "justificativa")
    private String justificativa;
    @Transient
    private boolean justificativaConsistente;
    
    
	//@NotNull(message = "Campo Responsável é obriogatorio!")
	@NotBlank(message = "Campo Responsável é obriogatorio!")
    @Column(name = "responsavel")
    private String responsavel;
    
	@NotBlank(message = "Campo Setor sResponsável é obriogatorio!")
    @Column(name = "setorResponsavel")
    private String setor;
	
	@OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ItemObjetivoEstrategico> objetivos = new ArrayList<ItemObjetivoEstrategico>();
	
	@OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ItemNecessidadeEssencial> necessidades = new ArrayList<ItemNecessidadeEssencial>();
    
	@OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ItemPAC> itensPAC = new ArrayList<ItemPAC>();
	
    public void setId(Integer id) {
		this.id = id;
	}
    
    public String getSituacao() {
		return situacao;
	}
    
    public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
    
    public Integer getId() {
		return id;
	}
    
    public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
    
    public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
    
    public String getJustificativa() {
		return justificativa;
	}
    
    public String getObjeto() {
		return objeto;
	}

	public String getResponsavel() {
		return responsavel;
	}
	
	public void setNecessidade(String necessidade) {
		this.necessidade = necessidade;
	}
	
	public String getNecessidade() {
		return necessidade;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	public void setObjetivos(List<ItemObjetivoEstrategico> objetivos) {
		this.objetivos = objetivos;
	}
	
	public List<ItemObjetivoEstrategico> getObjetivos() {
		return objetivos;
	}
	
	public void setItensPAC(List<ItemPAC> itensPAC) {
		this.itensPAC = itensPAC;
	}
	
	public List<ItemPAC> getItensPAC() {
		return itensPAC;
	}
	
	public void adicionarItensObjetivos(List<ItemObjetivoEstrategico> itens) {
		this.objetivos = itens;
		this.objetivos.forEach(a -> a.setDemanda(this));
	}
	
	public void setNecessidades(List<ItemNecessidadeEssencial> itens) {
		this.necessidades = itens;
	}
	
	public List<ItemNecessidadeEssencial> getNecessidades() {
		return necessidades;
	}
	
	public void adicionarItensNecessidades(List<ItemNecessidadeEssencial> itens) {
		this.necessidades = itens;
		this.necessidades.forEach(a -> a.setDemanda(this));
	}
	
	public void adicionarItensPac(List<ItemPAC> itens) {
		this.itensPAC = itens;
		this.itensPAC.forEach(a -> a.setDemanda(this));
	}
	
	public boolean isJusttificativaConsistente() {
		return !this.justificativa.isEmpty();
	}
    
    

}
