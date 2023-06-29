/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.ifnmg.edu;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Lucas
 */
@Named(value = "testeBean")
@Dependent
public class testeBean {

    private String nome;

    /**
     * Creates a new instance of testeBean
     */
    public testeBean() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
