package hf.yz.hfradiomanager_v2.chat;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.BasePresenter;
import hf.yz.hfradiomanager_v2.BaseView;
import hf.yz.hfradiomanager_v2.utils.Data.Message.Message;

public interface ChatContract {

    interface View extends BaseView<Presenter> {

        void showMessages();

        void showNoMessage();

        void addNewMessageSend();

        void updateNewMessage(int index);

        void messageRecv();

        void setSiteName(String name);

        void setSendButtonDisable(boolean state);


    }

    interface Presenter extends BasePresenter {

        void sendMsg(String textInput);

        void onBindMessageViewHolder(RecyclerView.ViewHolder viewHolder,int position);

        int getItemViewType(int position);

        int getItemcount();

        void test_On_MessageClicked(int position);

    }

    interface SendViewHolder {
        void setMsg(String msg);
        void setTime(String time);
        void setRecv(boolean isRecv);
        void setOnClick(int position);
    }

    interface RecvViewHolder{
        void setMsg(String msg);
        void setTime(String time);
        void setSourceID(final String sourceID);

    }
}
