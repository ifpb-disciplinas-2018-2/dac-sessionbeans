package br.edu.ifpb.carrinho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/12/2018, 09:50:32
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 5)
@Remote(Carrinho.class)
public class CarrinhoDeCompras implements Carrinho {

    private List<String> produtos = new ArrayList<String>();

    @PostConstruct
    public void iniciar(){
        this.produtos.add("TV");
        this.produtos.add("PC");
        this.produtos.add("Arroz");
    }
    
    @Override
    public void adicionar(String produto) {
        this.produtos.add(produto);
    }

    @Override
    public void remover(String produto) {
        this.produtos.remove(produto);
    }

    @Override
    public List<String> produtos() {
        return Collections.unmodifiableList(
                this.produtos
        );
    }

    @Remove
    @Override
    public void finalizar() {
        System.out.println("--- Produtos ----");
        this.produtos.forEach(System.out::println);
    }
}
