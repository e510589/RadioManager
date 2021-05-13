package hf.yz.hfradiomanager_v2.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.utils.ActivityUtils;
import hf.yz.hfradiomanager_v2.utils.db.DBHelper;

public class ChatActivity extends AppCompatActivity {

    private ChatPresenter mChatPresenter;

    public static final String CURRENT_TARGET_USER_ID = "CALLER_ID";

    private ActionBar mActionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        String ID = getIntent().getStringExtra(CURRENT_TARGET_USER_ID);
        mActionBar.setTitle(ID);


        ChatFragment chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.frm_msg);

        if(chatFragment == null) {
            // Create the fragment
            chatFragment = ChatFragment.getInstance();
            chatFragment.setUserID(ID);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),chatFragment,R.id.frm_msg);
        }

        mChatPresenter = new ChatPresenter(chatFragment, new DBHelper(this));
    }
}
