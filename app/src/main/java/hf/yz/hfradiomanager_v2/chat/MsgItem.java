package hf.yz.hfradiomanager_v2.chat;

public class MsgItem {

    private int messageType, messageID;

    private String sourceID, destinationID;

    private boolean isRecevied;

    private String content;

    private String time;

    public MsgItem(){}

    public MsgItem(int messageType, int messageID, String sourceID, String destinationID, String content, String time, boolean isRecevied){
        this.messageType = messageType;
        this.messageID = messageID;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.content = content;
        this.time = time;
        this.isRecevied = isRecevied;
    }

    public int getMessageType(){
        return messageType;
    }

    public int getMessageID(){
        return messageID;
    }

    public String getSourceID(){
        return sourceID;
    }

    public String getDestinationID(){
        return destinationID;
    }

    public String getContent(){
        return content;
    }

    public String getTime(){
        return time;
    }

    public boolean getReceived(){
        return isRecevied;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRecevied(boolean isRecevied){
        this.isRecevied = isRecevied;
    }
}