package Interface;

import java.io.File;
import java.util.Scanner;

import Business.Pedido;
import Business.Simulacao;

public class App {
    public static void main(String[] args) throws Exception {
        Simulacao simulacao = new Simulacao();
        File file = new File("dados_tp01.txt");
        Scanner scanner = new Scanner(file);
        int qtdPedidos;
        String pedidos;
        qtdPedidos = Integer.parseInt(scanner.nextLine());
        for(int i = 1; i <= qtdPedidos; i++) {
            pedidos = scanner.nextLine();
            String nome = next(pedidos);
            pedidos = remove(pedidos);
            int qtd = Integer.parseInt(next(pedidos));
            pedidos = remove(pedidos);
            int prazo = Integer.parseInt(next(pedidos));
            simulacao.addPedido(new Pedido(nome, qtd, prazo));
        }
        System.out.println("Tempo de término: " + simulacao.executar());
        for(int i = 0; i < simulacao.sizePedidos(); i++) {
            System.out.println(simulacao.getPedido(i));
        }
        System.out.println("Quantidade de pedidos entregues após o prazo: " + simulacao.getQtdPedidosPerdidos());
        scanner.close();
    }

    public static String next(String string) {
        int index = string.indexOf(";");
        String item = "";
        if(index != -1) {
            item = string.substring(0, index);
        } else {
            item = string;
        }
        return item;
    }

    public static String remove(String string) {
        int index = string.indexOf(";");
        if(index != -1) {
            string = string.substring(index + 1, string.length());
        }
        return string;
    }
}
