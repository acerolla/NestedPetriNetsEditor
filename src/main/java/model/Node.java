package model;


import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public abstract class Node {

    /**
     * ID узла
     * для позиции имеет вид P{порядковый номер при добавлении}: P1, P2, P3 etc.
     * для перехода имеет вид T{порядковый номер при добавлении} T1, T2, T3 etc.
     */
    @NotNull
    protected String ID;

    //список всех входящих в узел дуг
    protected List<Arc> arcsToNode;
    //список всех исходящих из узла дуг
    protected List<Arc> arcsFromNode;

    protected Node(String ID) {
        this.ID = ID;

        arcsToNode = new ArrayList<Arc>();
        arcsFromNode = new ArrayList<Arc>();
    }

    public String getID() {
        return ID;
    }

    public List<Arc> getArcsToNode() {
        return arcsToNode;
    }

    public List<Arc> getArcsFromNode() {
        return arcsFromNode;
    }

    //Добавление дуги, исходящей из данного узла
    public void addArcFromThisNode(Arc arc) {
        arcsFromNode.add(arc);
    }

    //Добавление дуги, идущей в данный узел
    public void addArcToThisNode(Arc arc) {
        arcsToNode.add(arc);
    }


    public String toString() {
        return getID();
    }
}
