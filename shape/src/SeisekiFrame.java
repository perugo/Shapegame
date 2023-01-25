import java.io.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

import java.awt.Container;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;

import java.util.Timer;
import java.util.TimerTask;

public class SeisekiFrame extends JFrame{

  private JLabel label1;
  private JLabel label2;
  private JButton replay;
  private JButton close;
  public SeisekiFrame(int playnum,int correctnum,int seconds){
    setBounds(400, 400, 400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container contentPane = getContentPane();
    setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    String s1=Integer.toString(playnum);
    String s2=Integer.toString(correctnum);
    String s=s1+"門中、"+s2+"門正解しました";
    label1=new JLabel(s);
    label1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
    label1.setBounds(50,20,300,50);
    String s3=Integer.toString(seconds);
    s=s3+"秒かかりました";
    label2=new JLabel(s);
    label2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
    label2.setBounds(50,70,300,50);
    JPanel panel2=new JPanel();
    panel2.setLayout(null);
    panel2.setBackground(new Color(250,250,250));

    replay=new JButton("挑戦");
    close=new JButton("閉じる");
    replay.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD,16));
    close.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD,16));
    replay.setBounds(10,180,100,50);
    close.setBounds(130,180,100,50);
    replay.setBorderPainted(false);
    close.setBorderPainted(false);
    close.setBackground(new Color(250,250,250));
    replay.setFocusPainted( false );
    panel2.add(label1);
    panel2.add(label2);
    panel2.add(replay);
    panel2.add(close);
    contentPane.add(panel2);
    close.addActionListener(e-> {
      this.setVisible(false);
      System.exit(0);
    });
    replay.addActionListener(e-> {
      ShapeFrame shapeframe=new ShapeFrame();
      shapeframe.setVisible(true);
      this.setVisible(false);
    });

    replay.addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent evt){
        replay.setBackground(Color.pink);
      }
      public void mouseExited(MouseEvent evt){
        replay.setBackground(new Color(250,250,250));
      }
    });
    close.addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent evt){
        close.setBackground(Color.pink);
      }
      public void mouseExited(MouseEvent evt){
        close.setBackground(new Color(250,250,250));
      }
    });
  }


}
