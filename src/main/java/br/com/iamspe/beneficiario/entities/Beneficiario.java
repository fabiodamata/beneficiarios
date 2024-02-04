package br.com.iamspe.beneficiario.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Set;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Beneficiario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String telefone;

    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    //@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    //private boolean ativo;

    @ApiModelProperty(hidden = true)
    private LocalDateTime dataInclusao;

    @ApiModelProperty(hidden = true)
    private LocalDateTime dataAtualizacao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiario")
    private Set<Documento> documentos;

    public Beneficiario() {
    }

    public Beneficiario(Long id, String nome, String telefone, LocalDate dataNascimento, boolean ativo, LocalDateTime dataInclusao, LocalDateTime dataAtualizacao, Set<Documento> documentos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        //this.ativo = ativo;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.documentos = documentos;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    //public boolean isAtivo() {
    //    return ativo;
    //}

    //public void setAtivo(boolean ativo) {
    //    this.ativo = ativo;
    //}

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    @PrePersist //Inclui a data criação automaticamnte...
    protected void onCreate() {
        this.dataInclusao = LocalDateTime.now();
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @PreUpdate //Atualiza a data de atualização durante a atualização...
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    //public void desativar() {
    //    this.ativo = false;
    //}
}
