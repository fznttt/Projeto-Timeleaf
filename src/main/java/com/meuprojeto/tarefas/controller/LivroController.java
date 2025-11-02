package com.meuprojeto.tarefas.controller;

import com.meuprojeto.tarefas.model.Livro;
import com.meuprojeto.tarefas.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroService.BuscarTodosOsLivros());
        return "livros";
    }

    @GetMapping("/add")
    public String formLivro(Livro livro) {
        return "livroAdd";
    }

    @GetMapping("edit/{id}")
    public String formLivro(@PathVariable("id") Long id, Model model) {
        Livro livro = livroService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Livro inválido" + id));
        model.addAttribute("livro", livro);
        return "livroAdd";
    }

    @PostMapping("/save")
    public String salvarLivro(@Valid Livro livro, BindingResult result) {
        if (result.hasErrors()) {
            return "livroAdd";
        }
        livroService.salvar(livro);
        return "redirect:/livros";
    }

    @GetMapping("/delete/{id}")
    public String deletarLivro(@PathVariable("id") Long id) {
        livroService.excluir(id);
        return "redirect:/livros";
    }
}
