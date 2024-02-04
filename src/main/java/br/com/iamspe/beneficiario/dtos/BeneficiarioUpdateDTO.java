package br.com.iamspe.beneficiario.dtos;

import java.time.LocalDate;

public class BeneficiarioUpdateDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;

    // Construtores
    public BeneficiarioUpdateDTO() {
    }

    public BeneficiarioUpdateDTO(Long id, String nome, LocalDate dataNascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
