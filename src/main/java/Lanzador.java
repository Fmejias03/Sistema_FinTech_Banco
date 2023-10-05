import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lanzador {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(10000);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 400; i++) {
            executor.execute(new HiloCliente(cuenta, HiloCliente.TipoTransaccion.DEPOSITO, 100));
            executor.execute(new HiloCliente(cuenta, HiloCliente.TipoTransaccion.RETIRO, 100));
        }
        for (int i = 0; i < 200; i++) {
            executor.execute(new HiloCliente(cuenta, HiloCliente.TipoTransaccion.DEPOSITO, 50));
            executor.execute(new HiloCliente(cuenta, HiloCliente.TipoTransaccion.RETIRO, 50));
        }
        for (int i = 0; i < 600; i++) {
            executor.execute(new HiloCliente(cuenta, HiloCliente.TipoTransaccion.DEPOSITO, 20));
            executor.execute(new HiloCliente(cuenta, HiloCliente.TipoTransaccion.RETIRO, 20));
        }


        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Saldo final: " + cuenta.getSaldo());
    }

}