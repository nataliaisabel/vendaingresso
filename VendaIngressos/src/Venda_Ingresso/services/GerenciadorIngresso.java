/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venda_Ingresso.services;

import java.util.ArrayList;

import Venda_Ingresso.entities.Ingresso;
import Venda_Ingresso.enums.SetorEnum;
import Venda_Ingresso.exceptions.*;

/**
 *
 * @author Junior
 */
public class GerenciadorIngresso {
    
    private ArrayList<Ingresso> ingressos;
    private int prox = 0;
    private GerenciadorArquivo gerenciadorArquivo = new GerenciadorArquivo();
    private final String CAMINHO = "ingressos.ser";

    public GerenciadorIngresso() {
    	ingressos = new ArrayList<>();
        carregarDados();
    }
    
    public synchronized boolean comprarIngresso(Ingresso ingresso) throws IngressoInvalidoException, NomeInvalidoException, QtdInvalidaException, SetorEsgotadoException {
        
    	// tratativas com exceptions
    	if (ingresso == null) {
    	    throw new IngressoInvalidoException("Ingresso inválido!");
    	}

    	if (ingresso.getNome() == null || ingresso.getNome().trim().isEmpty()) {
    	    throw new NomeInvalidoException("Nome não pode ser vazio!");
    	}

    	if (ingresso.getQuantidade() <= 0) {
    	    throw new QtdInvalidaException("Quantidade deve ser maior que 0!");
    	}
    	
    	int vendidoNoSetor = contarIngressosPorSetor(ingresso.getSetor());
    	
    	if (vendidoNoSetor + ingresso.getQuantidade() > ingresso.getSetor().getLimiteIngresso()) {
    		throw new SetorEsgotadoException("Setor esgotado!"); 
    	}
    	
    	
	   ingresso.setCodigo(++prox);
	   
	   ingresso.setThreadOrigem(Thread.currentThread().getName());
	   
	   ingressos.add(ingresso);
	
	    return true;
    }
    
    //Retorna os ingressos adquiridos
    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
    
    public void salvarDados() {
        gerenciadorArquivo.serializar(ingressos, CAMINHO);
    }
    
    public void carregarDados() {
        ingressos = new ArrayList<>(gerenciadorArquivo.desserializar(CAMINHO));

        if (!ingressos.isEmpty()) {
            prox = ingressos.get(ingressos.size() - 1).getCodigo();
        }
    }
    
    public void gerarRelatorioTxt() {
        gerenciadorArquivo.exportarTxt(ingressos, "relatorio.txt");
    }
    
    
    // método auxiliar para contagem dos ingressos por setor
    private int contarIngressosPorSetor(SetorEnum setor) {
        int total = 0;

        for (Ingresso i : ingressos) {
            if (i.getSetor() == setor) {
                total += i.getQuantidade();
            }
        }

        return total;
    }
}

    
    
    
    

