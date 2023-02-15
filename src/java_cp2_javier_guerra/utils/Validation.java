package java_cp2_javier_guerra.utils;

public abstract class Validation {

    /**
     * Comprueba si tiene la forma de un NIF.
     * No comprueba si es un NIF válido, sólo su formato.
     * @param nif NIF.
     * @return true / false
     */
    public static boolean isNifFormat(String nif) {
        return nif != null && !nif.equals("") &&
                (nif.matches("(\\d{1,8})" + "([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])"));
    }
    
    /**
     * Comprueba si tiene la forma de un número de teléfono.
     * No comprueba si es un número de telefono válido, sólo su formato.
     * @param phone número de teléfono.
     * @return true / false
     */
    public static boolean isPhoneFormat(String phone) {
        return phone != null && !phone.equals("") &&
                (phone.matches("(\\+)?[(\\([0-9]{2,3}\\))|([0-9]{2,3})]+([0-9]{6,9})"));
    }

    /**
     * Comprueba si email tiene la forma de un correo electrónico.
     * No comprueba si es un e-mail válido, sólo su formato.
     * @param email Correo electrónico
     * @return true / false
     */
    public static boolean isEmailFormat(String email) {
        return email != null && !email.equals("") &&
                email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
}
