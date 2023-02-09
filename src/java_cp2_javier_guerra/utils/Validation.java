package java_cp2_javier_guerra.utils;

public abstract class Validation {

    /**
     * Comprueba si dniNie tiene la forma de un DNI o NIE
     * No comprueba si es un DNI o NIE válido
     * @param dniNie DNI o NIE
     * @return true / false
     */
    public static boolean isDniNieFormat(String dniNie) {
        return dniNie != null && !dniNie.equals("") &&
                dniNie.matches("([XYxy])?(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
    }

    /**
     * Comprueba si email tiene la forma de un correo electrónico
     * No comprueba si es un e-mail válido
     * @param email Correo electrónico
     * @return true / false
     */
    public static boolean isEmailFormat(String email) {
        return email != null && !email.equals("") &&
                email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
}
