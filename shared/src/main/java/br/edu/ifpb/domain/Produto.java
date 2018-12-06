package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author murillo
 */
public class Produto implements Serializable{
    
    private String descricao;
    private float valor;
    private int codigo;

    public Produto() {
    }

    public Produto(String descricao, float valor, int codigo) {
        this.descricao = descricao;
        this.valor = valor;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Float.floatToIntBits(this.valor);
        hash = 53 * hash + this.codigo;
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
        final Produto other = (Produto) obj;
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "descricao=" + descricao + ", valor=" + valor + ", codigo=" + codigo + '}';
    }
    
}
