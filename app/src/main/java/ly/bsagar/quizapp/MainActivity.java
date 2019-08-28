package ly.bsagar.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAGG";
    // Variables Declarations
    TextView Q1,Q2,Q3,Q4,Q5;
    RadioGroup Q1_Gr, Q4_Gr;
    EditText Q2_Asnwer;
    CheckBox Q3_England, Q3_Virgin, Q3_Northen, Q3_Wales, Q3_Australia;
    EditText Q5_Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Variables initialization
        Q1_Gr = findViewById(R.id.Q1_Group);
        Q2_Asnwer = findViewById(R.id.Q2_Answer_Edit);
        Q3_England = findViewById(R.id.Q3_Check_England);
        Q3_Virgin = findViewById(R.id.Q3_Check_Virgin);
        Q3_Northen = findViewById(R.id.Q3_Check_Northen);
        Q3_Wales = findViewById(R.id.Q3_Check_Wales);
        Q3_Australia = findViewById(R.id.Q3_Check_Australia);
        Q4_Gr = findViewById(R.id.Q4_Group);
        Q5_Answer = findViewById(R.id.Q5_Answer_Edit);
        // question to show error if not answered
        Q1 = findViewById(R.id.Q1);
        Q2 = findViewById(R.id.Q2_TextView);
        Q3 = findViewById(R.id.Q3);
        Q4 = findViewById(R.id.Q4);
        Q5 = findViewById(R.id.Q5);
    }

    public void submit(View view) {
        if (!checkFields()){
            return;
        }
        validateAnswers();
    }

    private boolean checkFields(){
        // check if all questions are answered
        boolean isQuestionsAnswered = true;
        if (Q5_Answer.getText().toString().isEmpty()){
            Q5.setError(getString(R.string.pleaseWrite));
            Q5.requestFocus();
            isQuestionsAnswered = false;
        } else {
            Q5.setError(null);
        }
        if (Q4_Gr.getCheckedRadioButtonId() == -1){
            Q4.setError(getString(R.string.PleaseCheck));
            Q4.requestFocus();
            isQuestionsAnswered = false;
        } else {
            Q4.setError(null);
        }
        if (!Q3_Australia.isChecked() && !Q3_England.isChecked() && !Q3_Northen.isChecked() &&
                !Q3_Virgin.isChecked() && !Q3_Wales.isChecked()){
            Q3.setError(getString(R.string.pleaseCheckatLeast));
            Q3.requestFocus();
            isQuestionsAnswered = false;
        } else {
            Q3.setError(null);
        }
        if (Q2_Asnwer.getText().toString().isEmpty()){
            Q2.setError(getString(R.string.pleaseWrite));
            Q2.requestFocus();
            isQuestionsAnswered = false;
        } else {
            Q2.setError(null);
        }
        if (Q1_Gr.getCheckedRadioButtonId() == -1){
            Q1.setError(getString(R.string.PleaseCheck));
            Q1.requestFocus();
            isQuestionsAnswered = false;
        } else {
            Q1.setError(null);
        }
        return isQuestionsAnswered;
    }

    private void validateAnswers(){
        //get the answers
        int q1AnswerID = Q1_Gr.getCheckedRadioButtonId();
        String q2AnswerString = Q2_Asnwer.getText().toString();
        boolean q3Answer = Q3_England.isChecked() && Q3_Wales.isChecked() && Q3_Northen.isChecked()
                && !Q3_Australia.isChecked() && !Q3_Virgin.isChecked() ;
        int q4AnswerID = Q4_Gr.getCheckedRadioButtonId();
        String q5AnswerString = Q5_Answer.getText().toString();
        //Calculate the score
        int score = 0;
        // check if the answers are correct
        if (q1AnswerID == R.id.Q1_radio_Fiji) {
            score++;
        }
        if (q2AnswerString.toLowerCase().equals("georgia")){
            score++;
        }
        if (q3Answer){
            score++;
        }
        if (q4AnswerID == R.id.Q4_Radio_Asia){
            score++;
        }
        if (q5AnswerString.toLowerCase().equals("2010")){
            score++;
        }
        //Display the score
        Toast.makeText(this, getString(R.string.yourScore)+score+"/5", Toast.LENGTH_SHORT).show();
        //Clear all the answers
        Q1_Gr.clearCheck();
        Q2_Asnwer.setText("");
        Q3_Northen.setChecked(false);
        Q3_Wales.setChecked(false);
        Q3_Virgin.setChecked(false);
        Q3_Northen.setChecked(false);
        Q3_England.setChecked(false);
        Q3_Australia.setChecked(false);
        Q4_Gr.clearCheck();
        Q5_Answer.setText("");
    }

}
