package com.vartmp7.stalker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vartmp7.stalker.component.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "com.vartmp7.stalker.LoginActivitity";
    private static final int RC_SIGN_IN = 1;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    private Button signUpButton;
    private Button signInButton;

    private GoogleSignInClient mGoogleSignInClient;


    //    keytool -exportcert -alias YOUR_RELEASE_KEY_ALIAS -keystore YOUR_RELEASE_KEY_PATH | openssl sha1 -binary | openssl base64
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d(TAG, currentUser.getDisplayName());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpButton = (Button)findViewById(R.id.btn_signUp);
        signUpButton.setOnClickListener(this);

        signInButton = (Button)findViewById(R.id.btn_signIn);
        signInButton.setOnClickListener(this);

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // If using in a fragment
//        loginButton.setFragment(this);
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                AccessToken accessToken = AccessToken.getCurrentAccessToken();

                Log.d(TAG, "onSuccess: " + accessToken.getUserId().toString());
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

//                Successivamente, esegui l'accesso vero e proprio, come nell'OnClickListener di un pulsante personalizzato:
//                LoginManager.getInstance().logInWithReadPermissions(LoginActivitity.this, Collections.singletonList("public_profile"));
                handleFacebookAccessToken(accessToken);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError: " + error.toString());
            }

        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById(R.id.btn_googleSignIn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);
        findViewById(R.id.btnProcediSenzaAuth).setOnClickListener(this);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Log.d(TAG, "handleFacebookAccessToken: " + credential.getProvider());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            if (user != null)
                                Log.d(TAG, "onComplete: " + user.getDisplayName());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

//            Log.d(TAG, "handleSignInResult: " + account.getDisplayName());
            // Signed in successfully, show authenticated UI.

//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    public void showSignUpDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setTitle(R.string.sign_up);
        builder.setMessage("Iscriviti");
        builder.setView(inflater.inflate(R.layout.dialog_login, null));
        builder.setPositiveButton(getString(R.string.conferma), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog d = (Dialog) dialog;
                EditText etUsername = d.findViewById(R.id.etUsername);
                EditText etPassword = d.findViewById(R.id.etPassword);

                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });

        builder.setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private void showSignInDialog()  {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setTitle(R.string.sign_up);
        builder.setMessage("Iscriviti");
        builder.setView(inflater.inflate(R.layout.dialog_login, null));
        builder.setPositiveButton(getString(R.string.conferma), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog d = (Dialog) dialog;
                EditText etUsername = d.findViewById(R.id.etUsername);
                EditText etPassword = d.findViewById(R.id.etPassword);

                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                        task-> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                );


            }
        });

        builder.setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }



    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    private void googleSignOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LoginActivity.this, "Successufully logout", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_googleSignIn:
                googleSignIn();
                break;
            case R.id.btnProcediSenzaAuth:
                Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.btn_signUp:
                Log.e(TAG,"cliccato btn signUp");
                showSignUpDialog();
                break;
            case R.id.btn_signIn:
                showSignInDialog();
                break;
        }
    }




}
