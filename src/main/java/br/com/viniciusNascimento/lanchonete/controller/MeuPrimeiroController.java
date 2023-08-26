package br.com.viniciusNascimento.lanchonete.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    @GetMapping("/Oi")
    @ResponseBody
    public String hell(){
        return "Oi, Tudo Bem?";
    }

}
