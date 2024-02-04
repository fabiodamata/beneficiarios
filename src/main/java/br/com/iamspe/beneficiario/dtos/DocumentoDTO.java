package br.com.iamspe.beneficiario.dtos;

public class DocumentoDTO {

    private Long id;
    private Long beneficiarioId;
    private String descricao;
    private String tipoDocumento;

    public DocumentoDTO() {
    }

    public DocumentoDTO(Long id, String tipoDocumento, String descricao, Long beneficiarioId) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
        this.beneficiarioId = beneficiarioId;
    }

    //GETTERS
    public Long getId() {
        return id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getBeneficiarioId() {
        return beneficiarioId;
    }

    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setBeneficiarioId(Long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }
}
