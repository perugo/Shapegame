import javax.swing.*;

public class Shape_Info{

    private ImageIcon icon;
    private int num;
    private int x;
    private int y;
    public Shape_Info(int a_num,int a_x,int a_y){
        icon=get_shape_icon(a_num);
        num=a_num;
        x=a_x;
        y=a_y;
    }
    private ImageIcon get_shape_icon(int number){ 
        ImageIcon icon=new ImageIcon(this.getClass().getResource("/image/"+number+".png"));
        return icon;
        
    }
    public int get_num(){
        return num;
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public ImageIcon get_icon(){
        return icon;
    }
    
}