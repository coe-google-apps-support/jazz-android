package ca.edmonton.chat.vipchat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.livechatinc.inappchat.ChatWindowActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        Log.i("Hola", "Logging in...");
        Context context = view.getContext();
        Intent intent = new Intent(context, com.livechatinc.inappchat.ChatWindowActivity.class);
        intent.putExtra(ChatWindowActivity.KEY_GROUP_ID, "0");
        intent.putExtra(ChatWindowActivity.KEY_LICENCE_NUMBER, "9242305");
        intent.putExtra(ChatWindowActivity.KEY_VISITOR_NAME, "Android Native");
        intent.putExtra(ChatWindowActivity.KEY_VISITOR_EMAIL, "droid.native@email.com");
        context.startActivity(intent);
    }
}
