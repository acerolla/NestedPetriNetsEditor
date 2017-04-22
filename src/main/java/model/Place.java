package model;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.04.2017.
 */
public class Place extends Node {

    //Имя позиции
    private String label;

    public Place(String ID) {
        super(ID);
        label = "";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String toString() {
        return ID + " (" + getLabel() + ")";
    }
}
