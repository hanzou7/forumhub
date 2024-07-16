package br.com.alura.forumhub.domain.topico;

public record DetalhamentoTopicoDTO(Long id, String titulo, String mensagem, String autor, String curso) {
    public DetalhamentoTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}
