package com.example.tuopet.utils;

import java.security.SecureRandom;

public class CodigoUtils {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String gerarCodigo() {
        int codigo = 1000 + secureRandom.nextInt(9000); // Gera número entre 1000 e 9999
        return String.valueOf(codigo);
    }

}
