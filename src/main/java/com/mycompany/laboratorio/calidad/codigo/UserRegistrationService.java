/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laboratorio.calidad.codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de registro de usuarios con validaciones básicas.
 */
public class UserRegistrationService {

    private String lastErrorMessage = "";
    private final List<String> users = new ArrayList<>();
    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserRegistrationService() {
        System.out.println("Servicio de registro iniciado correctamente.");
    }

    /**
     * Registra un nuevo usuario.
     * 
     * @param username nombre de usuario
     * @param password contraseña del usuario
     * @param email    correo electrónico
     * @return true si el usuario se registra correctamente, false en caso contrario
     */
    public boolean registerUser(String username, String password, String email) {

        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario no puede estar vacío o ser nulo.";
            return false;
        }

        if (password == null) {
            lastErrorMessage = "La contraseña no puede ser nula.";
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta. Debe tener al menos " + MIN_PASSWORD_LENGTH + " caracteres.";
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
        } catch (IllegalArgumentException e) {
            lastErrorMessage = e.getMessage();
            return false;
        } catch (Exception e) {
            lastErrorMessage = "Error al guardar el usuario: " + e.getMessage();
            return false;
        }

        System.out.println("Usuario registrado correctamente: " + username);
        return true;
    }

    private void saveUser(String username, String password, String email) throws Exception {
        if ("error".equals(username)) {
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }
        users.add(username);
    }

    /**
     * Verifica si el correo cumple con un formato básico válido.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * Devuelve el último mensaje de error registrado.
     */
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Devuelve la longitud de una cadena, o -1 si es nula.
     */
    public int getStringLength(String input) {
        if (input == null) {
            return -1;
        }
        return input.length();
    }
}
