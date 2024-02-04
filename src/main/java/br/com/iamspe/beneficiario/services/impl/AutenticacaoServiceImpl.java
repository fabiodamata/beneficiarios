package br.com.iamspe.beneficiario.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import br.com.iamspe.beneficiario.dtos.AuthDto;
import br.com.iamspe.beneficiario.entities.Usuario;
import br.com.iamspe.beneficiario.repositories.UsuarioRepository;
import br.com.iamspe.beneficiario.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public String obterToken(AuthDto authDto) {
        Usuario usuario = usuarioRepository.findByLogin(authDto.login());
        return geraTokenJwt(usuario);
    }

    public  String geraTokenJwt(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("K$;(KuRR'5)E!+vFFTda850_6NW7VRb;o");

            return JWT.create()
                    .withIssuer("beneficiario")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token! " +exception.getMessage());
        }
    }

    public String validaTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("K$;(KuRR'5)E!+vFFTda850_6NW7VRb;o");

            return JWT.require(algorithm)
                    .withIssuer("beneficiario")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant geraDataExpiracao() {
        return LocalDateTime.now()
                .plusHours(1) //UMA HORAS PARA EXPIRAR O TOKEN
                .toInstant(ZoneOffset.of("-03:00")); //HORÁRIO DE BRASILIA
    }
}