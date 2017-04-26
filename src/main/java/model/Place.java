package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public class Place extends Node {

    List<Token> tokens;

    public Place(String ID) {
        super(ID);

        tokens = new ArrayList<Token>();
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public void removeToken(Token token) {
        tokens.remove(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

}
