package com.mosesasiago.hakiki111;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.mosesasiago.hakiki111.models.News;
import com.mosesasiago.hakiki111.interfaces.NewsJsonAPi;
import com.mosesasiago.hakiki111.config.Config;

import java.util.List;

public class PlayGameActivity extends AppCompatActivity {
    ImageView back_button;
    CircleImageView profile_photo;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    FirebaseUser user;
    TextView newsholder;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("news_game");
        setContentView(R.layout.activity_play_game);
        user = FirebaseAuth.getInstance().getCurrentUser();
        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        newsholder = (TextView) findViewById(R.id.newsholder);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsJsonAPi newsJsonAPi = retrofit.create(NewsJsonAPi.class);
        Call<List<News>> news = newsJsonAPi.getNews();
        news.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()) {
                    newsholder.setText("error code -> " + response.code());
                    return;
                }
                List<News> news1 = response.body();
                for (News news2 : news1) {
                    String news = "";
                    news += "ID: " + news2.getId();
                    news += "\nTitle: " + news2.getTitle();
                    news += "\nSummary: " + news2.getSummary();
                    news += "\nImageURL: " + news2.getImageUrl();
                    news += "\nsourceURL: " + news2.getSourceUrl();
                    news += "\nPoints: " + news2.getPoints()+"\n";
                    newsholder.append(news);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                newsholder.setText(t.getMessage());
            }
        });
    }

    public void openGameSummary(View view) {
        Intent intent = new Intent(getApplicationContext(), GameSummaryActivity.class);
        startActivity(intent);
    }

    public void loadData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("Database", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
// Failed to read value
                Log.w("Database", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
