package br.com.iamspe.beneficiario.dtos;

import br.com.iamspe.beneficiario.enums.RoleEnum;

public record UsuarioDto(
        String nome,
        String login,
        String senha,
        RoleEnum role
) {
}