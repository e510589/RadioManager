package hf.yz.hfradiomanager_v2.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.main.MLog;
import hf.yz.hfradiomanager_v2.utils.Data.Message.Message;

public class ChatFragment extends Fragment implements ChatContract.View {

    private ChatContract.Presenter mPresenter;

    private TextView tvNoMessage;

    private RecyclerView rcyvChat;

    private ChatAdapter chatAdapter;

    private EditText edtChat;

    private Button btnSend;

    private String userID;

    public static ChatFragment getInstance(){
        return new ChatFragment();
    }

    public void setUserID(String ID){
        userID = ID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatAdapter = new ChatAdapter();
        chatAdapter.setmPresenter(mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.chat_frag,container,false);

        tvNoMessage = rootView.findViewById(R.id.tv_noMessage);

        rcyvChat = rootView.findViewById(R.id.rcyv_msg);
        rcyvChat.setVisibility(View.INVISIBLE);
        rcyvChat.setAdapter(chatAdapter);
        rcyvChat.setLayoutManager(new LinearLayoutManager(this.getContext()));
        edtChat = rootView.findViewById(R.id.edt_msginput);
        btnSend = rootView.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(onClickListener);



        return rootView;

    }

    @Override
    public void onResume() {
        mPresenter.start();
        super.onResume();
        MLog.showDebug(this.toString(),"on Resume. Ask for msgList");

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String textInput = edtChat.getText().toString();
            if(!textInput.equals("")){
                mPresenter.sendMsg(textInput);
                edtChat.setText("");
            }
        }
    };

    @Override
    public void showNoMessage() {
        rcyvChat.setVisibility(View.INVISIBLE);
        tvNoMessage.setVisibility(View.VISIBLE);
        MLog.showDebug(this.toString(),"No message to show. Clear chat UI.");
    }

    @Override
    public void showMessages() {
        rcyvChat.setVisibility(View.VISIBLE);
        tvNoMessage.setVisibility(View.INVISIBLE);
        MLog.showDebug(this.toString(),"Msglist on UI is about to been updated.");

        chatAdapter.updateData();
    }

    @Override
    public void addNewMessageSend() {
        tvNoMessage.setVisibility(View.INVISIBLE);
        rcyvChat.setVisibility(View.VISIBLE);
        chatAdapter.addNewDate();
    }

    @Override
    public void updateNewMessage(int index) {
        chatAdapter.upateDate(index);
    }

    @Override
    public void messageRecv() {

    }

    @Override
    public void setSiteName(String name) {

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
    public void setPresenter(ChatContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
            MLog.showDebug(this.toString(),"Set presenter to View and adapter");
        }
    }
}
