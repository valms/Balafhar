package main.java.br.com.valmarjunior.model;

public enum Status {
	
	MATRICULADO( 1, "matriculado" ), TRANCADO( 2, "trancado" ), JUBILADO( 3, "jubilado" );
	
	private int code;
	private String status;
	
	/**
	 * @see <a href=http://www.codejava.net/frameworks/hibernate/hibernate-enum-type-mapping-example>Exemplo Enum</a>
	 */
	Status(int code, String status) {
		this.code = code;
		this.status = status;
	}
	
	public int getCode() {
		return code;
	}
	
	
	public String getStatus() {
		return status;
	}
	
}
