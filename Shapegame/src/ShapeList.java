import java.util.Random;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeList{
    private List<String> shapeList=new ArrayList<String>();
    public ShapeList(){
        List<String> tmp_shapeList=new ArrayList<String>();
        tmp_shapeList.add("円");
        tmp_shapeList.add("四角形");
        tmp_shapeList.add("三角形");
        tmp_shapeList.add("プラス");
        shapeList=Collections.unmodifiableList(tmp_shapeList);
    }
    public String getStr(int num){
        return shapeList.get(num);
    }
    public int getshape(){
        Random random=new Random();
        int x=random.nextInt(shapeList.size());
        return x;
    }
    public int getsize(){
        return shapeList.size();
    }
}