package model;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public class Net {

    //ID сети, передается извне
    private String ID;

    //Имя сети
    private String label;

    private List<Node> nodes;

    private int countPlace;
    private int countTransition;

    public Net(String ID) {
        setID(ID);
        label = "";

        nodes = new ArrayList<Node>();

        countPlace = 0;
        countTransition = 0;
    }

    //Добавление узла в сеть
    public void addNode(boolean isPlace){
        if (isPlace) {
            nodes.add(new Place("P" + ++countPlace));
        } else {
            nodes.add(new Transition("T" + ++countTransition));
        }
    }

    //Удаление узла из сети
    public void removeNode(Node node) {

        //Удаление всех дуг, указывающих на данный узел
        for (Arc arc : node.getArcsToNode()) {
            arc.getSource().getArcsFromNode().remove(arc);
        }
        //Удаление всех дуг, идущих из данного узла
        for (Arc arc : node.getArcsFromNode()) {
            arc.getTarget().getArcsToNode().remove(arc);
        }
        nodes.remove(node);
    }

    //Добавление дуги в сеть
    public void addArc(Node from, Node to) {
        Arc arc = new Arc(from, to);
        from.addArcFromThisNode(arc);
        to.addArcToThisNode(arc);
    }

    //Удаление дуги из сети
    public void removeArc(Arc arc) {
        arc.getSource().getArcsFromNode().remove(arc);
        arc.getTarget().getArcsToNode().remove(arc);
    }

    //Нахождение узла по ID (Например, getNode("P1")), если такой есть
    public Node getNode(String ID) {
        Node node = null;
        for (Node n : nodes) {
            if (n.getID().equals(ID)) {
                node = n;
            }
        }
        if (node == null) {
            throw new IllegalArgumentException("There is no such vertex!");
        }

        return node;
    }

    //Нахождение дуги между двумя узлами, если такая есть
    public Arc getArc(Node from, Node to) {
        Arc arc = null;
        for (Arc a : from.getArcsFromNode()) {
            if (a.getTarget() == to) {
                arc = a;
            }
        }
        if (arc == null) {
            throw new IllegalArgumentException("There is no arc between these nodes!");
        }

        return arc;
    }

    public String getID() {
        return ID;
    }

    private void setID(String ID) {
        if (ID == null) {
            throw new NullPointerException("Net ID cannot be null!");
        }
        this.ID = ID;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String getLabel() {
        return label;
    }

}
