package Venda_Ingresso.services;

import Venda_Ingresso.entities.Ingresso;
import Venda_Ingresso.enums.SetorEnum;

public class CompradorRunnable implements Runnable {
	private String nomeComprador;
    private SetorEnum setor;
    private int quantidade;
    private GerenciadorIngresso gerenciador;

    public CompradorRunnable(String nomeComprador, SetorEnum setor, int quantidade, GerenciadorIngresso gerenciador) 
    {
        this.nomeComprador = nomeComprador;
        this.setor = setor;
        this.quantidade = quantidade;
        this.gerenciador = gerenciador;
    }

    @Override
    public void run() {

    	try {
            Thread.sleep(50);

            Ingresso ingresso = new Ingresso();
            ingresso.setNome(nomeComprador);
            ingresso.setSetor(setor);
            ingresso.setQuantidade(quantidade);

            gerenciador.comprarIngresso(ingresso);

            System.out.println(nomeComprador + " comprou ingresso!");

        } catch (Exception e) {
            System.out.println(nomeComprador + " NÃO conseguiu: " + e.getMessage());
        }
}
}
