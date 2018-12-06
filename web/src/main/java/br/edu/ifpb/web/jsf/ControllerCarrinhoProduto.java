package br.edu.ifpb.web.jsf;

import br.edu.ifpb.carrinho.CarrinhoProdutoIF;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoDAOIF;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author murillo
 */
@Named
@SessionScoped
public class ControllerCarrinhoProduto implements Serializable{
    
    @EJB
    private CarrinhoProdutoIF carrinho;
    private Produto produto;
    
    @EJB
    private ProdutoDAOIF dao;
    
    public List<Produto> listarCarrinho(){
        return carrinho.produtos();
    }
    
    public List<Produto> listarTodosProdutos(){
        return dao.listar();
    }
    
    public void adicionar(){
        carrinho.adicionar(this.produto);
    }
    
    public void remover(Produto p){
        carrinho.remover(p);
    }
    
    public String finalizar(){
        this.carrinho.finalizar();
        finalizarSessao();
        return "index.xhtml?faces-redirect=true";
    }
    
    private void finalizarSessao() {    
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();
    }
    
    public Produto getProduto(){
        return this.produto;
    }
    public void setProduto(Produto p){
        this.produto = p;
    }
    
}
