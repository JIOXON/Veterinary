package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class SimpleValidator {

    public static String stringValidator(String value, String element) throws Exception {
        if (value == null || value.equals("")) {
            throw new Exception(element + " no tiene un valor valido");
        }
        return value;
    }
    
    public Long longValidator(String value, String element) throws Exception {
        try {
            return Long.parseLong(stringValidator(value, element));
        } catch (Exception e) {
            throw new Exception(element + " debe ser un valor numerico");
        }
    }
    
    public static int intValidator(String input) throws Exception {
        try {
            int value = Integer.parseInt(input);
            if (value < 0) {
                throw new Exception("Minimo de edad 10 años");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new Exception("Formato incorrecto, ingrese un número válido.");
        }
    }
    public static float floatValidator(String input, String element) throws Exception {
        try {
            float value = Float.parseFloat(input);
            if (value < 0) {
                throw new Exception(element + " debe ser un número positivo.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new Exception("Formato incorrecto, ingrese un número válido para " + element);
        }
    }
}