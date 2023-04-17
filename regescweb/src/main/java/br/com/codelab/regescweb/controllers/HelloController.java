package br.com.codelab.regescweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("hello"); // nome do arquivo html a ser renderizado
        System.out.println("******************");
        mv.addObject("nome", "Maria");
        return mv;
    }

    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request) {
        request.setAttribute("nome", "Samuka");
        System.out.println("******************");
        return "hello"; // o Spring vai renderizar o arquivo templates/hello.html
    }

    @GetMapping("/hello-model")
    public String hello(Model model) {
        model.addAttribute("nome", "Zezinho");
        System.out.println("******************");
        return "hello"; // o Spring vai renderizar o arquivo templates/hello.html
    }
}
