package br.com.iamspe.beneficiario.services;

import br.com.iamspe.beneficiario.dtos.BeneficiarioDTO;
import br.com.iamspe.beneficiario.dtos.BeneficiarioUpdateDTO;
import br.com.iamspe.beneficiario.dtos.DocumentoDTO;
import br.com.iamspe.beneficiario.entities.Beneficiario;
import br.com.iamspe.beneficiario.repositories.BeneficiarioRepository;
import br.com.iamspe.beneficiario.repositories.DocumentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private ModelMapper modelMapper;

    // Salva apenas o beneficiario...
    @Transactional
    public BeneficiarioDTO salvarBeneficiario(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = modelMapper.map(beneficiarioDTO, Beneficiario.class);
        beneficiario = beneficiarioRepository.save(beneficiario);
        return modelMapper.map(beneficiario, BeneficiarioDTO.class);
    }

    //Salva beneficiários com documentos, conforme solicitado...
    @Transactional
    public BeneficiarioDTO salvarComDocumentos(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = modelMapper.map(beneficiarioDTO, Beneficiario.class);

        //Salva o beneficiário primeiro....
        Beneficiario beneficiarioSalvo = beneficiarioRepository.save(beneficiario);

        if (!beneficiario.getDocumentos().isEmpty()) {
            beneficiarioSalvo.getDocumentos().forEach(documento -> documento.setBeneficiario(beneficiarioSalvo));
            documentoRepository.saveAll(beneficiarioSalvo.getDocumentos()); //Salva os documentos...
        }
        List<DocumentoDTO> documentosSalvos = beneficiarioSalvo.getDocumentos().stream()
                .map(documento -> modelMapper.map(documento, DocumentoDTO.class))
                .collect(Collectors.toList());

        BeneficiarioDTO beneficiarioSalvoDTO = modelMapper.map(beneficiarioSalvo, BeneficiarioDTO.class);
        beneficiarioSalvoDTO.setDocumentos(documentosSalvos);

        return beneficiarioSalvoDTO;
    }

    public List<BeneficiarioDTO> listarTodos() {
        List<Beneficiario> todosBeneficiarios = beneficiarioRepository.findAll();
        return todosBeneficiarios.stream()
                .map(beneficiario -> {
                    BeneficiarioDTO dto = modelMapper.map(beneficiario, BeneficiarioDTO.class);
                    dto.setDocumentos(null); // Remove os documentos da listagem
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public BeneficiarioDTO buscaPorId(Long id) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado. ID: " + id));
        return modelMapper.map(beneficiario, BeneficiarioDTO.class);
    }

    @Transactional
    public void removerPorId(Long id) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado. ID: " + id));
        beneficiarioRepository.delete(beneficiario);
    }

    @Transactional
    public BeneficiarioDTO atualizarBeneficiario(Long id, BeneficiarioUpdateDTO beneficiarioUpdateDTO) {
        Beneficiario beneficiarioExistente = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado. ID: " + id));

        // Atualiza apenas os campos permitidos
        modelMapper.map(beneficiarioUpdateDTO, beneficiarioExistente);
        beneficiarioExistente.setDataAtualizacao(LocalDateTime.now());

        Beneficiario beneficiarioAtualizado = beneficiarioRepository.save(beneficiarioExistente);

        return modelMapper.map(beneficiarioAtualizado, BeneficiarioDTO.class);
    }

}