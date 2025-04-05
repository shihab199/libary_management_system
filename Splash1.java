
package librarymanagementsystem;
   import javax.swing.*;
   import java.awt.*;
 
public class Splash1 extends JFrame implements Runnable{
    Thread t;
    Splash1(){
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("splash.png"));
        Image i2=i1.getImage().getScaledInstance(700,600,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image =new JLabel(i3);
        add(image);
        
        t=new Thread(this);      
        t.start(); // calling run method
         setVisible(true);
        setLocation(350,50);
        setSize(700,600);
       
    }
    public void run(){
        try{
            Thread.sleep(5000);
            setVisible(false);
            new Security2();
        }catch(Exception e){
            
        }
    }
    
    public static void main(String[] args) {
        new Splash1();
    }
}



