package com.mosesasiago.hakiki111;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    Button playGame, leaderBoard, howToPlay, rateOurApp, signout;
    TextView name;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        playGame = (Button) findViewById(R.id.button_play_game);
        leaderBoard = (Button) findViewById(R.id.button_leader_board);
        howToPlay = (Button) findViewById(R.id.button_how_to_play);
        rateOurApp = (Button) findViewById(R.id.button_rate_our_app);
        signout = (Button) findViewById(R.id.btn_signout);
        name = (TextView) findViewById(R.id.textview_user_name);
        if (bundle != null) {
            String user_name = (String) bundle.get("user_name");
            name.setText(user_name);
        }else{
            user = FirebaseAuth.getInstance().getCurrentUser();
            name.setText(user.getDisplayName());
        }
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
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        });
            }
        });
    }
}
