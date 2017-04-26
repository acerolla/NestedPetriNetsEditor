package model;

import util.Formula;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public class Arc {

    private Formula formula;

    private Node source;
    private Node target;

    public Arc(Node source, Node target) {
        setRelation(source, target);
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    private void setRelation(Node source, Node target) {
        if (source.getClass() == target.getClass()) {
            throw new IllegalArgumentException("Cannot relate one type of vertices!");
        }
        for (Arc arc : source.getArcsFromNode()) {
            if (arc.getTarget() == target) {
                throw new IllegalArgumentException("Vertices has already been related!");
            }
        }

        this.source = source;
        this.target = target;
    }


}
