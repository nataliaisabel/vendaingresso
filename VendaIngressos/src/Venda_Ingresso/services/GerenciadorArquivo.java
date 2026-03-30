package Venda_Ingresso.services;

import Venda_Ingresso.entities.Ingresso;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivo {

    public void serializar(List<Ingresso> ingressos, String path) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

            oos.writeObject(ingressos);
            oos.close();

        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao salvar: " + e.getMessage());

        } finally {
            System.out.println("Serialização concluída.");
        }
    }

    public List<Ingresso> desserializar(String path) {

        List<Ingresso> ingressos = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            ingressos = (List<Ingresso>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Arquivo não encontrado ou vazio.");

        } finally {
            System.out.println("Desserialização finalizada.");
        }

        return ingressos;
    }
    
    public void exportarTxt(List<Ingresso> ingressos, String path) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            for (Ingresso i : ingressos) {
                writer.write("Código: " + i.getCodigo());
                writer.newLine();

                writer.write("Nome: " + i.getNome());
                writer.newLine();

                writer.write("Setor: " + i.getSetor());
                writer.newLine();

                writer.write("Quantidade: " + i.getQuantidade());
                writer.newLine();

                writer.write("Valor Unitário: " + i.getValor());
                writer.newLine();

                writer.write("Valor Total: " + i.getValorTotal());
                writer.newLine();

                writer.write("Data: " + i.getDataHora());
                writer.newLine();

                writer.write("-------------------------");
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao gerar relatório: " + e.getMessage());
        }
    }
    
}
