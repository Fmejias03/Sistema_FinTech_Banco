class HiloCliente implements Runnable {
    public enum TipoTransaccion { DEPOSITO, RETIRO }

    private Cuenta cuenta;
    private TipoTransaccion tipoTransaccion;
    private int cantidadHilo;

    public HiloCliente(Cuenta cuenta, TipoTransaccion tipoTransaccion, int cantidadHilo) {
        this.cuenta = cuenta;
        this.tipoTransaccion = tipoTransaccion;
        this.cantidadHilo = cantidadHilo;
    }

    @Override
    public void run() {
        switch (tipoTransaccion) {
            case DEPOSITO:
                cuenta.depositar(cantidadHilo);
                break;
            case RETIRO:
                cuenta.retirar(cantidadHilo);
                break;
        }
    }
}