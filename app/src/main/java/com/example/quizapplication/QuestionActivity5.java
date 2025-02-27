package com.example.quizapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin.Result;

public class QuestionActivity5 extends AppCompatActivity {



    String questions[]={
            "Which of the following comment is correct when a macro definition includes arguments?",
            "What is a lint?",
            "What is the output of this statement \"printf(\"%d\", (a++))\"?",
            "Why is a macro used in place of a function?",
            "A pointer is a memory address. Suppose the pointer variable has p address 1000, and that p is declared to have type int*, and an int is 4 bytes long. What address is represented by expression p + 2?"



    };
    String answer[]={
            "The opening parenthesis should immediately follow the macro name",
            "Analyzing tool",
            "The current value of \"a\"",
            "It reduces code size",
            "1008"




    };
    String opt[]={
            "The opening parenthesis should immediately follow the macro name","There should be at least one blank between the macro name and the opening parenthesis","There should be only one blank between the macro name and the opening parenthesis","All the above comments are correct",

            "C compiler","Interactive debugger","Analyzing tool","C interpreter",

            "The value of (a + 1)","The current value of \"a\".","Error message","Garbage",

            "It reduces execution time","It reduces code size","It increases execution time"," It reduces code size",

            "1002","1004","1006","1008"


    };

    int flag=0;
    public static int marks=0,correct=0,wrong=0;




    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    private TextView questionnumber;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        final TextView score=(TextView) findViewById(R.id.textview4);
        TextView textview =(TextView) findViewById(R.id.DispName);
        Intent intent=getIntent();


        questionnumber=findViewById(R.id.DispName);
        submitbutton=(Button) findViewById(R.id.button3);
        quitbutton=(Button) findViewById(R.id.buttonquit);
        tv = (TextView) findViewById(R.id.tvque);


        radio_g= (RadioGroup) findViewById(R.id.answergrp);
        rb1=(RadioButton) findViewById(R.id.radiobutton);
        rb2=(RadioButton) findViewById(R.id.radiobutton2);
        rb3=(RadioButton) findViewById(R.id.radiobutton3);
        rb4=(RadioButton) findViewById(R.id.radiobutton4);


        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb1.setText(opt[1]);
        rb1.setText(opt[2]);
        rb1.setText(opt[3]);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radio_g.getCheckedRadioButtonId()==-1){
                    Toast.makeText(QuestionActivity5.this, "please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton uans=(RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText= uans.getText().toString();


                if(ansText.equals(answer[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity5.this, "Correct", Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                    Toast.makeText(QuestionActivity5.this, "wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                if(score!=null){
                    score.setText(""+correct);


                    if(flag< questions.length){
                        tv.setText(questions[flag]);
                        rb1.setText(opt[flag*4]);
                        rb2.setText(opt[flag*4+1]);
                        rb3.setText(opt[flag*4+2]);
                        rb4.setText(opt[flag*4+3]);
                        questionnumber.setText(flag+"/"+ questions.length+" Question");

                    }
                    else{
                        marks=correct;
                        Intent in= new Intent(QuestionActivity5.this, ResultAcitivity5.class);
                        startActivity(in);
                    }

                    radio_g.clearCheck();

                }

            }
        });


        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(getApplicationContext(),ResultAcitivity5.class);
                startActivity(intent1);
            }
        });




    }
}