public class Question_Info{
    private int answer_index;
    private int[] answer_choice;
    private String sentence;

    public Question_Info(int answer_index,int[] answer_choice,String sentence){
        this.answer_index=answer_index;
        this.answer_choice=answer_choice;
        this.sentence=sentence;
    }
    public String get_sentence(){
        return sentence;
    }
    public int[] get_answer_choice(){
        return answer_choice;
    }
    public int get_answer_index(){
        return answer_index;
    }
}