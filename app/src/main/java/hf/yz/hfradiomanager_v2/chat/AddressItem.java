package hf.yz.hfradiomanager_v2.chat;

public class AddressItem {
    public int index = 0;
    public String addressName = "";
    public String address = "";
    public int addressType = 0;
    public int presetMap = 0;
    public int netWork = 0;
    public int tuneTime = 0;
    public int replyTime = 0;
    public byte[] networkNumber = new byte[20];
    public byte[] slotNumber = new byte[20];

    public AddressItem(){}

    public AddressItem(int index, String addressName, String address, int addressType, int presetMap, int netWork){
        this.index = index;
        this.addressName = addressName;
        this.address = address;
        this.addressType = addressType;
        this.presetMap = presetMap;
        this.netWork = netWork;
    }




}
