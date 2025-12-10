package co.edu.unicauca.util;

public class validador {
    public static String validarContrasena(String pwd) {
        StringBuilder errores = new StringBuilder();

        if (pwd == null || pwd.length() < 7) {
            errores.append("Debe tener al menos 7 caracteres. ");
        }

        boolean tieneMayuscula = false;
        boolean tieneEspecial = false;

        if (pwd != null) {
            for (char c : pwd.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    tieneMayuscula = true;
                } else if (!Character.isLetterOrDigit(c)) {
                    tieneEspecial = true;
                }
            }
        }

        if (!tieneMayuscula) {
            errores.append("Debe tener al menos una mayúscula. ");
        }

        if (!tieneEspecial) {
            errores.append("Debe tener al menos un carácter especial. ");
        }

        // Si no hay errores, retornar cadena vacía
        return errores.toString().trim();
    }

}
