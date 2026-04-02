/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venda_Ingresso.main;

import Venda_Ingresso.enums.SetorEnum;
import Venda_Ingresso.services.CompradorRunnable;
import Venda_Ingresso.services.GerenciadorIngresso;
import Venda_Ingresso.ui.TelaInicial;

/**
 *
 * @author Junior
 */
public class Principal {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        
        GerenciadorIngresso gerenciador = new GerenciadorIngresso();
        
        TelaInicial telaInicial = new TelaInicial(gerenciador);
        
        // vai realizar a compra em "paralelo" a execução
        Thread t1 = new Thread( new CompradorRunnable("Ana", SetorEnum.AMARELO, 6, gerenciador));
        // não conseguiram comprar devido a quantidade estabelecida anteriormente (10 ingressos por setor)
        Thread t2 = new Thread( new CompradorRunnable("Carlos", SetorEnum.AMARELO, 6, gerenciador));
        Thread t3 = new Thread( new CompradorRunnable("Isabel", SetorEnum.AMARELO, 6, gerenciador));
        Thread t4 = new Thread( new CompradorRunnable("Bianca", SetorEnum.AMARELO, 6, gerenciador));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    
}
