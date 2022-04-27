package Business;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

public class Simulacao {
    private LocalTime horario;
    private int qtdPedidosPerdidos;
    private BracoMecanico braco;
    private Esteira esteira;
    private List<Pedido> pedidos;

    public Simulacao() {
        horario = LocalTime.of(8, 0);
        pedidos = new LinkedList<>();
        braco = new BracoMecanico();
        esteira = new Esteira(braco);
        this.qtdPedidosPerdidos = 0;
    }

    public int getQtdPedidosPerdidos() {
        return qtdPedidosPerdidos;
    }

    public int sizePedidos() {
        return pedidos.size();
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido getPedido(int index) {
        return pedidos.get(index);
    }

    public LocalTime executar() {
        for (Pedido pedido : pedidos) {
            esteira.addPedido(pedido);
        }
        while(esteira.hasPedido()) {
            moverEsteira();
            Pedido pedido = braco.entregar().getPedido();
            if(pedido.getQtdProdutos() <= 0) {
                pedido.finalizarPedido(horario);
                if(LocalTime.of(8, 0).plusMinutes(pedido.getPrazo()).isBefore(horario) && pedido.getPrazo() != 0) {
                    this.qtdPedidosPerdidos ++;
                }
            }
        }
        
        return horario;
    }

    private void moverEsteira() {
        horario = horario.plus((int) (1000 * Esteira.TEMPO_TRANSICAO), ChronoUnit.MILLIS);
        esteira.produzirPacote();
        horario = horario.plusSeconds(5);
    }
}
