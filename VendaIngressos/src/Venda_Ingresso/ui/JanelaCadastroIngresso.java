/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venda_Ingresso.ui;

import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

import Venda_Ingresso.entities.Ingresso;
import Venda_Ingresso.enums.SetorEnum;
import Venda_Ingresso.services.GerenciadorIngresso;

/**
 *
 * @author Junior
 */
public class JanelaCadastroIngresso extends JDialog {
    
    private DefaultTableModel modelo;
    private JPanel painelFundo;
    private JButton btnSalvar;
    private JButton btnVoltarTelaInicial;
    private JLabel lblCodigo;
    private JLabel lblNome;
    private JLabel lblValor;
    private JLabel lblQtde;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtSetor;
    private JTextField txtQtde;
    
    private String[] setores
            = {"Amarelo","Azul","Branco","Verde"};

    private JComboBox<String> cbxSetores;
    
    private String[] tiposTorcedor
            = {"Inteira", "Meia"};

    private JComboBox<String> cbxTipoTorcedor;
    
    private GerenciadorIngresso gerenciador;
    
    
    public JanelaCadastroIngresso(GerenciadorIngresso gerenciador) {
    	this.gerenciador = gerenciador;
        criarComponentesInsercao();        
    }

    private void limpar(){
        txtNome.setText("");
        txtQtde.setText("");        
    }

    private void criarComponentesInsercao() {
        
        
        btnSalvar = new JButton("Salvar");        
        btnVoltarTelaInicial  = new JButton("Voltar para Tela Inicial");
        
        btnSalvar.addActionListener((e) -> {
           comprarIngresso();
        });        
       
        btnVoltarTelaInicial.addActionListener((e) -> {
            setVisible(false);
            new TelaInicial();
        });
        
        lblNome = new JLabel("Nome:");       
        cbxTipoTorcedor = new JComboBox(tiposTorcedor);
        lblQtde = new JLabel("Quantidade:");
        txtNome = new JTextField(10);        
        txtQtde = new JTextField(5);
        cbxSetores = new JComboBox(setores);

        painelFundo = new JPanel();
        painelFundo.add(lblNome);
        painelFundo.add(txtNome);   
        painelFundo.add(cbxTipoTorcedor);
        painelFundo.add(lblQtde);
        painelFundo.add(txtQtde);
        painelFundo.add(cbxSetores);
        painelFundo.add(btnSalvar);
        painelFundo.add(btnVoltarTelaInicial);

        add(painelFundo);        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// Só fecha a janela(Esconde). Não fecha a aplicação(EXIT_ON_CLOSE)
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    
    private void comprarIngresso() {        
        Ingresso ingresso = new Ingresso();

        ingresso.setNome(txtNome.getText());          

        // pegar setor corretamente
        SetorEnum setorEnum = SetorEnum.valueOf(
            cbxSetores.getSelectedItem().toString().toUpperCase()
        );

        ingresso.setSetor(setorEnum.name());

        // tratar erro de número
        int quantidade;
        try {
            quantidade = Integer.parseInt(txtQtde.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número válido!");
            return;
        }

        ingresso.setQuantidade(quantidade);

        double valorIngresso = setorEnum.getPreco();

        String tipoTorcedor = cbxTipoTorcedor.getSelectedItem().toString();

        if (tipoTorcedor.equalsIgnoreCase("Meia")) {
            valorIngresso = valorIngresso / 2;
        }

        ingresso.setValor(valorIngresso);

        double valorTotal = valorIngresso * quantidade;
        ingresso.setValorTotal(valorTotal);

        ingresso.setDataHora(LocalDateTime.now());
        
        try {
            if (gerenciador.comprarIngresso(ingresso)) {
                gerenciador.salvarDados();
                limpar();
                JOptionPane.showMessageDialog(null, "Ingresso comprado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
}