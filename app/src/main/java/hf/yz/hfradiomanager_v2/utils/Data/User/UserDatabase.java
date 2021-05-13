package hf.yz.hfradiomanager_v2.utils.Data.User;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Objects;


@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private  static UserDatabase INSTANCE;

    public abstract UserDao userDao();

    private static final Object sLock = new Object();

    public static UserDatabase getInstance(Context context){
        synchronized (sLock){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class,"User.db").build();
            }
            return INSTANCE;
        }
    }


}