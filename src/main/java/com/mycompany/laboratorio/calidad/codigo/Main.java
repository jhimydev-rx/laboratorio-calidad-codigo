package com.mycompany.laboratorio.calidad.codigo;

/**
 * Punto de entrada de la aplicación.
 */
public final class Main {

    /**
     * Constructor privado para evitar la instanciación.
     */
    private Main() {
        // Intencionadamente vacío.
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(final String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        // Casos de prueba simples.
        service.registerUser("juan", "123", "juan@correo");
        System.out.println(service.getLastErrorMessage());

        service.registerUser(null, "12345678", "correo-sin-arroba");
        System.out.println(service.getLastErrorMessage());

        service.registerUser("error", "12345678",
                "error@correo.com");
        System.out.println(service.getLastErrorMessage());
    }
}
