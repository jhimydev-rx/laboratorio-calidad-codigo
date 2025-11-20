/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio.calidad.codigo;

/**
 * Clase principal que ejecuta casos de prueba para el registro de usuarios.
 */
public class Main {

    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        // Casos de prueba simples
        service.registerUser("juan", "123", "juan@correo"); // Contraseña muy corta
        System.out.println(service.getLastErrorMessage());

        service.registerUser(null, "12345678", "correo-sin-arroba"); // username null
        System.out.println(service.getLastErrorMessage());

        service.registerUser("error", "12345678", "error@correo.com"); // fuerza excepción
        System.out.println(service.getLastErrorMessage());
    }
}
