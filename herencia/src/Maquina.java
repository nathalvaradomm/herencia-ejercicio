class Maquina {
    protected int codigo;
    protected int precio;
    protected boolean alquilado;
    protected String marca;
    protected String modelo;
    private boolean registrada;

    public Maquina(int codigo, int precio, String marca, String modelo) {
        setCodigo(codigo);
        setPrecio(precio);
        setMarca(marca);
        setModelo(modelo);
        alquilado = false;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) {
        if (registrada) throw new IllegalStateException("No se puede cambiar un codigo registrado.");
        if (codigo <= 0) throw new IllegalArgumentException("El codigo debe ser positivo.");
        this.codigo = codigo;
    }
    void marcarRegistrada() { registrada = true; }
    public int getPrecio() { return precio; }
    public void setPrecio(int precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }
    public boolean getAlquilado() { return alquilado; }
    public void setAlquilado(boolean alquilado) { this.alquilado = alquilado; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) throw new IllegalArgumentException("La marca es obligatoria.");
        this.marca = marca.trim();
    }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) throw new IllegalArgumentException("El modelo es obligatorio.");
        this.modelo = modelo.trim();
    }
    public double calcularRecargo() { return 0; }
    public double calcularTarifaDiaria() { return precio + calcularRecargo(); }
    public double calcularTotal(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los dias deben ser mayores que cero.");
        double total = calcularTarifaDiaria() * dias;
        if (!Double.isFinite(total)) throw new IllegalArgumentException("El importe es demasiado grande.");
        return total;
    }
    public String mostrarCapacidad() { return "Sin capacidad especificada"; }
}
