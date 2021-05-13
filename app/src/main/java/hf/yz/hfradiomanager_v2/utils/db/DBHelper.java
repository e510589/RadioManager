package hf.yz.hfradiomanager_v2.utils.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.chat.AddressItem;
import hf.yz.hfradiomanager_v2.chat.MsgItem;

public class DBHelper extends SQLiteOpenHelper {



    private static final String _2GALEAddressTable = "2gale_Address_Repo";

    private static final String friendsTable = "Friends_Repo";

    public static final String msgRecordTableHead = "MSG_Repo_";

    private static final String dBName = "RadioRepo.db";

    private static final int dBVersion = 1;

    private SQLiteDatabase database;


    public DBHelper(@Nullable Context context) {
        super(context, dBName, null, dBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int openDatabase() {

        if (database == null || !database.isOpen()) {
            database = getWritableDatabase();
            return 1;
        }
        return 0;
    }

    public void closeDataBase(){
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public ArrayList<AddressItem> getAddressList(){
        if(database == null) return null;
        ArrayList<AddressItem> list = new ArrayList<>();
        Cursor cursor = database.query(_2GALEAddressTable,null,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            list.add(getAddressRecord(cursor));
        }
        cursor.close();
        return list;
    }

    private AddressItem getAddressRecord(@NonNull Cursor cursor){

        AddressItem item = new AddressItem();

        item.index = cursor.getInt(0);
        item.addressName = cursor.getString(1);
        item.address = cursor.getString(2);
        item.addressType = cursor.getInt(3);
        item.tuneTime = cursor.getInt(4);
        item.replyTime = cursor.getInt(5);
        item.presetMap = cursor.getInt(6);
        item.netWork = cursor.getInt(7);
        item.networkNumber = cursor.getBlob(8);
        item.slotNumber = cursor.getBlob(9);

        return item;
    }

    public ArrayList<MsgItem> getMsgList(@NonNull String id){

        if (database == null) return null;

        ArrayList<MsgItem> msgList = new ArrayList<>();

        Cursor cursor = database.query(msgRecordTableHead + id,null,null,null,null,null,null,null);

        while(cursor.moveToNext()){
            msgList.add(getMsgItem(cursor));
        }
        cursor.close();

        return msgList;
    }

    private MsgItem getMsgItem(@NonNull Cursor cursor){

        MsgItem item = new MsgItem();

        item.setMessageType(cursor.getInt(1));
        item.setSourceID(cursor.getString(2));
        item.setDestinationID(cursor.getString(3));
        item.setContent(cursor.getString(4));
        item.setTime(cursor.getString(5));
        item.setRecevied(cursor.getInt(6) == 1 ? true : false);

        return item;
    };

    public ArrayList<String> getFriendList(){

        if(database == null) return null;

        ArrayList<String> friendsList = new ArrayList<>();

        Cursor cursor = database.query(friendsTable,null,null,null,null,null,null,null);

        while(cursor.moveToNext()){
            friendsList.add(cursor.getString(1));
        }
        cursor.close();

        return friendsList;
    }

}
