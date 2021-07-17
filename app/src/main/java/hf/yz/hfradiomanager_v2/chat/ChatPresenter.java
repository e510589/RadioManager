package hf.yz.hfradiomanager_v2.chat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hf.yz.hfradiomanager_v2.main.MLog;
import hf.yz.hfradiomanager_v2.utils.Data.Message.Message;
import hf.yz.hfradiomanager_v2.utils.Data.Message.MsgLocalDataSource;
import hf.yz.hfradiomanager_v2.utils.DateTime;

public class ChatPresenter implements ChatContract.Presenter {

    private final ChatContract.View mChatView;

    private MsgLocalDataSource msgLocalDataSource;

    private String ID;

    private ArrayList<Message> mMsgList;

    public ChatPresenter(@NonNull String ID, ChatContract.View mChatView, MsgLocalDataSource msgLocalDataSource){

        this.ID = ID;
        this.mChatView = mChatView;
        this.msgLocalDataSource = msgLocalDataSource;
        mMsgList = new ArrayList<Message>(0);
        mChatView.setPresenter(this);
    }

    @Override
    public void sendMsg(@NonNull String textInput) {

        Message nMessage = new Message(ChatAdapter.TYPE_SEND, DateTime.getDateTime(),"ME",ID,textInput,false);

        msgLocalDataSource.saveMessage(nMessage);
        mMsgList.add(nMessage);
        mChatView.addNewMessageSend();
        MLog.showDebug(this.toString(),"send message");
    }

    public void loadMessages() {
        msgLocalDataSource.getMessageWith(ID, new MsgLocalDataSource.OnDataLoadedCallBack() {
            @Override
            public void OnDataLoaded(List<Message> msgList){
                MLog.showDebug(this.toString(),"MsgLis Loaded!");

                for (Message msg : msgList){
                    MLog.showDebug(this.toString(),"Msg: " + msg.getContent());
                    mMsgList.add(msg);
                }

                mChatView.showMessages();
            }

            @Override
            public void OnDataNotAvailable() {
                MLog.showDebug(this.toString(),"No Message Loaded.");
                mChatView.showNoMessage();
            }
        });
    }

    @Override
    public void onBindMessageViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int msgType = getItemViewType(position);
        switch (msgType){
            case MsgLocalDataSource.TYPE_text_SEND:
                ((ChatContract.SendViewHolder)viewHolder).setMsg(mMsgList.get(position).getContent());
                ((ChatContract.SendViewHolder)viewHolder).setTime(mMsgList.get(position).getDateTime());
                ((ChatContract.SendViewHolder)viewHolder).setOnClick(position);
                ((ChatContract.SendViewHolder)viewHolder).setRecv(false);
                if(mMsgList.get(position).isRecv())
                    ((ChatContract.SendViewHolder)viewHolder).setRecv(true);

                break;
            case MsgLocalDataSource.TYPE_FILE_RECV:
                ((ChatContract.RecvViewHolder)viewHolder).setMsg(mMsgList.get(position).getContent());
                ((ChatContract.RecvViewHolder)viewHolder).setTime(mMsgList.get(position).getDateTime());
                ((ChatContract.RecvViewHolder)viewHolder).setSourceID(mMsgList.get(position).getSourceId());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mMsgList.get(position).getMsgType();
    }

    @Override
    public int getItemcount() {
        return mMsgList.size();
    }

    @Override
    public void start() {
        loadMessages();
    }

    @Override
    public void test_On_MessageClicked(int position) {

        MLog.showDebug(this.toString(),"Position: "+ position +" Message Id: "+ ID + " was Clicked.");

        mMsgList.get(position).setRecv(true);
        msgLocalDataSource.updateMessage(mMsgList.get(position), new MsgLocalDataSource.OnDataUpdateCallBack() {
            @Override
            public void OnDataUpdate(int index) {
                MLog.showDebug(this.toString(),"Clicked message update success. Index from db: "+ index);
                mChatView.updateNewMessage(position);
            }

            @Override
            public void OnDataUpdateFailed() {
                MLog.showDebug(this.toString(),"Clicked message update failed.");

            }
        });


    }
}
