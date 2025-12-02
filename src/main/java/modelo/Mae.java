package modelo;

import java.time.LocalDate;

public class Mae {
    // Funcionalidade 1: Nome, Telefone, Endereço, Data de aniversário
    private int idMae;
    private String nome;
    private String telefone;
    private String endereco;
    private LocalDate dataAniversario; // Usando LocalDate para datas

    // Construtor
    public Mae() {
    }

    // Construtor completo (sem ID)
    public Mae(String nome, String telefone, String endereco, LocalDate dataAniversario) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataAniversario = dataAniversario;
    }

    // Getters e Setters
    public int getIdMae() {
        return idMae;
    }

    public void setIdMae(int idMae) {
        this.idMae = idMae;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }
}