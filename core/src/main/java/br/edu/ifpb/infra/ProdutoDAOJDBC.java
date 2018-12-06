package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Produto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import br.edu.ifpb.domain.ProdutoDAOIF;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ProdutoDAOIF.class)
public class ProdutoDAOJDBC implements ProdutoDAOIF{

    @Resource(lookup = "java:app/jdbc/sessionbeans")
    private DataSource dataSource;
    
    @Override
    public boolean salvar(Produto p) {
        try(Connection conn = dataSource.getConnection()){
            
            String sql = "INSERT INTO Produtos(descricao, valor) "
                       + "VALUES (?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getDescricao());
            stmt.setFloat(2, p.getValor());
            
            int result = stmt.executeUpdate();
            stmt.close();
            return result == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Produto> listar() {
        try(Connection conn = dataSource.getConnection()){
            
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Produtos;";
            ResultSet result = stmt.executeQuery(query);
            
            List<Produto> produtos = getProdutos(result);
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean remover(Produto p) {
        try(Connection conn = dataSource.getConnection()){
            
            String sql = "DELETE FROM Pedidos WHERE codigo = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getCodigo());
            
            int result = stmt.executeUpdate();
            stmt.close();            
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Produto buscar(String descricao) {
        try(Connection conn = dataSource.getConnection()){
            String query = "SELECT * FROM Produtos WHERE descricao = ? ;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, descricao);
            
            ResultSet result = stmt.executeQuery();
            result.next();
            float valor = result.getFloat("valor");
            int codigo = result.getInt("codigo");
            Produto p = new Produto(descricao, valor, codigo);
            
            stmt.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<Produto> getProdutos(ResultSet result) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        while(result.next()){
            
            String descricao = result.getString("descricao");
            float valor = result.getFloat("valor");
            int codigo = result.getInt("codigo");
            
            Produto p = new Produto(descricao, valor, codigo);
            produtos.add(p);            
        }
        return produtos;
    }
    
}
