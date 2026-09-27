import java.util.Scanner;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        int opcion;

        do {
            System.out.println( "\n1. Registrar maquina"
                + "\n2. Ver maquinas"
                + "\n3. Confirmar alquiler"
                + "\n4. Devolver maquina"
                + "\n5. Consultar cantidades"
                + "\n6. Ver dinero acumulado"
                + "\n0. Salir"
            );

            opcion = leerEntero("Opcion: ");

            try {
                if (opcion == 1) {
                    registrar(inventario);

                } else if (opcion == 2) {
                    inventario.mostrarMaquinas();

                } else if (opcion == 3) {
                    int codigo = leerEntero("Codigo: ");
                    int dias = leerEntero("Dias: ");

                    Maquina maquina = inventario.buscarMaquina(codigo);

                    if (inventario.confirmarAlquiler(codigo, dias)) {
                        System.out.printf(
                            "Alquiler confirmado. Total: Q%.2f%n",
                            maquina.calcularTotal(dias)
                        );
                    } else {
                        System.out.println(
                            "No se pudo alquilar: codigo inexistente, "
                            + "maquina ocupada o dias invalidos."
                        );
                    }

                } else if (opcion == 4) {
                    int codigo = leerEntero("Codigo: ");

                    if (inventario.devolverMaquina(codigo)) {
                        System.out.println(
                            "Devolucion registrada. Maquina disponible."
                        );
                    } else {
                        System.out.println(
                            "El codigo no existe o la maquina no estaba alquilada."
                        );
                    }

                } else if (opcion == 5) {
                    System.out.println(
                        "Total registrado: " + inventario.contarRegistradas()
                    );

                    mostrarCantidades(
                        inventario, "Palomitas", Maqpalomitas.class
                    );
                    mostrarCantidades(
                        inventario, "Algodones", Maqalgodones.class
                    );
                    mostrarCantidades(
                        inventario, "Chocolate", Maqchocolate.class
                    );

                } else if (opcion == 6) {
                    System.out.printf(
                        "Dinero acumulado: Q%.2f%n",
                        inventario.getDineroacumulado()
                    );

                } else if (opcion == 0) {
                    System.out.println("Programa finalizado.");

                } else {
                    System.out.println("Opcion invalida.");
                }

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }

        } while (opcion != 0);

        teclado.close();
    }

    private static void registrar(Inventario inventario) {
        int tipo = leerEntero(
            "1. Palomitas  2. Algodones  3. Chocolate: "
        );

        if (tipo < 1 || tipo > 3) {
            System.out.println("Categoria invalida.");
            return;
        }

        int codigo = leerEntero("Codigo: ");

        if (inventario.buscarMaquina(codigo) != null) {
            System.out.println("Ese codigo ya esta registrado.");
            return;
        }

        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int precio = leerEntero(
            "Tarifa diaria base (quetzales enteros): "
        );

        Maquina maquina;

        if (tipo == 1) {
            int porcionesph = leerEntero("Porciones por hora: ");
            boolean carritodex = leerBooleano(
                "Tiene carrito de exhibicion? (s/n): "
            );

            maquina = new Maqpalomitas(
                codigo, precio, marca, modelo, porcionesph, carritodex
            );

        } else if (tipo == 2) {
            int vatios = leerEntero("Potencia en vatios: ");

            maquina = new Maqalgodones(
                codigo, precio, marca, modelo, vatios
            );

        } else {
            double capacidadmax = leerDecimal(
                "Capacidad maxima en kg: "
            );

            maquina = new Maqchocolate(
                codigo, precio, marca, modelo, capacidadmax
            );
        }

        if (inventario.registrarMaquina(maquina)) {
            System.out.println("Maquina registrada y disponible.");
        } else {
            System.out.println("Codigo duplicado.");
        }
    }

    private static void mostrarCantidades(
        Inventario inventario,
        String nombre,
        Class<?> categoria
    ) {
        System.out.printf(
            "%s: %d registradas, %d disponibles, %d alquiladas.%n",
            nombre,
            inventario.contarPorCategoria(categoria),
            inventario.contarDisponibles(categoria),
            inventario.contarAlquiladas(categoria)
        );
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("El dato no puede quedar vacio.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));

            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero entero valido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                double numero = Double.parseDouble(
                    leerTexto(mensaje).replace(',', '.')
                );

                if (Double.isFinite(numero)) {
                    return numero;
                }

            } catch (NumberFormatException e) {
                // El mensaje se muestra abajo.
            }

            System.out.println("Ingresa un numero decimal valido.");
        }
    }

    private static boolean leerBooleano(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje);

            if (respuesta.equalsIgnoreCase("s")) {
                return true;

            } else if (respuesta.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Responde s o n.");
        }
    }
}