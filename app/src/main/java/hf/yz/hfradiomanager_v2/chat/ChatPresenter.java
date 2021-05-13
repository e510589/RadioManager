package hf.yz.hfradiomanager_v2.chat;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.utils.db.DBHelper;

public class ChatPresenter implements ChatContract.Presenter {

    private final ChatContract.View mChatView;

    private DBHelper mDBHelper;


    public ChatPresenter(ChatContract.View mChatView, DBHelper mDBHelper){
        this.mChatView = mChatView;
        this.mDBHelper = mDBHelper;
        mDBHelper.openDatabase();
        mChatView.setPresenter(this);

    }


    @Override
    public boolean sendMsg(MsgItem item) {
        return false;
    }

    @Override
    public void loadMsgRecord() {

    }

    @Override
    public ArrayList<MsgItem> getMsgList(String ID) {
        return mDBHelper.getMsgList(ID);
    }

    @Override
    public void start() {

    }
}
