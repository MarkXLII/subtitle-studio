package substu4;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Crazy_Coder
 */
public class SubStu3_1 
{
    public static void main(String[] args) 
    {
        try 
        {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } 
        catch (UnsupportedLookAndFeelException ex) 
        {
            Logger.getLogger(SubStu3_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MainWindow mw0 = new MainWindow();
        mw0.setResizable(false);
        mw0.setLocationRelativeTo(null);
        mw0.setVisible(true);  
    }
}