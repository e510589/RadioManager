package hf.yz.hfradiomanager_v2.chat;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.BasePresenter;
import hf.yz.hfradiomanager_v2.BaseView;
import hf.yz.hfradiomanager_v2.utils.db.DBHelper;

public interface ChatContract {

    interface View extends BaseView<Presenter> {

        void scrollToEnd();

        void newMessageSend(MsgItem item);

        void messageReceivedAcked(int messageID);

        void inComingMessage(MsgItem item);

        void setSiteName(String name);

        void clearEditText();

        void setSendButtonDisable(boolean state);

        void returnMainPage();

        void returnLoginPage();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        boolean sendMsg(MsgItem item);

        void loadMsgRecord();

        ArrayList<MsgItem> getMsgList(String ID);

    }
}
