package br.edu.ifpb.carrinho;

import br.edu.ifpb.domain.Produto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author murillo
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 5)
@Remote(CarrinhoProdutoIF.class)
public class CarrinhoProduto implements CarrinhoProdutoIF {

    private List<Produto> produtos = new ArrayList<>();
    
    @Override
    public void adicionar(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public List<Produto> produtos() {
        return Collections.unmodifiableList(this.produtos);
    }

    @Override
    public void remover(Produto produto) {
        this.produtos.remove(produto);
    }

    @Remove
    @Override
    public void finalizar() {
        System.out.println("--- Produtos ----");
        this.produtos.forEach(System.out::println);
    }
    
}
