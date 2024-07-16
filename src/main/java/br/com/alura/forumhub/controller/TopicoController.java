package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.infra.exception.ValidacaoException;
import br.com.alura.forumhub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroTopicoDTO dados, UriComponentsBuilder uriBuilder) {
        var topico = new Topico(dados);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoTopicoDTO(topico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTopicoDTO>> listagem(@PageableDefault(size = 10,sort = "dataCriacao") Pageable paginacao) {
        var page = topicoRepository.findAllByStatusTrue(paginacao).map(ListagemTopicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topicoPresente = topicoRepository.findById(id);
        if (topicoPresente.isPresent()){
            var topico = topicoRepository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhamentoTopicoDTO(topico));
        }
        else{
            throw new ValidacaoException("O tópico não foi encontrado.");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoTopicoDTO dados, @PathVariable Long id) {
        Optional<Topico> topicoPresente = topicoRepository.findById(id);
        if (topicoPresente.isPresent()){
            var topico = topicoPresente.get();
            topico.atualizar(dados);

            return ResponseEntity.ok(new DetalhamentoTopicoDTO(topico));
        }
        else{
            throw new ValidacaoException("O tópico não foi encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Optional<Topico> topicoPresente = topicoRepository.findById(id);
        if (topicoPresente.isPresent()){
            var topico = topicoRepository.getReferenceById(id);
            topico.excluir();

            return ResponseEntity.noContent().build();
        }
        else{
            throw new ValidacaoException("O tópico não foi encontrado.");
        }
    }
}
