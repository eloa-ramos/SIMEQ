package dao;

import factory.ConnectionFactory;
import modelo.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    private final Connection connection;

    public ServicoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    // Funcionalidade 3: Listar Serviços Fixos
    public List<Servico> listar() {
        String sql = "SELECT * FROM servico ORDER BY id_servico";
        List<Servico> servicos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdServico(rs.getInt("id_servico"));
                servico.setNome(rs.getString("nome"));
                servicos.add(servico);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços.", e);
        }
        return servicos;
    }
}