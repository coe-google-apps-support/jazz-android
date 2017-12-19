package ca.edmonton.jazz;

import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.livechatinc.inappchat.ChatWindowActivity;
import com.livechatinc.inappchat.ChatWindowConfiguration;
import com.livechatinc.inappchat.ChatWindowView;
import com.livechatinc.inappchat.models.NewMessageModel;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ChatWindowView.ChatWindowEventsListener {

    private GoogleSignInClient mGoogleSignInClient;
    private ChatWindowView fullScreenChatWindow;
    private int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            ChatWindowConfiguration config = new ChatWindowConfiguration(
                    Config.license,
                    Config.group,
                    account.getDisplayName(),
                    account.getEmail(),
                    new HashMap<String, String>()
            );

            launchChat(this, config);
        }
        else {
            signIn();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (fullScreenChatWindow != null) fullScreenChatWindow.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else {
            Log.w("Chat", "Received unknown code " + requestCode);
        }
    }

    @Override
    public void onChatWindowVisibilityChanged(boolean b) {
        Log.w("Chat", "Chat window visibility changed.");
    }

    @Override
    public void onNewMessage(NewMessageModel newMessageModel, boolean b) {
        Log.w("Chat", "Chat message received.");
    }

    @Override
    public void onStartFilePickerActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            ChatWindowConfiguration config = new ChatWindowConfiguration(
                    Config.license,
                    Config.group,
                    account.getDisplayName(),
                    account.getEmail(),
                    new HashMap<String, String>()
            );

            // Signed in successfully, show authenticated UI.
            launchChat(this, config);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Whoopsie", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void launchChat(Context context, ChatWindowConfiguration config) {
//        Intent intent = new Intent(context, ChatWindowActivity.class);
//        intent.putExtra(ChatWindowActivity.KEY_GROUP_ID, "1");
//        intent.putExtra(ChatWindowActivity.KEY_LICENCE_NUMBER, "9242305");
//        intent.putExtra(ChatWindowActivity.KEY_VISITOR_NAME, name);
//        intent.putExtra(ChatWindowActivity.KEY_VISITOR_EMAIL, email);
//        context.startActivity(intent);
        if (fullScreenChatWindow == null) {
            fullScreenChatWindow = ChatWindowView.createAndAttachChatWindowInstance(this);
            fullScreenChatWindow.setUpWindow(config);
            fullScreenChatWindow.setUpListener(this);
            fullScreenChatWindow.initialize();
        }
        fullScreenChatWindow.showChatWindow();
    }
}
