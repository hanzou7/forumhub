package br.com.alura.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record AtualizacaoTopicoDTO(
        @NotBlank
        String mensagem,
        @NotBlank
        String titulo) {
}
