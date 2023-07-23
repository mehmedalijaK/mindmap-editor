package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.repository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SelectionModel {
    public static List<Element> selected = new ArrayList<>();

}
