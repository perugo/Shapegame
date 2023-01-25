import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;

public class ShapeMap{
    private Map<Integer,String> shapeMap=new HashMap<>();
    public ShapeMap(){
        Map<Integer,String> tmp_shapeMap=new HashMap<>();
        tmp_shapeMap.put(0,"円");
        tmp_shapeMap.put(1,"四角形");
        tmp_shapeMap.put(2,"三角形");
        tmp_shapeMap.put(3,"プラス");
        shapeMap=Collections.unmodifiableMap(tmp_shapeMap);
    }
    public String getStr(int num){
        return shapeMap.get(num);
    }
    public int getshape(){
        Random random=new Random();
        int x=random.nextInt(shapeMap.size());
        return x;
    }
    public int getsize(){
        return shapeMap.size();
    }
}