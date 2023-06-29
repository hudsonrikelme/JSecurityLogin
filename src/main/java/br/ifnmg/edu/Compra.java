/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifnmg.edu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 *
 * @author @Daniel Alves F.N.&lt;Daniel Aluno do IFNMG&gt;
 */
@Entity
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 6, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    //cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //produtos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "compra_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    private Boolean pago = false;

    @Column(columnDefinition = "DATE")
    private LocalDate dia;

    @Enumerated(EnumType.ORDINAL)
    private TipoPagamento tipoLogradouro;

    public Compra() {
        
        produtos = new ArrayList();
    }

    //<editor-fold defaultstate="collapsed" desc="get e set">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public TipoPagamento getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoPagamento tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="hash e to string">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        
        return "Compra{" + "id=" + id + ", total=" + total + ", clientes=" + cliente.getNome() + ", produtos=" + produtos + ", pago=" + pago + ", dia=" + dia + ", tipoLogradouro=" + tipoLogradouro + '}';
    }

    
    //</editor-fold>

    public enum TipoPagamento {
        CARTAO,
        DINHEIRO,
        PIX,
        SALDO,
        AUXILIO;
    }

}
