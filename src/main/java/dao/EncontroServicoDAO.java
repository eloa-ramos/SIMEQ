package dao;

import factory.ConnectionFactory;
import modelo.Encontro;
import modelo.EncontroServico;
import modelo.Mae;
import modelo.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncontroServicoDAO {

    private final Connection connection;
    private final MaeDAO maeDAO;
    private final EncontroDAO encontroDAO; // Será útil para buscar o Encontro se necessário

    public EncontroServicoDAO() {
        this.connection = new ConnectionFactory().getConnection();
        // Inicializa DAOs dependentes para facilitar a busca e montagem dos objetos
        this.maeDAO = new MaeDAO();
        this.encontroDAO = new EncontroDAO();
    }

    // Funcionalidade 2: Registrar um serviço específico em um encontro
    public void registrarServicoEmEncontro(EncontroServico es) {
        // Tenta inserir; se a chave única já existir, atualiza (ON DUPLICATE KEY UPDATE)
        String sql = "INSERT INTO encontro_servico (id_encontro, id_servico, id_mae_responsavel, descricao_atividade) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "id_mae_responsavel = VALUES(id_mae_responsavel), " +
                "descricao_atividade = VALUES(descricao_atividade)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, es.getEncontro().getIdEncontro());
            stmt.setInt(2, es.getServico().getIdServico());

            // Lida com Mãe Responsável opcional (NULL no banco)
            if (es.getMaeResponsavel() != null) {
                stmt.setInt(3, es.getMaeResponsavel().getIdMae());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.setString(4, es.getDescricaoAtividade());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar ou atualizar EncontroServico.", e);
        }
    }

    // Método para buscar todos os serviços de um encontro
    public List<EncontroServico> buscarServicosPorEncontro(int idEncontro) {
        // Realiza um JOIN para obter o nome do serviço e o nome da mãe responsável
        String sql = "SELECT es.id_encontro_servico, es.descricao_atividade, " +
                "s.id_servico, s.nome AS nome_servico, " +
                "m.id_mae, m.nome AS nome_mae_responsavel " +
                "FROM encontro_servico es " +
                "JOIN servico s ON es.id_servico = s.id_servico " +
                "LEFT JOIN mae m ON es.id_mae_responsavel = m.id_mae " +
                "WHERE es.id_encontro = ? ORDER BY s.id_servico";

        List<EncontroServico> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEncontro);

            try (ResultSet rs = stmt.executeQuery()) {

                // Busca o objeto Encontro uma única vez (ou pode ser injetado)
                Encontro encontro = encontroDAO.buscarPorId(idEncontro);

                while (rs.next()) {
                    EncontroServico es = new EncontroServico();
                    es.setIdEncontroServico(rs.getInt("id_encontro_servico"));
                    es.setDescricaoAtividade(rs.getString("descricao_atividade"));
                    es.setEncontro(encontro);

                    // Cria e define o objeto Servico
                    Servico servico = new Servico();
                    servico.setIdServico(rs.getInt("id_servico"));
                    servico.setNome(rs.getString("nome_servico"));
                    es.setServico(servico);

                    // Cria e define o objeto Mae (se houver responsável)
                    int idMae = rs.getInt("id_mae");
                    if (!rs.wasNull()) {
                        Mae mae = new Mae();
                        mae.setIdMae(idMae);
                        mae.setNome(rs.getString("nome_mae_responsavel"));
                        es.setMaeResponsavel(mae);
                    }

                    lista.add(es);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviços por encontro.", e);
        }
        return lista;
    }
}