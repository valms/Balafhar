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
	
	private int numero;
	
	private int integer;
	
	private Status status;
	
	public Aluno(String nome, String curso, int numero, int integer, Status status) {
		this.nome = nome;
		this.curso = curso;
		this.numero = numero;
		this.integer = integer;
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
	
	public int getNumero() {
		return this.numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getInteger() {
		return this.integer;
	}
	
	public void setInteger(int integer) {
		this.integer = integer;
	}
	
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return this.status;
	}
	
}
