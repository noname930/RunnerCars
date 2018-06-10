/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demorunner;


import demorunner.graph.Edge;
import demorunner.graph.Graph;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author aless
 */
public class Mappa extends javax.swing.JFrame {

    /**
     * Creates new form Mappa
     */
    private final Border cornice = BorderFactory.createRaisedBevelBorder();
    private JPanel[][] graphic_map;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Graph g;
    private HashMap<Integer,int[][]> coordinate_nodi; 
    
    private final String auto1="/demorunner/utils/automobile.png";
    private final String auto2="/demorunner/utils/car.png";
    private final String place="/demorunner/utils/placeholder.png";
    private final String flag="/demorunner/utils/flag.png";
    private final String h_street="/demorunner/utils/horizontal_street.png";
    private final String v_street="/demorunner/utils/vertical_street.png";
    private final String tras1_street="/demorunner/utils/trasversal1_street.png";
    private final String tras2_street="/demorunner/utils/trasversal2_street.png";
    
    
    
    
    public Mappa() {
        graphic_map = new JPanel[18][30];
        g=new Graph(15);
        g.randomGraph(10,20);
        g.printGraph();
        initComponents();
        buildCoordinate();
        generatejPanels();
        generateObjects();
        buildStreets();
        this.setSize(screenSize);
      //  connectNodes(4,4,1,10);
     //   connectNodes(8,4,1,10);
     //   connectNodes(12,4,12,15);
     //   connectNodes(8,4,5,10);
     //   connectNodes(7,0,4,4);
     //   connectNodes(9,0,4,4);
     //   connectNodes(12,4,5,10);
     //   connectNodes(9,0,12,4);
     //   connectNodes(7,0,12,4);
    }
    
    public void buildCoordinate()
    {
        coordinate_nodi=new HashMap<>();
        coordinate_nodi.put(0, new int[][]{{7,0}});
        coordinate_nodi.put(1, new int[][]{{9,0}});
        coordinate_nodi.put(2, new int[][]{{4,4}});
        coordinate_nodi.put(3, new int[][]{{8,4}});
        coordinate_nodi.put(4, new int[][]{{14,4}});
        coordinate_nodi.put(5, new int[][]{{1,10}});
        coordinate_nodi.put(6, new int[][]{{5,10}});
        coordinate_nodi.put(7, new int[][]{{11,10}});
        coordinate_nodi.put(8, new int[][]{{15,10}});
        coordinate_nodi.put(9, new int[][]{{4,15}});
        coordinate_nodi.put(10, new int[][]{{8,15}});
        coordinate_nodi.put(11, new int[][]{{12,15}});
        coordinate_nodi.put(12, new int[][]{{5,20}});
        coordinate_nodi.put(13, new int[][]{{11,20}});
        coordinate_nodi.put(14, new int[][]{{8,25}});
    }
    
    public void generateObjects()
    {
        setObject_on_Map(graphic_map[7][0].getComponents()[0],auto1);
        setObject_on_Map(graphic_map[9][0].getComponents()[0],auto2);
        
        setObject_on_Map(graphic_map[4][4].getComponents()[0],place);
        setObject_on_Map(graphic_map[8][4].getComponents()[0],place);
        setObject_on_Map(graphic_map[12][4].getComponents()[0],place);
        
        setObject_on_Map(graphic_map[1][10].getComponents()[0],place);
        setObject_on_Map(graphic_map[5][10].getComponents()[0],place);
        setObject_on_Map(graphic_map[11][10].getComponents()[0],place);
        setObject_on_Map(graphic_map[15][10].getComponents()[0],place);
        
        setObject_on_Map(graphic_map[4][15].getComponents()[0],place);
        setObject_on_Map(graphic_map[8][15].getComponents()[0],place);
        setObject_on_Map(graphic_map[12][15].getComponents()[0],place);
        
        setObject_on_Map(graphic_map[5][20].getComponents()[0],place);
        setObject_on_Map(graphic_map[11][20].getComponents()[0],place);
        
        setObject_on_Map(graphic_map[8][25].getComponents()[0],flag);
               
    }
    
    public void generatejPanels()
    {
        for(int i=0 ; i<18 ; i++)
        {   
          for(int j=0 ; j<30; j++)
          {
            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            panel.add(label);
            panel.setName("jPanel"+i);
            panel.setBorder(cornice);
            jBackground.add(panel);
            graphic_map[i][j]=panel;
          }
        }
              
        
    }
 
    public void connectNodes(int x1, int y1, int x2, int y2)
    {
        int dislivelloX=x1-x2,dislivelloY=y1-y2;
        
        if(dislivelloX==0 && dislivelloY==0)
            return;
        
        /* CASO:
                 n2
        n1
        */
        if(dislivelloX>0 && dislivelloY<0)
        {   
            connectNodes(x1-1,y1+1,x2,y2);
            
            if(((JLabel)graphic_map[x1][y1].getComponents()[0]).getIcon() == null)
                setObject_on_Map(graphic_map[x1][y1].getComponents()[0],tras2_street);
            
            return;
        }
        
        
        /*
        CASO:
        n1
        
            n2
        */
        
        if(dislivelloX<0 && dislivelloY<0)
        {
            connectNodes(x1+1,y1+1,x2,y2);
            
            if(((JLabel)graphic_map[x1][y1].getComponents()[0]).getIcon() == null)
                setObject_on_Map(graphic_map[x1][y1].getComponents()[0],tras1_street);
            
            return;
        }
       
        /*
        CASO:      
        n1    n2
        */
        if(dislivelloX==0 && dislivelloY<0)
        {   
            connectNodes(x1,y1+1,x2,y2);
            
            if(((JLabel)graphic_map[x1][y1].getComponents()[0]).getIcon() == null)
                setObject_on_Map(graphic_map[x1][y1].getComponents()[0],h_street);
            
            return;
        }
        
  
        /*
        CASO         
        n2
        
        n1
        */       
        if(dislivelloX>0 && dislivelloY==0)
        {   
            connectNodes(x1-1,y1,x2,y2);
            
            if(((JLabel)graphic_map[x1][y1].getComponents()[0]).getIcon() == null)
                setObject_on_Map(graphic_map[x1][y1].getComponents()[0],v_street);
            
            return;
        }
        
        /*
        n1
        
        n2
        */
        
         if(dislivelloX<0 && dislivelloY==0)
        {   
            connectNodes(x1+1,y1,x2,y2);
            
            if(((JLabel)graphic_map[x1][y1].getComponents()[0]).getIcon() == null)
                setObject_on_Map(graphic_map[x1][y1].getComponents()[0],v_street);
            
            return;
        }
        
        
    }
    
    public void setObject_on_Map(Component c,String path_object)
    {
        ((JLabel) c).setIcon(new javax.swing.ImageIcon(getClass().getResource(path_object)));
    }
    
    public void buildStreets()
    {
        for(int i=0; i<g.getNode_count();i++)
        {
              for(Edge curr : g.getAdjGraph()[i])
                connectNodes(coordinate_nodi.get(i)[0][0],
                             coordinate_nodi.get(i)[0][1],
                             coordinate_nodi.get(curr.getTarget())[0][0],
                             coordinate_nodi.get(curr.getTarget())[0][1]);
                
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBackground.setLayout(new java.awt.GridLayout(18, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mappa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mappa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mappa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mappa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mappa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBackground;
    // End of variables declaration//GEN-END:variables
}
