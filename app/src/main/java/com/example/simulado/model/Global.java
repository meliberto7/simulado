package com.example.simulado.model;

public class Global {

    private static boolean logado;

    public static boolean isLogado() {
        return logado;
    }

    public static void setLogado(boolean logado) {
        Global.logado = logado;
    }
}
