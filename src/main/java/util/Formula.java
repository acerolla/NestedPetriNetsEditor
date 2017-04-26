package util;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev) on 26.04.2017.
 */
public class Formula {

    private HashMap<String, Integer> variables;

    public Formula(String formula) {
        variables = new HashMap<String, Integer>();
        tryParse(formula);
    }

    public HashMap<String, Integer> getVariables() {
        return variables;
    }

    public String toString() {
        if (variables == null) {
            return "null";
        }
        String str = "";
        for (String variable : variables.keySet()) {
            if (variables.get(variable) > 1) {
                str += variables.get(variable) + "*" + variable;
            } else {
                str += variable;
            }

            str += " + ";

        }
        str = str.substring(0, str.length() - 3);
        return str;
    }

    //Получение формулы из строки
    private void tryParse(String formula) {
        formula = formula.replaceAll(" ", "");
        formula = formula.replaceAll( ",", "+");
        for (int i = 0; i < formula.length(); i++) {
            if (!legalSymbol(formula.charAt(i))) {
                throw new IllegalArgumentException("Illegal symbol in arc formula!");
            }
        }
        String[] variables = formula.split("\\+"); //WTF is dangling metacharacter???!!!

        for (String variable : variables) {
            Pair<String, Integer> pair = makePair(variable);
            if (this.variables.containsKey(pair.getKey())) {
                throw new IllegalArgumentException("Formula cannot contains the identical variables");
            }

            this.variables.put(pair.getKey(), pair.getValue());
        }
    }

    //Проверка на валидность символа в формуле
    private boolean legalSymbol(char smb) {
        if (smb >= 'a' && smb <= 'z') {
            return true;
        }

        if (smb >= 'A' && smb <= 'Z') {
            return true;
        }

        if (smb >= '0' && smb <= '9') {
            return true;
        }

        if (smb == '+' || smb == '*') {
            return true;
        }

        return false;
    }

    //Создание пары  (Переменная : количество) из строки
    private Pair<String, Integer> makePair(String variable) {

        String[] pair = variable.split("\\*");

        if (pair.length == 1 && onlyLatinLetters(pair[0])) {
            return new Pair<String, Integer>(variable, 1);
        } else if (pair.length == 2 && onlyNumbers(pair[0]) && onlyLatinLetters(pair[1])) {
            return new Pair<String, Integer>(pair[1], Integer.parseInt(pair[0]));
        }

        throw new IllegalArgumentException("Illegal arc formula!");
    }


    private boolean onlyNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }

    private boolean onlyLatinLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) < 'a' || str.charAt(i) > 'z') && (str.charAt(i) < 'A' || str.charAt(i) > 'Z')) {
                return false;
            }
        }

        return true;
    }

}
