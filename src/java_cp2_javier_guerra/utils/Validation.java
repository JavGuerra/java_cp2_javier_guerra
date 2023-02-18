package java_cp2_javier_guerra.utils;

/**
 * Validaciones de tipos de datos de entrada: NIF, teléfono y correo electrónico.
 * @author Javier Guerra
 */
public abstract class Validation {

    /**
     * Comprueba si tiene la forma de un NIF.
     * @param nif NIF.
     * @return true / false
     */
    public static boolean isNifFormat(String nif) {
        return !isEmpty(nif) && (nif.matches(
                "(\\d{1,8})" + "([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])"));
    }
    
    /**
     * Comprueba si tiene la forma de un número de teléfono.
     * @param phone número de teléfono.
     * @return true / false
     */
    public static boolean isPhoneFormat(String phone) {
        return !isEmpty(phone) && (phone.matches(
                "(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{3,4}"));
    }

    /**
     * Comprueba si email tiene la forma de un correo electrónico.
     * @param email Correo electrónico.
     * @return true / false
     */
    public static boolean isEmailFormat(String email) {
        return !isEmpty(email) && email.matches(
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    /**
     * Comprueba si una cadena de texto es null o está vacía.
     * @param str cadena de texto.
     * @return true si está vacía, false en caso contrario.
     */
    private static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
