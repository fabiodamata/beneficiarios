    package br.com.iamspe.beneficiario.controllers;

import br.com.iamspe.beneficiario.dtos.BeneficiarioDTO;
import br.com.iamspe.beneficiario.dtos.BeneficiarioUpdateDTO;
import br.com.iamspe.beneficiario.services.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    //@PostMapping()
    //@ResponseStatus(HttpStatus.CREATED)
    //public BeneficiarioDTO criarBeneficiario(@RequestBody BeneficiarioDTO beneficiarioDTO) {
    //    BeneficiarioDTO beneficiarioCriado = beneficiarioService.salvarBeneficiario(beneficiarioDTO);
    //    return beneficiarioCriado;
    //}

    @PostMapping
    public ResponseEntity<BeneficiarioDTO> criarBeneficiarioComDocumentos(@RequestBody BeneficiarioDTO beneficiarioDTO) {
        BeneficiarioDTO beneficiarioSalvoDTO = beneficiarioService.salvarComDocumentos(beneficiarioDTO);
        return new ResponseEntity<>(beneficiarioSalvoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BeneficiarioDTO>> listarBeneficiarios() {
        List<BeneficiarioDTO> todosBeneficiarios = beneficiarioService.listarTodos();
        return ResponseEntity.ok(todosBeneficiarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiarioDTO> buscarBeneficiarioPorId(@PathVariable("id") Long id) {
        BeneficiarioDTO beneficiarioDTO = beneficiarioService.buscaPorId(id);
        return ResponseEntity.ok(beneficiarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerBeneficiario(@PathVariable("id") Long id) {
        beneficiarioService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioDTO> atualizarBeneficiario(@PathVariable("id") Long id, @RequestBody BeneficiarioUpdateDTO beneficiarioUpdateDTO) {
        BeneficiarioDTO beneficiarioAtualizadoDTO = beneficiarioService.atualizarBeneficiario(id, beneficiarioUpdateDTO);
        return ResponseEntity.ok(beneficiarioAtualizadoDTO);
    }
}
