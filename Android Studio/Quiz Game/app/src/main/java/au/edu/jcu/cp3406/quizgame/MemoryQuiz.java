package au.edu.jcu.cp3406.quizgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class MemoryQuiz extends AppCompatActivity {
     // adding the images from drawables into a list to use
    private static final int[] easy = {
            R.drawable.easy1,
            R.drawable.easy2,
            R.drawable.easy3,
            R.drawable.easy4,
            R.drawable.easy5,
            R.drawable.easy6,
            R.drawable.easy7,
            R.drawable.easy8,
    };

    private static final int[] normal = {
            R.drawable.normal1,
            R.drawable.normal2,
            R.drawable.normal3,
            R.drawable.normal4,
            R.drawable.normal5,
            R.drawable.normal6,
            R.drawable.normal7,
            R.drawable.normal8,
    };

    private static final int[] hard = {
            R.drawable.hard1,
            R.drawable.hard2,
            R.drawable.hard3,
            R.drawable.hard4,
            R.drawable.hard5,
            R.drawable.hard6,
            R.drawable.hard7,
            R.drawable.hard8,
    };

    private ImageView imageDisplay;
    private TextView displayQuestion, displayScore;
    private EditText inputName;
    private Button choice1, choice2, choice3, choice4;
    private ArrayList<Questions> questionsArrayList;
    int score = 0, questionCount = 0, questionTotal = 0, imageCount = 0, correct, wrong;
    private String difficulty;
    private SoundPool soundPool;
    private LinearLayout questions, answers, gameOver;
    HighScoresDatabaseHelper highScoresDatabaseHelper;

    private Twitter twitter = TwitterFactory.getSingleton();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_quiz);

        displayQuestion = (TextView) findViewById(R.id.displayQuestion);
        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        choice4 = (Button) findViewById(R.id.choice4);

        questions = (LinearLayout) findViewById(R.id.questions);
        answers = (LinearLayout) findViewById(R.id.answers);
        gameOver = (LinearLayout) findViewById(R.id.gameOver);

        displayScore = (TextView) findViewById(R.id.displayScore);
        inputName = (EditText) findViewById(R.id.inputName);
        highScoresDatabaseHelper = new HighScoresDatabaseHelper(this);

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        correct = soundPool.load(this, R.raw.correct, 0);
        wrong = soundPool.load(this, R.raw.wrong, 0);

        // show settings screen immediately on create
        Intent intent = new Intent(this, Settings.class);
        startActivityForResult(intent, Settings.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // get string (easy, normal, hard) difficulty data back from settings
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Settings.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    difficulty = data.getStringExtra("difficulty");
                }
            }
        }

        startQuiz();
        // only view the imageView page
        answers.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);

    }

    private void startQuiz() {
        // changing the list of questions depending on the difficulty setting chosen
        questionsArrayList = new ArrayList<>();
        switch (difficulty) {
            case ("Easy"):
                easyImage(imageCount);
                easyQuestions(questionsArrayList);
                viewQuestions(questionCount);
                break;
            case ("Normal"):
                normalImage(imageCount);
                normalQuestions(questionsArrayList);
                viewQuestions(questionCount);
                break;
            case ("Hard"):
                hardImage(imageCount);
                hardQuestions(questionsArrayList);
                viewQuestions(questionCount);
                break;

        }

    }


    private void easyImage(int imageCount) {
        // setting imageView to the drawable image
        imageDisplay = (ImageView) findViewById(R.id.imageDisplay);
        imageDisplay.setImageDrawable(getResources().getDrawableForDensity(easy[imageCount], 0));

    }

    private void normalImage(int imageCount) {
        imageDisplay = (ImageView) findViewById(R.id.imageDisplay);
        imageDisplay.setImageDrawable(getResources().getDrawableForDensity(normal[imageCount], 0));

    }

    private void hardImage(int imageCount) {
        imageDisplay = (ImageView) findViewById(R.id.imageDisplay);
        imageDisplay.setImageDrawable(getResources().getDrawableForDensity(hard[imageCount], 0));

    }

    public void easyQuestions(ArrayList<Questions> questionsArrayList) {
        questionsArrayList.add(new Questions("Which is Potassium Chloride?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("Which is the flag of Argentina?", "1", "2", "3", "4", "2"));
        questionsArrayList.add(new Questions("Which is 0.67543 to 2 significant figures?", "1", "2", "3", "4", "2"));
        questionsArrayList.add(new Questions("Which is the smallest digit?", "1", "2", "3", "4", "1"));
        questionsArrayList.add(new Questions("Which is not a perfect square?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("Which is the square root of 81?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("Which is the gradient of Y = 4 - 2X", "1", "2", "3", "4", "1"));
        questionsArrayList.add(new Questions("Which is a triangle with 2 equal sides?", "1", "2", "3", "4", "2"));
    }

    public void normalQuestions(ArrayList<Questions> questionsArrayList) {
        questionsArrayList.add(new Questions("What date did Germany surrender in WW1", "1", "2", "3", "4", "3"));
        questionsArrayList.add(new Questions("Which is correct?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("How many states are there in Australia?", "1", "2", "3", "4", "3"));
        questionsArrayList.add(new Questions("44", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("How many bones are there in the human body?", "1", "2", "3", "4", "3"));
        questionsArrayList.add(new Questions("Who wrote 'The Great Gatsby'?", "1", "2", "3", "4", "1"));
        questionsArrayList.add(new Questions("What is the maximum number of electrons in the second shell?", "1", "2", "3", "4", "3"));
        questionsArrayList.add(new Questions("Which is the capital of Spain?", "1", "2", "3", "4", "3"));
    }

    public void hardQuestions(ArrayList<Questions> questionsArrayList) {
        questionsArrayList.add(new Questions("Which is the oldest city in the world?", "1", "2", "3", "4", "1"));
        questionsArrayList.add(new Questions("What is $80 - 11%?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("Which was not a founding member of NATO?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("Which elements can be found in the first column of the periodic table?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("Which is the hottest planet?", "1", "2", "3", "4", "1"));
        questionsArrayList.add(new Questions("Which is Potassium Chloride?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("When was WW1?", "1", "2", "3", "4", "4"));
        questionsArrayList.add(new Questions("'Polly Pocket Picked a Purple Plant' is an example of what?", "1", "2", "3", "4", "1"));
    }


    public void viewQuestions(int questionCount) {
        // when the total number of questions have been reached, show the gameover screen and display score
        if (questionTotal == 7) {
            displayScore.setText(Integer.toString(score));
            gameOver.setVisibility(View.VISIBLE);
            answers.setVisibility(View.INVISIBLE);
            questions.setVisibility(View.INVISIBLE);

        } else {
            displayQuestion.setText(questionsArrayList.get(questionCount).getQuestion());
            choice1.setText(questionsArrayList.get(questionCount).getChoice1());
            choice2.setText(questionsArrayList.get(questionCount).getChoice2());
            choice3.setText(questionsArrayList.get(questionCount).getChoice3());
            choice4.setText(questionsArrayList.get(questionCount).getChoice4());
        }


    }

    public void clickChoice1(View view) {
        // check if the choice matches the answer
        if (questionsArrayList.get(questionCount).getAnswer().equals(questionsArrayList.get(questionCount).getChoice1())) {
            soundPool.play(correct, 1, 1, 1, 0, 1);
            score++;
        } else {
            soundPool.play(wrong, 1, 1, 1, 0, 1);
        }
        imageCount++;
        questionTotal++;
        // make the necessary screen visible, to alternate between both answer and question
        answers.setVisibility(View.INVISIBLE);
        questions.setVisibility(View.VISIBLE);
        switch (difficulty) {
            case ("Easy"):
                easyImage(imageCount);
                break;
            case ("Normal"):
                normalImage(imageCount);
                break;
            case ("Hard"):
                hardImage(imageCount);
                break;

        }
        viewQuestions(questionCount);
    }


    public void clickChoice2(View view) {
        if (questionsArrayList.get(questionCount).getAnswer().equals(questionsArrayList.get(questionCount).getChoice2())) {
            soundPool.play(correct, 1, 1, 1, 0, 1);
            score++;
        } else {
            soundPool.play(wrong, 1, 1, 1, 0, 1);
        }
        imageCount++;
        questionTotal++;
        answers.setVisibility(View.INVISIBLE);
        questions.setVisibility(View.VISIBLE);
        switch (difficulty) {
            case ("Easy"):
                easyImage(imageCount);
                break;
            case ("Normal"):
                normalImage(imageCount);
                break;
            case ("Hard"):
                hardImage(imageCount);
                break;

        }
        viewQuestions(questionCount);


    }

    public void clickChoice3(View view) {
        if (questionsArrayList.get(questionCount).getAnswer().equals(questionsArrayList.get(questionCount).getChoice3())) {
            soundPool.play(correct, 1, 1, 1, 0, 1);
            score++;
        } else {
            soundPool.play(wrong, 1, 1, 1, 0, 1);
        }
        imageCount++;
        questionTotal++;
        answers.setVisibility(View.INVISIBLE);
        questions.setVisibility(View.VISIBLE);
        switch (difficulty) {
            case ("Easy"):
                easyImage(imageCount);
                break;
            case ("Normal"):
                normalImage(imageCount);
                break;
            case ("Hard"):
                hardImage(imageCount);
                break;

        }
        viewQuestions(questionCount);


    }

    public void clickChoice4(View view) {
        if (questionsArrayList.get(questionCount).getAnswer().equals(questionsArrayList.get(questionCount).getChoice4())) {
            soundPool.play(correct, 1, 1, 1, 0, 1);
            score++;
        } else {
            soundPool.play(wrong, 1, 1, 1, 0, 1);
        }
        imageCount++;
        questionTotal++;
        answers.setVisibility(View.INVISIBLE);
        questions.setVisibility(View.VISIBLE);
        switch (difficulty) {
            case ("Easy"):
                easyImage(imageCount);
                break;
            case ("Normal"):
                normalImage(imageCount);
                break;
            case ("Hard"):
                hardImage(imageCount);
                break;

        }
        viewQuestions(questionCount);

    }

    public void clickGo(View view) {
        questionCount++;
        questions.setVisibility(View.INVISIBLE);
        answers.setVisibility(View.VISIBLE);

    }

    public void clickAgain(View view) {
        Intent intent = new Intent(this, Landing.class);
        startActivity(intent);
    }

    public void clickShare(View view) {
        // twitter integration referenced from twitter4j demo
        // update status of dummymobile twitter account
        TwitterBackground.run(new Runnable() {
            @Override
            public void run() {
                try {
                    twitter.updateStatus("I scored" + " " + score + " " + "in the memory quiz!");
                } catch (TwitterException e) {
                    e.printStackTrace();
                }

            }
        });
        Toast.makeText(this, "Shared to Twitter!", Toast.LENGTH_SHORT).show();
    }

    public void clickAddScore(View view) {
        // add the name and score into database
        String name = inputName.getText().toString();
        String score = displayScore.getText().toString();
        highScoresDatabaseHelper.addScore(name, score);
        Toast.makeText(this, "Score added!", Toast.LENGTH_SHORT).show();

    }


}


