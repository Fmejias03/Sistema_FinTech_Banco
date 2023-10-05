class Cuenta {
    private int saldo;

    public Cuenta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void depositar(int cantidadTransac) {
        saldo += cantidadTransac;
    }

    public synchronized void retirar(int cantidadTransac) {
        saldo -= cantidadTransac;
    }

    public int getSaldo() {
        return saldo;
    }
}