package br.com.codelab.regescweb.controllers;

import br.com.codelab.regescweb.dto.RequisicaoFormCurso;
import br.com.codelab.regescweb.models.Curso;
import br.com.codelab.regescweb.models.TipoCurso;
import br.com.codelab.regescweb.repositories.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping("")
    public ModelAndView index() {

        List<Curso> cursos = this.cursoRepository.findAll();

        ModelAndView mv = new ModelAndView("cursos/index");
        mv.addObject("cursos", cursos);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormCurso requisicao) {
        ModelAndView mv = new ModelAndView("cursos/new");
        mv.addObject("listaTipoCurso", TipoCurso.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormCurso requisicao, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            ModelAndView mv = new ModelAndView("cursos/new");
            mv.addObject("listaTipoCurso", TipoCurso.values());
            return mv;
        } else {
            Curso curso = requisicao.toCurso();
            this.cursoRepository.save(curso);
            return new ModelAndView("redirect:/cursos/" + curso.getCodigo());
        }

    }

    @GetMapping("/{codigo}")
    public ModelAndView show(@PathVariable Long codigo) {
        Optional<Curso> optional = this.cursoRepository.findById(codigo);

        if(optional.isPresent()) {
            Curso curso = optional.get();
            ModelAndView mv = new ModelAndView("cursos/show");

            mv.addObject("curso", curso);

            return mv;
        } else {
            return this.retornaErroCurso("SHOW ERROR: Curso #" + codigo + " não encontrado no banco!");
        }
    }

    @GetMapping("/{codigo}/edit")
    public ModelAndView edit(@PathVariable Long codigo, RequisicaoFormCurso requisicao) {
        Optional<Curso> optional = this.cursoRepository.findById(codigo);

        if(optional.isPresent()) {
            Curso curso = optional.get();
            requisicao.fromCurso(curso);
            ModelAndView mv = new ModelAndView("cursos/edit");
            mv.addObject("cursoCodigo", curso.getCodigo());
            mv.addObject("listaTipoCurso", TipoCurso.values());

            return mv;
        } else {
            return this.retornaErroCurso("EDIT ERROR: Curso #" + codigo + " não encontrado no banco!");
        }
    }

    @PostMapping("/{codigo}")
    public ModelAndView update(@PathVariable Long codigo, @Valid RequisicaoFormCurso requisicao, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("cursos/edit");
            mv.addObject("cursoCodigo", codigo);
            mv.addObject("listaTipoCurso", TipoCurso.values());
            return mv;
        } else {
            Optional<Curso> optional = this.cursoRepository.findById(codigo);

            if(optional.isPresent()) {
                Curso curso = requisicao.toCurso(optional.get());
                this.cursoRepository.save(curso);
                return new ModelAndView("redirect:/cursos/" + curso.getCodigo());
            } else {
                return this.retornaErroCurso("UPDATE ERROR: Curso #" + codigo + " não encontrado no banco!");

            }
        }
    }

    @GetMapping("/{codigo}/delete")
    public ModelAndView delete(@PathVariable Long codigo) {

        ModelAndView mv = new ModelAndView("redirect:/cursos");

        try {
            this.cursoRepository.deleteById(codigo);
            mv.addObject("mensagem", "Curso #" + codigo + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroCurso("DELETE ERROR: Curso #" + codigo + " não encontrado no banco!");
        }

        return mv;

    }

    private ModelAndView retornaErroCurso(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/cursos");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
