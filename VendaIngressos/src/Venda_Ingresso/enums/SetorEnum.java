package Venda_Ingresso.enums;


public enum SetorEnum {

    AMARELO("Amarelo", 50),
    AZUL("Azul", 40),
    BRANCO("Branco", 30),
    VERDE("Verde", 20);

    private String nome;
    private double preco;

    SetorEnum(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco(){
        return preco;
    }

    public String getNome(){
        return nome;
    }
}
