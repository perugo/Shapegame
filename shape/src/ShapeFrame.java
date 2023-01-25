import java.io.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

import java.awt.Container;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ShapeFrame extends JFrame{

  public Bt_Choice[] bt_choice;
  private JLabel label1;
  private BackgroundPanel backgroundpanel;
  private BackgroundPanel2 backgroundpanel2;
  private JButton bt_start;
  private JButton bt_restart;

  private Manege manege;
  private int playnum;
  private int correctnum;
  private LocalDateTime now;
  private Question_Info question_info;
  public ShapeFrame(){
    manege=new Manege();
    question_info=null;
    playnum=0;
    correctnum=0;
    this.now=null;
    

    setBounds(100, 100, 600, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container contentPane = getContentPane();
    setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    String url;
    ImageIcon wrong=new ImageIcon(this.getClass().getResource("/image/-2.png"));
    ImageIcon correct=new ImageIcon(this.getClass().getResource("/image/-1.png"));
    backgroundpanel2= new BackgroundPanel2(wrong,correct);
    backgroundpanel2.setBackground(new Color(250,250,250));
    backgroundpanel2.setPreferredSize(new Dimension(600, 200));
    backgroundpanel2.setLayout(null);
    
    ImageIcon haike=new ImageIcon(this.getClass().getResource("/image/haike.jpg"));
    backgroundpanel =new BackgroundPanel(haike);

    backgroundpanel.setBackground(new Color(250,250,250));
    backgroundpanel.setPreferredSize(new Dimension(600, 300));
    backgroundpanel.setLayout(null);
    bt_start=new JButton();
    bt_restart=new JButton();
    url="./image/restart.png";
    ImageIcon restart=new ImageIcon(this.getClass().getResource("/image/restart.png"));
    Icon icon_restart=new ImageIcon(restart.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    bt_restart.setIcon(icon_restart);
    bt_restart.setBounds(0,0,60,60);
    bt_restart.setBorderPainted(false);
    backgroundpanel.add(bt_restart);
    bt_restart.setVisible(false);

    ImageIcon start=new ImageIcon(this.getClass().getResource("/image/start.png"));
    Icon icon_start= new ImageIcon(start.getImage().getScaledInstance(340, 100, Image.SCALE_DEFAULT));
    
    bt_start.setIcon(icon_start);
    bt_start.setVisible(true);

    bt_start.setBounds(130,100,340,100);
    bt_start.setBorderPainted(false);
    backgroundpanel.add(bt_start);
    bt_choice=new Bt_Choice[3];
    for(int i=0;i<3;i++){
      bt_choice[i]=new Bt_Choice(i);
      bt_choice[i].setBounds(100+140*i,50,100,100);
      backgroundpanel2.add(bt_choice[i]);
    }
    
    label1=new JLabel("");
    label1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
    
    label1.setBounds(50,2,420,50);
    backgroundpanel2.add(label1);

    contentPane.add(backgroundpanel);
    contentPane.add(backgroundpanel2);
    bt_start.addActionListener(e-> {
      mondai_sakusei();//
      now = LocalDateTime.now(); //
      bt_start.setVisible(false);
      bt_restart.setVisible(true);
    });
    bt_restart.addActionListener(e->{
      playnum=0; correctnum=0;//
      for(int i=0;i<bt_choice.length;i++){
        bt_choice[i].setVisible(false);
      }
      backgroundpanel.set_reset();
      backgroundpanel.repaint();
      label1.setText("");
      TimerTask task = new TimerTask() {
      public void run() {
        bt_start.setVisible(true);
      }
      };
      Timer timer = new Timer();
      timer.schedule(task, 300);
      
    });
    bt_choice[0].addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent evt){
        bt_choice[0].setBackground(Color.pink);
      }
      public void mouseExited(MouseEvent evt){
        bt_choice[0].setBackground(new Color(250,250,250));
      }
    });
    bt_choice[1].addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent evt){
        bt_choice[1].setBackground(Color.pink);
      }
      public void mouseExited(MouseEvent evt){
        bt_choice[1].setBackground(new Color(250,250,250));
      }
    });
    bt_choice[2].addMouseListener(new MouseAdapter(){
      public void mouseEntered(MouseEvent evt){
        bt_choice[2].setBackground(Color.pink);
      }
      public void mouseExited(MouseEvent evt){
        bt_choice[2].setBackground(new Color(250,250,250));
      }
    });
  }
  
  class Bt_Choice extends JButton implements ActionListener{
    private int n;
    protected Bt_Choice(int n){
      this.n=n;
      setVisible(false);
      addActionListener(this);
      setFont(new Font("Arial", Font.BOLD, 30));
      setBorderPainted(false);
      setBackground(new Color(250,250,250));
      setFocusPainted( false );
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      kaito(n);
    }
  }
  class BackgroundPanel extends JPanel{ //上のパネル
    private Shape_Info[] shape_info;
    private ImageIcon icon;
    private boolean reset;
    protected BackgroundPanel(ImageIcon icon){
        this.shape_info=null;
        this.icon=icon;
        this.reset=false;
    }
    protected void setIcon(Shape_Info[] shape_info){
        this.shape_info=shape_info;
    }
    protected void set_reset(){
      this.reset=true;
    }
    protected void paintComponent(Graphics g){
      super.paintComponent(g);
      if(this.shape_info!=null){
        g.drawImage(icon.getImage(),80,20,433,260,this); //背景の絵
        for(int i=0;i<shape_info.length;i++){
          Shape_Info si=shape_info[i];
          g.drawImage(si.get_icon().getImage(),si.get_x(),si.get_y(),60,60,this);
          //図形の絵        
        }
      }
      if(reset==true){
        g.setColor(new Color(250,250,250));
        g.fillRect(0,0,600,300);
        this.reset=false;
      }
    }
  }
  class BackgroundPanel2 extends JPanel{ //下のパネル
    public ImageIcon correct;
    public ImageIcon wrong;

    private int state;
    protected BackgroundPanel2(ImageIcon wrong,ImageIcon correct){
      this.correct=correct;
      this.wrong=wrong;
      this.state=0; 
    }
    
    protected void setResult(int state){
        this.state=state;
    }

    protected void paintComponent(Graphics g){
      super.paintComponent(g);
      if(this.state!=0){
        if(this.state==-2){ //間違っている場合は-2
          g.drawImage(wrong.getImage(),200,20,160,160,this);
        }
        if(this.state==-1){ //正解の場合は-1
          g.drawImage(correct.getImage(),200,20,160,160,this);
        }
        this.state=0;
        
      }
    }
  }
  
  protected void mondai_sakusei(){  //問題作成ユースケース
    backgroundpanel2.repaint();
    if(playnum==10){
      seiseki_form(); //評価ユースケース
    }
    Shape_Info[] shape_info=manege.get_shape_info(playnum); //図形情報取得
    backgroundpanel.setIcon(shape_info);//図形情報設定
    this.question_info=manege.get_question_info(shape_info,playnum); //問題情報取得
    
    int[] answer_choice=question_info.get_answer_choice();//問題情報設定 始
    for(int i=0;i<answer_choice.length;i++){
      bt_choice[i].setText(Integer.toString(answer_choice[i]));
    }
    label1.setText(question_info.get_sentence());        //問題情報設定　終


    backgroundpanel.repaint();
    
    for(int i=0;i<bt_choice.length;i++){
      bt_choice[i].setVisible(true);
    }
    playnum++; //プレイ回数+1
  }

  public void kaito(int x){   //解答ユースケース
    for(int i=0;i<bt_choice.length;i++){
      bt_choice[i].setVisible(false);
    }

    int seihi=0; //正否を区別する変数 -1 正解 -2 不正解
    if(x==question_info.get_answer_index()){
      x=-1;
      correctnum++; //正答数+1
    }else{
      x=-2;
    }
    
    backgroundpanel2.setResult(x);
    backgroundpanel2.repaint();
    
    TimerTask task = new TimerTask() {
      public void run() {
        mondai_sakusei();
      }
    };
    Timer timer = new Timer();
    timer.schedule(task, 700);
  }
  protected void seiseki_form(){  //評価フォーム作成ユースケース
    LocalDateTime dd = LocalDateTime.now();  
    int diffInSeconds = (int) java.time.Duration.between(now,dd).getSeconds();
    SeisekiFrame seisekiframe=new SeisekiFrame(playnum,correctnum,diffInSeconds);
    seisekiframe.setVisible(true);
    this.setVisible(false);
  }
}