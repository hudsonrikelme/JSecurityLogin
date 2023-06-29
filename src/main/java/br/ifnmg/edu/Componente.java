/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@Entity
public class Componente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String nome;

    private Float caloria;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Componente() {
    }

    public Componente(String nome, Float caloria, Categoria categoria) {
        this.nome = nome;
        this.caloria = caloria;
        this.categoria = categoria;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Geteres/Seteres">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCaloria() {
        return caloria;
    }

    public void setCaloria(Float caloria) {
        this.caloria = caloria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hashcode/Equals/toString">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", nome=" + nome + ", caloria=" + caloria + ", categoria=" + categoria + '}';
    }
    //</editor-fold>

}
