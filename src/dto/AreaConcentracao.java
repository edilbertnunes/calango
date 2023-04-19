package dto;

public class AreaConcentracao {
    private int id;
    private String nome;

    public AreaConcentracao(String nome) {
        this.nome = nome;
    }
    
    public AreaConcentracao(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return id + " - " + nome;
    }
}

// dto - so get,  setter e construtor- sem banco
// criar classe menu e

//jMenu JmenuItem JmenuBar -recebe jMenu recebe JmenuItem
