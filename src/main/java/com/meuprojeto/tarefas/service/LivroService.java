package com.meuprojeto.tarefas.service;

import com.meuprojeto.tarefas.model.Livro;
import com.meuprojeto.tarefas.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> BuscarTodosOsLivros() {
        return livroRepository.findAll();
    }

    public Livro salvar(Livro livro) {
        if(livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O titulo do livro deve ser preenchido!");
        }

        if(livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("O autor do livro deve ser preenchido!");
        }

        return livroRepository.save(livro);
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }
}
