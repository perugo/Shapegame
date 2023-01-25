import java.util.Random;

public class Shape_Create{

  private int[][] dimension;
  private Shape_Info[] shape_info;
  private ShapeList shapelist;
  private int x;
  private int y;
  private final int[] shapenum; 
  public Shape_Create(){
    shape_info=null;
    shapenum=new int[]{6,7,7,8,9,11,13,15,15,15,15};
    this.x=5;
    this.y=3;
    shapelist=new ShapeList();
    dimension=new int[x][y];
  }
  public void make_shape_info(int playnum){
    int num=shapenum[playnum];
    for(int n=0;n<x;n++){
      for(int m=0;m<y;m++){
        dimension[n][m]=1;
      }  
    }
    shape_info=new Shape_Info[num];

    int n=-1;int m=-1;
    for(int i=0;i<num;i++){
      Random rand=new Random();
      int pic=shapelist.getshape();
      do{
        n=rand.nextInt(x);
        m=rand.nextInt(y);
      }while(dimension[n][m]==0);
        dimension[n][m]=0;
        shape_info[i]=new Shape_Info(pic,105+80*n,40+80*m);
    }
  }

  public Shape_Info[] get_shape_info(){
    return shape_info;
  } 
}