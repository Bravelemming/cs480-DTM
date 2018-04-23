/*
Author: Sam Alston, Tom Murphy, Jack (Daniel) Kinne [STD]
Last Modified: 4/15/2018
Purpose: PresentClue displays a TextView with a clue and then disappears, returning to GetClues.
 */
package std.dtm;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static std.dtm.AskQuestion.EXTRA_MESSAGE;

public class PresentClue extends AppCompatActivity {

    private String btnNumber;
    private String clueString;
    private TextView clueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_clue);

        Intent thisIntent = getIntent();
        btnNumber = thisIntent.getStringExtra(EXTRA_MESSAGE);

        clueTextView = (TextView) findViewById(R.id.cluetextview);

        switch (btnNumber) {
            case "1":
                clueString = "SUMMARY: "+GetClues.currentMovie.getSummary();
                MainActivity.user.subtractBalance(GetClues.clueOneValue);
                break;
            case "2":
                clueString = "STARRING: "+GetClues.currentMovie.getActors();
                MainActivity.user.subtractBalance(GetClues.clueTwoValue);
                break;
            case "3":
                clueString = "DIRECTED BY: "+GetClues.currentMovie.getDirectedBy()+"\nWRITTEN BY: "+GetClues.currentMovie.getWrittenBy();
                MainActivity.user.subtractBalance(GetClues.clueThreeValue);
                break;
            case "4":
                clueString = "RELEASED: "+GetClues.currentMovie.getReleaseDate()+"\nBY: "+GetClues.currentMovie.getProduction();
                MainActivity.user.subtractBalance(GetClues.clueFourValue);
                break;
            case "5":
                clueString = "GENRE: "+GetClues.currentMovie.getGenre()+"\nRATED: "+GetClues.currentMovie.getRated();
                MainActivity.user.subtractBalance(GetClues.clueFiveValue);
                break;
            case "6":
                clueString = "WINNER OF: "+GetClues.currentMovie.getAwards();
                MainActivity.user.subtractBalance(GetClues.clueSixValue);
                break;
        }



        clueTextView.setText(clueString);
        if(MainActivity.settings.isTimerSet()) {
            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    TextView count = (TextView) findViewById(R.id.timerTextView);
                    count.setText("seconds remaining: " + (millisUntilFinished / 1000 + 1));
                }

                public void onFinish() {
                    finish();
                }
            }.start();
        } else {
            TextView count = (TextView) findViewById(R.id.timerTextView);
            count.setText("No time limit.");
        }

    }
}
