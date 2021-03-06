package model;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
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

    private int placeLastNumber;
    private int transitionLastNumber;

    public Net(String ID) {
        setID(ID);
        label = "";

        nodes = new ArrayList<Node>();

        placeLastNumber = 0;
        transitionLastNumber = 0;
    }

    //Добавление позиции в сеть
    public void addPlace() {
        nodes.add(new Place("P" + ++placeLastNumber));
    }

    //Добавление перехода в сеть
    public void addTransition() {
        nodes.add(new Transition("T" + ++transitionLastNumber));
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

    //список переходов в сети
    public List<Transition> getTransitions() {
        List<Transition> list = new ArrayList<Transition>();
        for (Node node : nodes) {
            if (node.getClass() == Transition.class) {
                list.add((Transition)node);
            }
        }

        return list;
    }

    //список позиций в сети
    public List<Place> getPlaces() {
        List<Place> list = new ArrayList<Place>();
        for (Node node : nodes) {
            if (node.getClass() == Place.class) {
                list.add((Place)node);
            }
        }


        return list;
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
