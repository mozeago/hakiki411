package com.mosesasiago.hakiki111;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    Button playGame, leaderBoard, howToPlay, rateOurApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        playGame = (Button) findViewById(R.id.button_play_game);
        leaderBoard = (Button) findViewById(R.id.button_leader_board);
        howToPlay = (Button) findViewById(R.id.button_how_to_play);
        rateOurApp = (Button) findViewById(R.id.button_rate_our_app);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                startActivity(intent);
            }
        });
        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderBoardActivity.class);
                startActivity(intent);
            }
        });
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HowToPlayActivity.class);
                startActivity(intent);
            }
        });
        rateOurApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RateOurAppActivity.class);
                startActivity(intent);
            }
        });
    }
}
