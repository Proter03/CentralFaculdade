package com.felipegabriel.centralfaculdade.domain;

import lombok.Getter;
import lombok.Setter;

public class Sessao {
    @Getter
    @Setter
    public static int id;
    @Getter
    @Setter
    public static String usuario;
    @Getter
    @Setter
    public static String senha;

    public static void setUser(int idUser, String user, String pass) {
        id = idUser;
        usuario = user;
        senha = pass;
    }

    public static void clearSession() {
        id = -1;
        usuario = null;
        senha = null;
    }
}
