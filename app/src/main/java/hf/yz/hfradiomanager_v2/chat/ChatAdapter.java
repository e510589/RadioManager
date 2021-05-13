package hf.yz.hfradiomanager_v2.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.R;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MsgItem> msgList;

    private static final int TYPE_SEND = 0;
    private static final int TYPE_RECV = 1;

    public ChatAdapter(ArrayList<MsgItem> msgList) {
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder vh = null;

        switch (viewType){
            case TYPE_SEND:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_send,parent,false);
                vh =  new MsgSendViewHolder(view);
                break;

            case TYPE_RECV:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_recv,parent,false);
                vh =  new MsgReceivedViewHolder(view);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = msgList.get(position).getMessageType();
        if (type == TYPE_SEND){
            ((MsgSendViewHolder)holder).setMsg(msgList.get(position).getContent());
            ((MsgSendViewHolder)holder).setTime(msgList.get(position).getTime());
            if(msgList.get(position).getReceived()) ((MsgSendViewHolder)holder).setRecv();
        }else if (type == TYPE_RECV){

            ((MsgReceivedViewHolder)holder).setMsg(msgList.get(position).getContent());
            ((MsgReceivedViewHolder)holder).setTime(msgList.get(position).getTime());
            ((MsgReceivedViewHolder)holder).setTvSourceID(msgList.get(position).getSourceID());
        }
    }

    @Override
    public int getItemViewType(int position) {

        return msgList.get(position).getMessageType();
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }



    class MsgSendViewHolder extends RecyclerView.ViewHolder{

        private TextView tvMsg, tvTime;

        private ImageView imvAck;

        MsgSendViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMsg = itemView.findViewById(R.id.tv_tx_msg);
            tvTime = itemView.findViewById(R.id.tv_tx_msg_time);
            imvAck = itemView.findViewById(R.id.imv_chat_ack);
            imvAck.setVisibility(View.INVISIBLE);
        }

        void setMsg(String content){
            tvMsg.setText(content);
        }

        void setTime(String time){
            tvTime.setText(time);
        }

        void setRecv(){
            imvAck.setVisibility(View.VISIBLE);
        }
    }

    class MsgReceivedViewHolder extends RecyclerView.ViewHolder{

        private TextView tvMsg, tvTime, tvSourceID;

        MsgReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_rx_msg);
            tvTime = itemView.findViewById(R.id.tv_rx_msg_time);
            tvSourceID = itemView.findViewById(R.id.tv_rx_msg_sorce_id);
        }

        void setTvSourceID(String id){
            tvSourceID.setText(id);
        }

        void setMsg(String content){
            tvMsg.setText(content);
        }

        void setTime(String time){
            tvTime.setText(time);
        }


    }
}
