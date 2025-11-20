package com.mycompany.laboratorio.calidad.codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de registro de usuarios con validaciones básicas.
 */
public class UserRegistrationService {

    /**
     * Último mensaje de error generado por el servicio.
     */
    private String lastErrorMessage = "";

    /**
     * Lista de nombres de usuario registrados.
     */
    private final List<String> users = new ArrayList<>();

    /**
     * Longitud mínima permitida para la contraseña.
     */
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Crea una nueva instancia del servicio de registro.
     */
    public UserRegistrationService() {
        System.out.println("Servicio de registro iniciado correctamente.");
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param username nombre de usuario
     * @param password contraseña del usuario
     * @param email    correo electrónico del usuario
     * @return {@code true} si el usuario se registra correctamente,
     *         {@code false} en caso contrario
     */
    public boolean registerUser(final String username,
                                final String password,
                                final String email) {

        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage =
                    "El nombre de usuario no puede estar vacío o ser nulo.";
            return false;
        }

        if (password == null) {
            lastErrorMessage = "La contraseña no puede ser nula.";
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage =
                    "La contraseña es muy corta. Debe tener al menos "
                    + MIN_PASSWORD_LENGTH + " caracteres.";
            return false;
        }

        if (email == null || !isValidEmail(email)) {
            lastErrorMessage = "El correo electrónico no es válido.";
            return false;
        }

        if (users.contains(username)) {
            lastErrorMessage = "El nombre de usuario ya está registrado.";
            return false;
        }

        try {
            saveUser(username, password, email);
        } catch (IllegalArgumentException ex) {
            lastErrorMessage = ex.getMessage();
            return false;
        } catch (Exception ex) {
            lastErrorMessage =
                    "Error al guardar el usuario: " + ex.getMessage();
            return false;
        }

        System.out.println("Usuario registrado correctamente: " + username);
        return true;
    }

    /**
     * Guarda un nuevo usuario en la lista interna.
     *
     * @param username nombre de usuario
     * @param password contraseña del usuario
     * @param email    correo electrónico del usuario
     * @throws Exception si ocurre un problema al guardar el usuario
     */
    private void saveUser(final String username,
                          final String password,
                          final String email) throws Exception {

        if ("error".equals(username)) {
            throw new IllegalArgumentException(
                    "Nombre de usuario no permitido.");
        }
        users.add(username);
    }

    /**
     * Verifica si el correo cumple con un formato básico válido.
     *
     * @param email correo electrónico a validar
     * @return {@code true} si el correo tiene un formato válido,
     *         {@code false} en caso contrario
     */
    private boolean isValidEmail(final String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern
                .compile(emailRegex)
                .matcher(email)
                .matches();
    }

    /**
     * Devuelve el último mensaje de error registrado.
     *
     * @return último mensaje de error
     */
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Devuelve la longitud de una cadena.
     *
     * @param input cadena de texto, puede ser {@code null}
     * @return longitud de la cadena o {@code -1} si es {@code null}
     */
    public int getStringLength(final String input) {
        if (input == null) {
            return -1;
        }
        return input.length();
    }
}
