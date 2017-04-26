package model;

import util.Formula;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public class Transition extends Node {

    private boolean isSynchronized;
    private boolean isAutonomic;

    public Transition(String ID) {
        super(ID);
        isAutonomic = true;
        isSynchronized = false;
    }

    //обавление формулы на дугу
    public void addFormulaToArc(Arc arc, String stringFormula) {

        Formula formula = new Formula(stringFormula);
        if (arcsToNode.contains(arc)) {
            validateFormulaForIncomingArc(formula);
            arc.setFormula(formula);
        } else if (arcsFromNode.contains(arc)) {
            validateFormulaForOutcomingArc(formula);
            arc.setFormula(formula);
        }
    }

    //переключение типа Перехода(автономный/синхронизированный)
    public void switchType() {
        isSynchronized = !isSynchronized;
        isAutonomic = !isAutonomic;
        if (isSynchronized) {
            setLabel("Synchronized");
        } else {
            setLabel("Automatic");
        }
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public boolean isAutonomic() {
        return isAutonomic;
    }

    //валидация формулы для входящей дуги в переход
    private void validateFormulaForIncomingArc(Formula formula) {
        for (String variable : formula.getVariables().keySet()) {
            if (variable.equalsIgnoreCase("const")) {
                throw new IllegalArgumentException("Incorrect formula!");
            }
            if (formula.getVariables().get(variable) > 1) {
                throw new IllegalArgumentException("Incorrect formula!");
            }
        }
    }

    //Валидцаия формулы для исходящей дуги из перехода
    private void validateFormulaForOutcomingArc(Formula formula) {
        for (String variable : formula.getVariables().keySet()) {
            if (!variable.equalsIgnoreCase("const")) {
                for (Arc arc : arcsToNode) {
                    if (!arc.getFormula().getVariables().containsKey(variable)) {
                        throw new IllegalArgumentException("Incorrect formula!");
                    }
                }
            }
        }
    }
}
