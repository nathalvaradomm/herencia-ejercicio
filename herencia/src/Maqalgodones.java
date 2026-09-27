class Maqalgodones extends Maquina {
    private int vatios;

    public Maqalgodones(int codigo, int precio, String marca, String modelo, int vatios) {
        super(codigo, precio, marca, modelo);
        setVatios(vatios);
    }
    public int getVatios() { return vatios; }
    public void setVatios(int vatios) {
        if (vatios <= 0) throw new IllegalArgumentException("Los vatios deben ser positivos.");
        this.vatios = vatios;
    }
    @Override
    public double calcularRecargo() {
        if (vatios > 1000) {
            return 60;
        } else {
            return 0;
        }
    }
    @Override
    public String mostrarCapacidad() { return vatios + " vatios"; }
}

