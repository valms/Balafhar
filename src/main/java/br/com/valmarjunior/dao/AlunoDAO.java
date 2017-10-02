package main.java.br.com.valmarjunior.dao;

import main.java.br.com.valmarjunior.model.Aluno;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoDAO extends GenericDAO<Aluno, Integer> {
}
