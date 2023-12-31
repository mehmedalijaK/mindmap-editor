package dsw.gerumap.app.gui.swing.view;


import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.ActionManager;
import dsw.gerumap.app.gui.swing.controller.MainWindowListener;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame {

    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MapTree mapTree;
    private ProjectView projectView;

    private void initialise() {
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();

        initialiseGUI();
    }

    private void initialiseGUI() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imgM = new ImageIcon(getClass().getResource("/images/logo.png"));
        this.setIconImage(imgM.getImage());
        setTitle("GeRuMap app");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new Toolbar();
        add(toolBar, BorderLayout.NORTH);


        JTree projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());


        projectView = new ProjectView();
        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll, projectView);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
        addWindowListener(new MainWindowListener());
    }

    //Singleton

    private static MainFrame instance;

    private MainFrame(){

    }

    public static MainFrame getInstance(){
        if(instance==null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

}
