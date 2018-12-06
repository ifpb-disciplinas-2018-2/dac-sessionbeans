package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoDAOIF;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ProdutoDAOIF.class)
public class ProdutoDAOJDBC implements ProdutoDAOIF {

    @Resource(lookup = "java:app/jdbc/sessionbeans")
    private DataSource dataSource;

    @Override
    public boolean salvar(Produto p) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO Produtos(descricao, valor) VALUES (?, ?);";
            int result = 0;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, p.getDescricao());
                stmt.setFloat(2, p.getValor());
                result = stmt.executeUpdate();
            }
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT * FROM Produtos;";
                try (ResultSet result = stmt.executeQuery(query)) {
                    produtos = getProdutos(result);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    @Override
    public boolean remover(Produto p) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM Pedidos WHERE codigo = ? ;";
            int result = 0;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, p.getCodigo());
                result = stmt.executeUpdate();
            }
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Produto buscar(String descricao) {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM Produtos WHERE descricao = ? ;";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, descricao);
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    return criarProduto(result);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO: retornar um Produto vazio (Null Object)
        return null;
    }

    private List<Produto> getProdutos(ResultSet result) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        while (result.next()) {
            produtos.add(criarProduto(result));
        }
        return produtos;
    }

    private Produto criarProduto(ResultSet result) throws SQLException {
        String descricao = result.getString("descricao");
        float valor = result.getFloat("valor");
        int codigo = result.getInt("codigo");
        return new Produto(descricao, valor, codigo);
    }

}
