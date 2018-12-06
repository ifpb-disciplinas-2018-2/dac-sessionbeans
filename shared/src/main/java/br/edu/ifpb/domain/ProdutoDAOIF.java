package br.edu.ifpb.domain;

import java.util.List;

public interface ProdutoDAOIF {
    
    public boolean salvar(Produto p);
    public List<Produto> listar();
    public boolean remover(Produto p);
    public Produto buscar(String descricao);
    
}
