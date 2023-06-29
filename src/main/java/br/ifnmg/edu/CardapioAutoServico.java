/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu;

import java.io.Serializable;
import java.time.LocalDate;
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
public class CardapioAutoServico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dia;

   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (joinColumns = @JoinColumn(name ="componente_id"),
            inverseJoinColumns = @JoinColumn(name = "cardapioAutoServico_id") )
    private List<Componente> componentes;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public CardapioAutoServico() {
        this.componentes = new ArrayList<>();
    }

    public CardapioAutoServico(LocalDate dia, List<Componente> componentes) {
        this.dia = dia;
        this.componentes = componentes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Geteres/Seteres">
    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="HashCode/Equals/toString">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        return "CardapioAutoServico{" + "id=" + id + ", dia=" + dia + ", componentes=" + componentes + '}';
    }
    //</editor-fold>

}
