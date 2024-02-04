package br.com.iamspe.beneficiario.services;

import br.com.iamspe.beneficiario.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {
    public String obterToken(AuthDto authDto);
    public String validaTokenJwt(String token);
}