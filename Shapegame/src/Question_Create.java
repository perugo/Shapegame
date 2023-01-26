import java.util.Random;

public class Question_Create{
    int[] selected_shape;
    ShapeMap shapemap;  
    Question_Info question_info;
    private final int[] question_type_array;
    public Question_Create(){
        question_type_array=new int[]{0,0,0,1,1,2,1,2,1,2,2};
        shapemap=new ShapeMap();
        selected_shape=new int[2];
        question_info=null;
    }
    public void make_question_info(Shape_Info[] shape_info, int playnum){
        int shape_size=shapemap.getsize();
        int[] shape_num=new int[shape_size];

        for(int i=0;i<shape_num.length;i++){
            shape_num[i]=0;
        }
        int question_type=question_type_array[playnum];
        for(int i=0;i<shape_info.length;i++){
            int x=shape_info[i].get_num();
            shape_num[x]+=1;
        }

        int answer=-1;
        if(question_type==0){
            answer=keisan_type0(shape_num);
        }else if(question_type==1){
            answer=keisan_type1(shape_num);
        }else if(question_type==2){
            answer=keisan_type2(shape_num);
        }

        int[] answer_choice=new int[3];
        answer_choice=make_answer_choice(answer);
        String sentence=get_sentence(question_type);
        int answer_index=get_answer_index(answer,answer_choice);

        this.question_info=new Question_Info(answer_index,answer_choice,sentence);
    }
    public Question_Info get_question_info(){
        return this.question_info;
    }
    private int keisan_type0(int[] shape_num){ //三角形はいつくあるでしょうか？　用
        Random rand=new Random();
        int tmp;
        do{
            tmp=rand.nextInt(shape_num.length);
        }while(shape_num[tmp] == 0);
        this.selected_shape[0]=tmp;
        return shape_num[tmp];
    }
    private int keisan_type1(int[] shape_num){  //三角形と丸は足して、いくつでしょうか？　用
        Random rand=new Random();
        int tmp;
        do{
            tmp=rand.nextInt(shape_num.length);
        }while(shape_num[tmp] == 0);
        this.selected_shape[0]=tmp;
        int tmp2;
        int breaker=0; //ブレイカー 全てが一つの図形のみという可能性がある
        do{
            tmp2=rand.nextInt(shape_num.length);
            breaker++;
            if(breaker==20){break;}
        }while(shape_num[tmp2] == 0 || tmp==tmp2);
        if(breaker==20){
            System.out.println("●●から●●を足して、いくつか？ と出題されます");
            tmp2=tmp;
        }
        this.selected_shape[1]=tmp2;

        return ( shape_num[tmp]+shape_num[tmp2] );
    }
    private int keisan_type2(int[] shape_num){  //三角形から四角を引いて、いくつでしょうか？
                                                //引き算のアルゴリズムを理解するのはかなり難しいです。
                                                //飛ばしてok
        int[] keep_shape_num=new int[shape_num.length];
        for(int i=0;i<shape_num.length;i++){
            keep_shape_num[i]=shape_num[i];
        }
        int[] tmp_arr=new int[shape_num.length];
        for(int i=0;i<shape_num.length;i++){
            tmp_arr[i]=i;
        }
        for (int i=0;i<shape_num.length-1;i++) {
            for (int j = shape_num.length-1; j>i;j--) {
                if (shape_num[j-1] > shape_num[j]) {
                    int t=shape_num[j-1]; int t2=tmp_arr[j-1];
                    shape_num[j-1]=shape_num[j]; tmp_arr[j-1]=tmp_arr[j];
                    shape_num[j]=t; tmp_arr[j]=t2;
                }
             }
        }

        Random rand=new Random();
        int tmp;
        
        tmp=rand.nextInt(shape_num.length-1)+1;
        while(tmp<shape_num.length-2 && shape_num[tmp]==shape_num[tmp+1]){
           tmp++;
        }
        int tmp2;
        int breaker=0;
        do{
            tmp2=rand.nextInt(shape_num.length-1);
            breaker++;
            if(breaker==20){break;}
        }while(shape_num[tmp2] == 0 || tmp2>=tmp);

        if(breaker==20){
            System.out.println("●●から●●を引いて、いくか？ と出題されます");
            System.out.println("答えは0になります");
            tmp2=tmp;
        }
        tmp=tmp_arr[tmp];
        tmp2=tmp_arr[tmp2];
        this.selected_shape[0]=tmp;
        this.selected_shape[1]=tmp2;
        return ( keep_shape_num[tmp] - keep_shape_num[tmp2] );
    }
    private int[] make_answer_choice(int answer){ //正解の数字をもとに、間違った選択肢をつくる
                                                  //メソッドです。
        int x=3;
        int[] tmp_answer_choice=new int[x]; //x=3
        tmp_answer_choice[0]=answer;
        
        int[] zone={-3,-2,-1,1,2,3};  //正解の数字が5 であれば 間違った選択肢は 2,3,4,6,7,8です。
                                      //間違った解答と正解の数字の合計３つが選択肢です。
                                      //選択肢はシャッフルされます。
        for(int i=1;i<x;i++){
            int r;
            do{
                Random random=new Random();
                r=random.nextInt(6);
            }while(zone[r]==0 || (answer+zone[r]) < 0);
            tmp_answer_choice[i]=answer+zone[r];
            zone[r]=0;
        }
        Random random=new Random();
        int r=random.nextInt(x);
        int t=tmp_answer_choice[0];
        tmp_answer_choice[0]=tmp_answer_choice[r];
        tmp_answer_choice[r]=t;

        return tmp_answer_choice;
    }

    private String get_sentence(int keisan_type){
        String bun="";
        String s,s1,s2;
        if(keisan_type==0){
            s=shapemap.getStr(selected_shape[0]);
            bun=s+"はいくつですか？";
        }else if(keisan_type==1){
            s1=shapemap.getStr(selected_shape[0]);
            s2=shapemap.getStr(selected_shape[1]);
            bun=s1+"と"+s2+"を足して、いくつですか？";
        }else if(keisan_type==2){
            s1=shapemap.getStr(selected_shape[0]);
            s2=shapemap.getStr(selected_shape[1]);
            bun=s1+"から"+s2+"を引いて、いくつですか？";
        }else{
            bun="【エラー】Questionでエラーが発生";
        }
        
        return bun;
    }

    private int get_answer_index(int answer,int[] answer_choice){ //正解の選択肢が左から何番目に位置するのかを取得します。
                                                                  //これで正否の判定をします。
                                                                  //例 正解の数字が３だとして
                                                                  //選択肢が 4,5,3
                                                                  //2をanser_indexにします。 (右のボタンが正解になる)
        for(int i=0;i<answer_choice.length;i++){
            if(answer_choice[i]==answer){
                return i;
            }
        }
        return -1;
    }
}