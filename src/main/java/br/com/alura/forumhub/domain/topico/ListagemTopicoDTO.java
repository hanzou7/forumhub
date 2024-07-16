package br.com.alura.forumhub.domain.topico;

import java.time.LocalDateTime;

public record ListagemTopicoDTO(Long id, String titulo, String mensagem, LocalDateTime data, Boolean status, String autor, String curso) {

    public ListagemTopicoDTO(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
