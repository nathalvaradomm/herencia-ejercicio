class Maqpalomitas extends Maquina {
    private int porcionesph;
    private boolean carritodex;

    public Maqpalomitas(int codigo, int precio, String marca, String modelo, int porcionesph, boolean carritodex) {
        super(codigo, precio, marca, modelo);
        setPorcionesph(porcionesph);
        setCarritodex(carritodex);
    }
    public int getPorcionesph() { return porcionesph; }
    public void setPorcionesph(int porcionesph) {
        if (porcionesph <= 0) throw new IllegalArgumentException("Las porciones por hora deben ser positivas.");
        this.porcionesph = porcionesph;
    }
    public boolean getCarritodex() { return carritodex; }
    public void setCarritodex(boolean carritodex) { this.carritodex = carritodex; }
    @Override
     public double calcularRecargo() {
        if (carritodex) {
            return 40;
        } else {
            return 0;
        }
    }
    @Override
    public String mostrarCapacidad() {
        if (carritodex) {
            return porcionesph + " porciones/hora; carrito: si";
        } else {
            return porcionesph + " porciones/hora; carrito: no";
        }
    }
}
