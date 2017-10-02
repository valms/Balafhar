package main.java.br.com.valmarjunior;

import main.java.br.com.valmarjunior.dao.AlunoDAO;
import main.java.br.com.valmarjunior.dao.impl.AlunoDAOmpl;
import main.java.br.com.valmarjunior.model.Aluno;
import main.java.br.com.valmarjunior.utils.JpaUtils;

import java.util.List;

public class main {
	
	public static void main(String[] args) {
		JpaUtils.openEntityManagerFactory();
		AlunoDAO alunoDAO = new AlunoDAOmpl();
		List<Aluno> alunos = alunoDAO.getAll( Aluno.class );
		
		
	}
}
