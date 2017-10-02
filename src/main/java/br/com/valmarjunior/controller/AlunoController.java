package main.java.br.com.valmarjunior.controller;

import main.java.br.com.valmarjunior.dao.AlunoDAO;
import main.java.br.com.valmarjunior.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public class AlunoController {
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	@GetMapping("/")
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView( "/aluno" );
		modelAndView.addObject( "alunos", alunoDAO.getAll( Aluno.class ) );
		return modelAndView;
	}
	
	@GetMapping("/adiciona")
	public ModelAndView add(Aluno aluno) {
		ModelAndView modelAndView = new ModelAndView( "/adicionarAluno" );
		modelAndView.addObject( "aluno", aluno );
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		return add( alunoDAO.getById( Aluno.class, id ) );
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView remove(@PathVariable("id") int id) {
		alunoDAO.remove( alunoDAO.getById( Aluno.class, id ) );
		return findAll();
	}
	
	@GetMapping("/save")
	public ModelAndView save(@Valid Aluno aluno, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return add( aluno );
		}
		alunoDAO.save( aluno );
		return findAll();
	}
}
