package br.com.alura.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record CadastroTopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
