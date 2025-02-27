package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ResultAcitivity6 extends AppCompatActivity {

    TextView tv,tv2,tv3;
    Button btnRestart;
    CircularProgressBar circularProgressBar;
    TextView resultText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_acitivity6);


        tv=(TextView) findViewById(R.id.tvres);
        tv2=(TextView)findViewById(R.id.tvres2);
        tv3=(TextView)findViewById(R.id.tvres3);


        btnRestart=(Button) findViewById(R.id.btnRestart);
        resultText = findViewById(R.id.resulttext);

        circularProgressBar=(CircularProgressBar) findViewById(R.id.circularprogressbar);
        circularProgressBar.setProgress(QuestionActivity4.correct);


        StringBuffer sb=new StringBuffer();
        sb.append("Correct answer :" + QuestionActivity6.correct+"\n");

        StringBuffer sb2=new StringBuffer();
        sb2.append("Wrong answer :" + QuestionActivity6.wrong+"\n");

        StringBuffer sb3=new StringBuffer();
        sb3.append("Final Score :" + QuestionActivity6.correct+"\n");

        StringBuffer sb4 =new StringBuffer();
        sb4.append(QuestionActivity6.correct + "/"+ "5");

        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);
        resultText.setText(sb4);

        QuestionActivity6.correct=0;
        QuestionActivity6.wrong=0;

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ResultAcitivity6.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}