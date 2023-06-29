/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Gustavo Rafael Nunes Durães &lt;grnd at aluno.ifnmg.edu.br&gt;
 */
@Entity
public class Prato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (joinColumns = @JoinColumn(name ="componente_id"),
            inverseJoinColumns = @JoinColumn(name = "prato_id") )
    private List<Componente> componentes;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Prato() {
        this.componentes = new ArrayList<>();
    }

    public Prato(String nome, List<Componente> componentes) {
        this.nome = nome;
        this.componentes = componentes;
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

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hashcode/Equals/toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        return "Prato{" + "id=" + id + ", nome=" + nome + ", componentes=" + componentes + '}';
    }
    //</editor-fold>

}
