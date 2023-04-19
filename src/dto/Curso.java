package dto;

public class Curso {
	
	private int id;
	private String nomeCurso;
	private int cargaHoraria;
	private int areaConcentracao;
	
	public Curso () {
		
	}
	
	public Curso(String nomeCurso, int cargaHoraria, int areaConcentracao) {
		this.nomeCurso = nomeCurso;
		this.cargaHoraria = cargaHoraria;
		this.areaConcentracao = areaConcentracao;
	}
	
	public Curso(int id, String nomeCurso, int cargaHoraria, int areaConcentracao) {
		super();
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.cargaHoraria = cargaHoraria;
		this.areaConcentracao = areaConcentracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getAreaConcentracao() {
		return areaConcentracao;
	}

	public void setAreaConcentracao(int areaConcentracao) {
		this.areaConcentracao = areaConcentracao;
	}
	
    @Override
    public String toString() {
        return id + " - " + nomeCurso;
    }

}
