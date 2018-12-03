package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/10/2018, 10:26:28
 */
@Stateless
@Remote(Clientes.class)
public class ClientesEmJDBC implements Clientes {

    @Resource(lookup = "java:app/jdbc/sessionbeans")
//    @Resource(lookup = "java:app/jdbc/sessionbeans-docker")
    private DataSource dataSource;

//    private Connection connection;
    @Override
    public void novo(Cliente cliente) {
        try (Connection connection = this.dataSource.getConnection()) {
            PreparedStatement createStatement = connection.prepareStatement(
                    "INSERT INTO clientes VALUES (?,?);"
            );
            createStatement.setString(1, cliente.getNome());
            createStatement.setString(2, cliente.getCpf());
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Cliente> todosOsClientes() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection()) {
            Statement createStatement = connection.createStatement();
            ResultSet result = createStatement.executeQuery(
//                    "SELECT * FROM Clientes LIMIT 2;"
                    "SELECT * FROM Clientes;"
            );
            iterarComClientes(result, lista);
//            lista = lista.subList(0, 1);
            createStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;//.subList(1, 1);
    }

    private void iterarComClientes(ResultSet result, List<Cliente> lista) throws SQLException {
        while (result.next()) {
            lista.add(
                    criarCliente(result)
            );
        }
    }

    private Cliente criarCliente(ResultSet result) throws SQLException {
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        int id = result.getInt("id");

        return new Cliente(id, nome, cpf);

    }

//    @PostConstruct
//    public void iniciar() {
//        try {
//            Class.forName("org.postgresql.Driver");
//            this.connection = DriverManager.getConnection(
//                    "jdbc:postgresql://host-banco:5432/clientes",
//                    "job", "123"
//            );
//        } catch (Exception ex) {
//            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @PreDestroy
//    public void finalizar() {
//        try {
//            this.connection.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
