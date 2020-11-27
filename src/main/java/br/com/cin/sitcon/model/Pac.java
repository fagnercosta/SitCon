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
    @Column(name = "descricaoBem")
    private String descricaoBem;
    @Column(name = "descricaoServico")
    private String descricaoServico;
    @Column(name = "codigoCatMaT")
    private Integer codigoCatMaT;
    @Column(name = "codigoCatSer")
    private Integer codigoCatSer;
    @OneToMany(mappedBy = "objetivoEstrategico", fetch = FetchType.LAZY)
    private List<Necessidade> necessidadeList;

    public Pac() {
    }

    public Pac(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoBem() {
        return descricaoBem;
    }

    public void setDescricaoBem(String descricaoBem) {
        this.descricaoBem = descricaoBem;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Integer getCodigoCatMaT() {
        return codigoCatMaT;
    }

    public void setCodigoCatMaT(Integer codigoCatMaT) {
        this.codigoCatMaT = codigoCatMaT;
    }

    public Integer getCodigoCatSer() {
        return codigoCatSer;
    }

    public void setCodigoCatSer(Integer codigoCatSer) {
        this.codigoCatSer = codigoCatSer;
    }

    public List<Necessidade> getNecessidadeList() {
        return necessidadeList;
    }

    public void setNecessidadeList(List<Necessidade> necessidadeList) {
        this.necessidadeList = necessidadeList;
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
