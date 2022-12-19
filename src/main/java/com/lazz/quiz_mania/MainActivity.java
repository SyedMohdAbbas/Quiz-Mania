package com.lazz.quiz_mania;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    TextView totalQues ;
    TextView question;
    Button ans_A,ans_B,ans_C,ans_D;
    Button submitBtn;
     int score=0;
     int totalQuestion= QuestionAns.ques.length;
     int currentQuestionIndex=0;
     String selectedAns="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQues=findViewById(R.id.totalQues);
        question=findViewById(R.id.question);
        ans_A=findViewById(R.id.ans_A);
        ans_B=findViewById(R.id.ans_B);
        ans_C=findViewById(R.id.ans_C);
        ans_D=findViewById(R.id.ans_D);
        submitBtn=findViewById(R.id.submitBtn);
        ans_A.setOnClickListener(this);
        ans_B.setOnClickListener(this);
        ans_C.setOnClickListener(this);
        ans_D.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQues.setText("Total Question: "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        ans_A.setBackgroundColor(Color.WHITE);
        ans_B.setBackgroundColor(Color.WHITE);
        ans_C.setBackgroundColor(Color.WHITE) ;
        ans_D.setBackgroundColor(Color.WHITE);
        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.submitBtn){
            if(selectedAns.equals(QuestionAns.correct[currentQuestionIndex])){
                score++;}

            currentQuestionIndex++;
            loadNewQuestion();

            }
         else{
            selectedAns=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    void loadNewQuestion(){

        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }

        question.setText(QuestionAns.ques[currentQuestionIndex]);
        ans_A.setText(QuestionAns.choice[currentQuestionIndex][0]);
        ans_B.setText(QuestionAns.choice[currentQuestionIndex][1]);
        ans_C.setText(QuestionAns.choice[currentQuestionIndex][2]);
        ans_D.setText(QuestionAns.choice[currentQuestionIndex][3]);

    }

    void finishQuiz(){

        String passStatus="";
        if(score> totalQuestion*.60){
            passStatus="PASSED";
        }
        else{
            passStatus="BETTER LUCK NEXT TIME";

        }

        new AlertDialog.Builder(this)
        .setTitle(passStatus)
                .setMessage("Score is:" +score +"out of"+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
        .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
}