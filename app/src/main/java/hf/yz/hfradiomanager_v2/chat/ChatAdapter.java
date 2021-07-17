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
import hf.yz.hfradiomanager_v2.main.MLog;
import hf.yz.hfradiomanager_v2.utils.Data.Message.Message;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ChatContract.Presenter mPresenter;

    public static final int TYPE_SEND = 0;
    public static final int TYPE_RECV = 1;

    public ChatAdapter() {
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
        mPresenter.onBindMessageViewHolder(holder,position);
    }

    @Override
    public int getItemViewType(int position) {
        return mPresenter.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getItemcount();
    }



    class MsgSendViewHolder extends RecyclerView.ViewHolder implements ChatContract.SendViewHolder{

        private TextView tvMsg, tvTime;

        private ImageView imvAck;

        MsgSendViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMsg = itemView.findViewById(R.id.tv_tx_msg);
            tvTime = itemView.findViewById(R.id.tv_tx_msg_time);
            imvAck = itemView.findViewById(R.id.imv_chat_ack);
            imvAck.setVisibility(View.GONE);
        }

        @Override
        public void setMsg(String msg) {
            tvMsg.setText(msg);
        }

        @Override
        public void setTime(String time) {
            tvTime.setText(time);
        }

        @Override
        public void setRecv(boolean isRecv) {
            if (isRecv){
                imvAck.setVisibility(View.VISIBLE);
            }else {
                imvAck.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void setOnClick(final int position) {
            tvMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MLog.showDebug(this.toString(),"Msg on Clicked!");
                    mPresenter.test_On_MessageClicked(position);
                }
            });
        }
    }

    class MsgReceivedViewHolder extends RecyclerView.ViewHolder implements ChatContract.RecvViewHolder{

        private TextView tvMsg, tvTime, tvSourceID;

        MsgReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_rx_msg);
            tvTime = itemView.findViewById(R.id.tv_rx_msg_time);
            tvSourceID = itemView.findViewById(R.id.tv_rx_msg_sorce_id);
        }


        @Override
        public void setMsg(String msg) {
            tvMsg.setText(msg);
        }

        @Override
        public void setTime(String time) {
            tvTime.setText(time);
        }

        @Override
        public void setSourceID(String sourceID) {
            tvSourceID.setText(sourceID);
        }
    }

    public void updateData(){
        this.notifyDataSetChanged();
        MLog.showDebug(this.toString(),"Msglist on UI has been updated.");
    }

    public void upateDate(int index){
        this.notifyItemChanged(index);
    }

    public void addNewDate(){
        this.notifyDataSetChanged();
        MLog.showDebug(this.toString(),"new Message Added");
    }

    public void setmPresenter(ChatContract.Presenter presenter){
        this.mPresenter = presenter;
    }
}
