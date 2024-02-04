package br.com.iamspe.beneficiario.services;

import br.com.iamspe.beneficiario.dtos.DocumentoDTO;
import br.com.iamspe.beneficiario.entities.Documento;
import br.com.iamspe.beneficiario.repositories.DocumentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public DocumentoDTO criarDocumento(DocumentoDTO documentoDTO) {
        Documento documento = modelMapper.map(documentoDTO, Documento.class);
        Documento novoDocumento = documentoRepository.save(documento);
        return modelMapper.map(novoDocumento, DocumentoDTO.class);
    }

    @Transactional
    public DocumentoDTO atualizarDocumento(Long id, DocumentoDTO documentoDTO) {
        Documento documentoExistente = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado com ID: " + id));
        modelMapper.map(documentoDTO, documentoExistente);
        documentoExistente = documentoRepository.save(documentoExistente);
        return modelMapper.map(documentoExistente, DocumentoDTO.class);
    }

    public List<DocumentoDTO> listarDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        return documentos.stream()
                .map(documento -> modelMapper.map(documento, DocumentoDTO.class))
                .collect(Collectors.toList());
    }

    public DocumentoDTO buscarPorId(Long id) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado com ID: " + id));
        return modelMapper.map(documento, DocumentoDTO.class);
    }

    @Transactional
    public void removerDocumento(Long id) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado com ID: " + id));
        documentoRepository.delete(documento);
    }

    public List<DocumentoDTO> listarDocumentosPorBeneficiario(Long beneficiarioId) {
        List<Documento> documentos = documentoRepository.findByBeneficiarioId(beneficiarioId);
        return documentos.stream()
                .map(documento -> modelMapper.map(documento, DocumentoDTO.class))
                .collect(Collectors.toList());
    }
}
