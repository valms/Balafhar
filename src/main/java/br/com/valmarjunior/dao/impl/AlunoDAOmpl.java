package main.java.br.com.valmarjunior.dao.impl;

import main.java.br.com.valmarjunior.dao.AlunoDAO;
import main.java.br.com.valmarjunior.dao.hibernate.HibernateGenericDAO;
import main.java.br.com.valmarjunior.model.Aluno;

public class AlunoDAOmpl extends HibernateGenericDAO<Aluno, Integer> implements AlunoDAO {
}
