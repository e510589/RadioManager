package hf.yz.hfradiomanager_v2.main;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import hf.yz.hfradiomanager_v2.chat.AddressItem;
import hf.yz.hfradiomanager_v2.utils.db.DBHelper;

public class MainPresenter implements MainContract.Presenter {

    private DBHelper mDbHelper;


    public MainPresenter(MainContract.View mMainView, DBHelper dBHelper) {

        if(mMainView != null){
            mMainView.setPresenter(this);
        }

        mDbHelper = dBHelper;
        mDbHelper.openDatabase();
    }

    @Override
    public void start() {

    }

    @Override
    public String[] getFriendListFromRepo() {

        String[] friendsList;

        ArrayList<String> list = mDbHelper.getFriendList();

        friendsList = new String[list.size()];

        for ( int i = 0 ; i < list.size() ; i ++)  friendsList[i] = list.get(i);

        return friendsList;
    }

    @Override
    public void closeRepo() {
        mDbHelper.closeDataBase();
    }

    @Override
    public void chnDown() {

    }

    @Override
    public void chnUp() {

    }
}
