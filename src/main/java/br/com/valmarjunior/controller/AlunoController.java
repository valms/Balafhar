package main.java.br.com.valmarjunior.controller;

import main.java.br.com.valmarjunior.dao.AlunoDAO;
import main.java.br.com.valmarjunior.model.Aluno;
import main.java.br.com.valmarjunior.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class AlunoController {
	
	private final AlunoDAO alunoDAO;
	
	@Autowired
	public AlunoController(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

//	@ModelAttribute("alunos")
//	public List<Aluno> alunos() {
//		return alunoDAO.getAll( Aluno.class );
//	}
	
	@GetMapping("/")
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView( "aluno" );
		modelAndView.addObject( "alunos", alunoDAO.getAll( Aluno.class ) );
		return modelAndView;
		
	}
	
	@GetMapping("/adiciona")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView( "alunoAdd" );
		modelAndView.addObject( "aluno", new Aluno() );
		modelAndView.addObject( "statusList", Status.values() );
		return modelAndView;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView( "alunoAdd" );
		modelAndView.addObject( "aluno", alunoDAO.getById( Aluno.class, id ) );
		modelAndView.addObject( "statusList", Status.values() );
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView remove(@PathVariable("id") int id) {
		alunoDAO.remove( alunoDAO.getById( Aluno.class, id ) );
		return new RedirectView( "aluno", true );
	}
	
	@GetMapping("/salvar")
	public ModelAndView save(@Valid Aluno aluno, BindingResult bindingResult) {
		alunoDAO.save( aluno );
		return findAll();
	}
}
