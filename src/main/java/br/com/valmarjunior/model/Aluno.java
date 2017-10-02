package main.java.br.com.valmarjunior.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "aluno")
@SequenceGenerator(name = Aluno.SEQUENCE_NAME, sequenceName = Aluno.SEQUENCE_NAME, allocationSize = 50)
@NamedQuery(name = "Aluno.findAll", query = "SELECT e FROM Aluno e")
public class Aluno implements Serializable {
	
	static final String SEQUENCE_NAME = "aluno_id_seq";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private int id;
	
	private String nome;
	
	private String curso;
	
	private int matricula;
	
	private int semestre;
	
	private Status status;
	
	public Aluno(String nome, String curso, int matricula, int semestre, Status status) {
		this.nome = nome;
		this.curso = curso;
		this.matricula = matricula;
		this.semestre = semestre;
		this.status = status;
	}
	
	public Aluno() {
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCurso() {
		return this.curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public int getMatricula() {
		return this.matricula;
	}
	
	public void setMatricula(int numero) {
		this.matricula = numero;
	}
	
	public int getSemestre() {
		return this.semestre;
	}
	
	public void setSemestre(int integer) {
		this.semestre = integer;
	}
	
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return this.status;
	}
	
	
	
	
}
