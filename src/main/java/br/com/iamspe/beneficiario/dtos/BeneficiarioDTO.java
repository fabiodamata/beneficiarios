package br.com.iamspe.beneficiario.dtos;

import java.time.LocalDate;
import java.util.List;

public class BeneficiarioDTO {
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    //private boolean ativo;

    private List<DocumentoDTO> documentos;

    public BeneficiarioDTO() {}

    public BeneficiarioDTO(Long id, String nome, String telefone, LocalDate dataNascimento, boolean ativo, List<DocumentoDTO> documentos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        //this.ativo = ativo;
        this.documentos = documentos;
    }

    //GETTERS
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    //public boolean isAtivo() {
    //    return ativo;
    //}

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    //public void setAtivo(boolean ativo) {
    //    this.ativo = ativo;
    //}

    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }
}

