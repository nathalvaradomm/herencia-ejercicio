class Maqchocolate extends Maquina {
    private double capacidadmax;

    public Maqchocolate(int codigo, int precio, String marca, String modelo, double capacidadmax) {
        super(codigo, precio, marca, modelo);
        setCapacidadmax(capacidadmax);
    }
    public double getCapacidadmax() { return capacidadmax; }
    public void setCapacidadmax(double capacidadmax) {
        if (!Double.isFinite(capacidadmax) || capacidadmax <= 0 || !Double.isFinite(20 * capacidadmax)) {
            throw new IllegalArgumentException("La capacidad debe ser un numero positivo valido.");
        }
        this.capacidadmax = capacidadmax;
    }
    @Override
    public double calcularRecargo() { return 20 * capacidadmax; }
    @Override
    public String mostrarCapacidad() { return capacidadmax + " kg de capacidad maxima"; }
}