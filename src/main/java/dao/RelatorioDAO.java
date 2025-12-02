package dao;

import modelo.EncontroServico;
import modelo.Encontro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioDAO {

    private final EncontroDAO encontroDAO;
    private final EncontroServicoDAO encontroServicoDAO;

    public RelatorioDAO() {
        this.encontroDAO = new EncontroDAO();
        this.encontroServicoDAO = new EncontroServicoDAO();
    }

    // Funcionalidade 6: Geração de Relatório (.txt)
    public String gerarRelatorioEncontro(int idEncontro, String caminhoArquivo) throws IOException {

        Encontro encontro = encontroDAO.buscarPorId(idEncontro);
        if (encontro == null) {
            return "Erro: Encontro com ID " + idEncontro + " não encontrado.";
        }

        List<EncontroServico> servicosDoEncontro = encontroServicoDAO.buscarServicosPorEncontro(idEncontro);

        // Formata a data para o padrão dd/MM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {

            writer.write("Data do Encontro: " + encontro.getDataEncontro().format(formatter));
            writer.newLine();
            writer.write("Serviços:");
            writer.newLine();

            // Monta a lista de serviços
            for (EncontroServico es : servicosDoEncontro) {
                String nomeServico = es.getServico().getNome();
                // Usa o nome da mãe responsável ou "Não Atribuído" se for nulo
                String nomeMae = es.getMaeResponsavel() != null ? es.getMaeResponsavel().getNome() : "Não Atribuído";

                // Exemplo de formato: MÚSICA: Fernanda
                writer.write(nomeServico + ": " + nomeMae);
                writer.newLine();

                // Opcional: Adicionar a descrição da atividade em outra linha
                if (es.getDescricaoAtividade() != null && !es.getDescricaoAtividade().trim().isEmpty()) {
                    writer.write("  (Descrição: " + es.getDescricaoAtividade() + ")");
                    writer.newLine();
                }
            }

            // Retorna o caminho onde o arquivo foi salvo
            return "Relatório gerado com sucesso em: " + caminhoArquivo;

        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo de relatório: " + e.getMessage());
            throw e; // Lança a exceção para ser tratada na GUI
        }
    }
}