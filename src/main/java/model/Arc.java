package model;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public class Arc {

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
