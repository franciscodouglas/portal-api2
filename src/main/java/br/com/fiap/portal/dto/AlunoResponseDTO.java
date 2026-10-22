package br.com.fiap.portal.dto;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone
) {
}
