package com.mosesasiago.hakiki111;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button register, login;
    ImageView imageView;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseUserMetadata metadata;
    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 254;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            finish();
        } else {
            // not signed in
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                                    new AuthUI.IdpConfig.AnonymousBuilder().build()))
                            .setTheme(R.style.FullscreenTheme)
                            .build(),
                    RC_SIGN_IN);
        }
        //setContentView(R.layout.activity_main);
//        imageView = (ImageView) findViewById(R.id.ic_user);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openProfile();
//            }
//        });
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                if (user != null) {
                    launchProfile();
                    Toast.makeText(this, " Login successfully" + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
                metadata = auth.getCurrentUser().getMetadata();
                if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
                    // The user is new, show them a fancy intro screen!
                    launchProfile();
                    Toast.makeText(this, " New User !" + user.getDisplayName(), Toast.LENGTH_SHORT).show();

                } else {
                    launchProfile();
                    // This is an existing user, show them a welcome back screen.
                    Toast.makeText(this, " Welcome back " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    // No network
                    return;
                }
            }
        }
    }
    public void launchProfile(){
        startActivity(new Intent(this, ProfileActivity.class)
                .putExtra("user_name", user.getDisplayName()));
        finish();
    }
}
