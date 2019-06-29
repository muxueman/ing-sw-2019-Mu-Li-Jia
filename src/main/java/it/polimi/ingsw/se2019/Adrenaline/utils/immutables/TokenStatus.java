package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import org.fusesource.jansi.Ansi;

public class TokenStatus implements Status {

    private String token;

    public TokenStatus(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "This is your reconnection token: " + token;
    }

    @Override
    public Ansi toAnsi() {
        return Ansi.ansi().a(toString());
    }
}