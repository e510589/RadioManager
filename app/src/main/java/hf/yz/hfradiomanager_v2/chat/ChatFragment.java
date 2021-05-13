package hf.yz.hfradiomanager_v2.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.R;

public class ChatFragment extends Fragment implements ChatContract.View {

    private ChatContract.Presenter mPresenter;

    private RecyclerView rcyvChat;

    private EditText edtChat;

    private Button btnSend;

    private String userID;

    private ArrayList<MsgItem> listItem;

    public static ChatFragment getInstance(){
        return new ChatFragment();
    }

    public void setUserID(String ID){
        userID = ID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.chat_frag,container,false);

        rcyvChat = rootView.findViewById(R.id.rcyv_msg);

        edtChat = rootView.findViewById(R.id.edt_msginput);

        btnSend = rootView.findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listItem = mPresenter.getMsgList(userID);

        rcyvChat.setAdapter(new ChatAdapter(listItem));

        return rootView;

    }



    @Override
    public void scrollToEnd() {

    }

    @Override
    public void newMessageSend(MsgItem item) {

    }

    @Override
    public void messageReceivedAcked(int messageID) {

    }

    @Override
    public void inComingMessage(MsgItem item) {

    }

    @Override
    public void setSiteName(String name) {

    }

    @Override
    public void clearEditText() {

        edtChat.setText("");

    }

    @Override
    public void setSendButtonDisable(boolean state) {
        if(state){
            btnSend.setClickable(false);
        }else {
            btnSend.setClickable(true);
        }
    }

    @Override
    public void returnMainPage() {

    }

    @Override
    public void returnLoginPage() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void setPresenter(ChatContract.Presenter presenter) {

        if(presenter != null){
            mPresenter = presenter;
        }

    }
}
