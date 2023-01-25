import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Manege{
  Shape_Create shape_create;
  Question_Create question_create;
  public Manege(){
    shape_create=new Shape_Create();
    question_create=new Question_Create();
  }

  public Shape_Info[] get_shape_info(int playnum){
    shape_create.make_shape_info(playnum);
    Shape_Info[] shape_info =shape_create.get_shape_info();
    return shape_info;
  }
  public Question_Info get_question_info(Shape_Info[] shape_info,int playnum){
    question_create.make_question_info(shape_info,playnum);
    Question_Info question_info=question_create.get_question_info();
    return question_info;
  } 
}