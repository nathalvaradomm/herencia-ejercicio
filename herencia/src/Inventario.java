import java.util.ArrayList;
   
class Inventario {
    private final ArrayList<Maquina> maquinas;
    private double dineroacumulado;

    public Inventario() {
        maquinas = new ArrayList<>();
        dineroacumulado = 0;
    }
    public boolean registrarMaquina(Maquina maquina) {
        if (maquina == null || buscarMaquina(maquina.getCodigo()) != null) return false;
        maquina.marcarRegistrada();
        maquinas.add(maquina);
        return true;
    }
    public Maquina buscarMaquina(int codigo) {
        for (Maquina maquina : maquinas) {
            if (maquina.getCodigo() == codigo) return maquina;
        }
        return null;
    }
    public boolean confirmarAlquiler(int codigo, int dias) {
        Maquina maquina = buscarMaquina(codigo);
        if (maquina == null || maquina.getAlquilado() || dias <= 0) return false;
        double total = maquina.calcularTotal(dias);
        if (!Double.isFinite(dineroacumulado + total)) {
            throw new IllegalArgumentException("El dinero acumulado excederia el limite numerico.");
        }
        maquina.setAlquilado(true);
        dineroacumulado += total;
        return true;
    }
    public boolean devolverMaquina(int codigo) {
        Maquina maquina = buscarMaquina(codigo);
        if (maquina == null || !maquina.getAlquilado()) return false;
        maquina.setAlquilado(false);
        return true;
    }
    public int contarRegistradas() { return maquinas.size(); }
    public int contarPorCategoria(Class<?> categoria) {
        int cantidad = 0;
        for (Maquina maquina : maquinas) {
            if (categoria.isInstance(maquina)) cantidad++;
        }
        return cantidad;
    }
    public int contarDisponibles(Class<?> categoria) {
        int cantidad = 0;
        for (Maquina maquina : maquinas) {
            if (categoria.isInstance(maquina) && !maquina.getAlquilado()) cantidad++;
        }
        return cantidad;
    }
    public int contarAlquiladas(Class<?> categoria) {
        return contarPorCategoria(categoria) - contarDisponibles(categoria);
    }
    public double getDineroacumulado() { return dineroacumulado; }
    public void mostrarMaquinas() {
        if (maquinas.isEmpty()) System.out.println("No hay maquinas registradas.");
        for (Maquina maquina : maquinas) {
            System.out.printf("%n%s | Codigo: %d | Marca: %s | Modelo: %s%n", maquina.getClass().getSimpleName(),
                    maquina.getCodigo(), maquina.getMarca(), maquina.getModelo());
            System.out.println(maquina.mostrarCapacidad());
            System.out.printf("Base diaria: Q%d | Recargo diario: Q%.2f | Total diario: Q%.2f%n",
                    maquina.getPrecio(), maquina.calcularRecargo(), maquina.calcularTarifaDiaria());
              if (maquina.getAlquilado()) {
                System.out.println("Estado: Alquilada");
            } else {
                System.out.println("Estado: Disponible");
            }
        }
    }
}
