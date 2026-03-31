package Venda_Ingresso.enums;


public enum SetorEnum {

	// constantes referentes aos ingressos com os atributos:
	// nome, valor, quantidade limite de ingressos
    AMARELO("Amarelo", 50, 10),
    AZUL("Azul", 40, 10),
    BRANCO("Branco", 30, 10),
    VERDE("Verde", 20, 10);

    private String nome;
    private double preco;
    private int qtdLimite;

    SetorEnum(String nome, double preco, int qtdLimite){
        this.nome = nome;
        this.preco = preco;
        this.qtdLimite = qtdLimite;
    }

    public double getPreco(){
        return preco;
    }

    public String getNome(){
        return nome;
    }
    
    public int getLimiteIngresso() {
    	return qtdLimite;
    }
    
}
