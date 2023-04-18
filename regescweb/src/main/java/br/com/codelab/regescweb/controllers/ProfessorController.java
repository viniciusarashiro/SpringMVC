package br.com.codelab.regescweb.controllers;

import br.com.codelab.regescweb.dto.RequisicaoNovoProfessor;
import br.com.codelab.regescweb.models.Professor;
import br.com.codelab.regescweb.models.StatusProfessor;
import br.com.codelab.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index() {


        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores")
    public String create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("\n********** TEM ERROS ***************");
            return "redirect:/professor/new";
        } else {
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);
            return "redirect:/professores";
        }

    }
 }
