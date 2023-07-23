package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.Message;
import dsw.gerumap.app.core.Message.MessageType;
import dsw.gerumap.app.core.observer.ISubscriber;
import dsw.gerumap.app.gui.swing.controller.MouseCtrl;
import dsw.gerumap.app.gui.swing.view.Painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.Painters.FigurePainter;
import dsw.gerumap.app.gui.swing.view.Painters.SelectionSquarePainter;
import dsw.gerumap.app.repository.implementation.MindMap;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapView extends JPanel implements ISubscriber {

    private transient List<ElementPainter> painters= new ArrayList<ElementPainter>();
    private MindMap m;
    private AffineTransform t = new AffineTransform();
    private double translateX = 0;
    private double translateY = 0;
    private double scaling = 1;
    public MapView(MindMap m){
        this.m = m;
        MouseCtrl mc = new MouseCtrl();
        this.addMouseListener(mc);
        this.addMouseMotionListener(mc);

    }
    @Override
    public void update(Object notification) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ((Graphics2D)g).setTransform(t);


        for(ElementPainter p : painters){
            if(!(p instanceof FigurePainter) || !(p instanceof SelectionSquarePainter))
                p.draw((Graphics2D) g);
        }
        for(ElementPainter p : painters){
            if(p instanceof FigurePainter)
                p.draw((Graphics2D) g);
        }
        for(ElementPainter p : painters){
            if(p instanceof SelectionSquarePainter)
                p.draw((Graphics2D) g);
        }

        scaling = 1;
    }

    public void zoomIn(){

        this.scaling += 0.2;
        //if(scaling > 5)
        //   scaling = 5;

        translateX = (getWidth()  - scaling*getSize().width)/2;
        translateY = (getHeight() - scaling*getSize().height)/2;
        t.translate(translateX, translateY);
        t.scale(scaling, scaling);
        repaint();
    }

    public void zoomOut(){
        this.scaling -= 0.2;
        //if(scaling < 0.2)
        //    scaling = 0.2;

        translateX = (getWidth()  - scaling*getSize().width)/2;
        translateY = (getHeight() - scaling*getSize().height)/2;
        t.translate(translateX, translateY);
        t.scale(scaling, scaling);
        repaint();
    }

    public void capture(){
        ApplicationFramework.getInstance().getMessageObserver().generate(new Message("MindMap captured!", MessageType.INFORMATION));
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        String name = m.getName().toLowerCase();
        if(name.contains(".json"))
            name = m.getName().substring(0, m.getName().indexOf('.'));
        try{
            ImageIO.write(image, "png", new File("src/main/resources/Captures/" + name + "."+"png"));
        }
        catch(Exception e){

        }
    }

}
