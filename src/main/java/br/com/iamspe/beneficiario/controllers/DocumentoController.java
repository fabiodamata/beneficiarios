package br.com.iamspe.beneficiario.controllers;

import br.com.iamspe.beneficiario.dtos.DocumentoDTO;
import br.com.iamspe.beneficiario.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<DocumentoDTO> criarDocumento(@RequestBody DocumentoDTO documentoDTO) {
        DocumentoDTO documentoCriadoDTO = documentoService.criarDocumento(documentoDTO);
        return new ResponseEntity<>(documentoCriadoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> listarDocumentos() {
        List<DocumentoDTO> todosDocumentos = documentoService.listarDocumentos();
        return ResponseEntity.ok(todosDocumentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> buscarPorId(@PathVariable Long id) {
        DocumentoDTO documentoDTO = documentoService.buscarPorId(id);
        return ResponseEntity.ok(documentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDocumento(@PathVariable Long id) {
        documentoService.removerDocumento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> atualizarDocumento(@PathVariable Long id, @RequestBody DocumentoDTO documentoDTO) {
        DocumentoDTO documentoAtualizadoDTO = documentoService.atualizarDocumento(id, documentoDTO);
        return ResponseEntity.ok(documentoAtualizadoDTO);
    }

    @GetMapping("/beneficiario/{beneficiarioId}")
    public ResponseEntity<List<DocumentoDTO>> listarDocumentosPorBeneficiario(@PathVariable Long beneficiarioId) {
        List<DocumentoDTO> documentos = documentoService.listarDocumentosPorBeneficiario(beneficiarioId);
        return ResponseEntity.ok(documentos);
    }
}
