package br.ifnmg.edu;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Lucas Freitas &lt;lpf1 at ifnmg.edu.br&gt;
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Credencial.all", 
            query = "select c from Credencial c "
                    + "order by c.id"),
    @NamedQuery(
            name = "Credencial.byemail", 
            query = "select c from Credencial c "
                    + "where c.email = :email")
})
public class Credencial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente = TipoCliente.GERENTE;

    @OneToOne(mappedBy = "credencial",
            cascade = CascadeType.ALL)
    private Cliente cliente;

    public Credencial() {

    }

    public Credencial(String email, String senha, Cliente cliente) {
        this.email = email;
        this.senha = senha;
        this.cliente = cliente;
        tipoCliente = TipoCliente.GERENTE;
    }
    //<editor-fold defaultstate="collapsed" desc="Getters / Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
  
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Hash / Equals / ToString">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credencial)) {
            return false;
        }
        Credencial other = (Credencial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//</editor-fold>
    
    public enum TipoCliente {
        CLIENTE("CLIENTE"),
        GERENTE("GERENTE"),
        NUTRICIONISTA("NUTRICIONISTA"),
        CAIXA("CAIXA");

        private String rotulo;

        private TipoCliente(String rotulo) {
            this.rotulo = rotulo;
        }

        public String getRotulo() {
            return rotulo;
        }
    }
}
