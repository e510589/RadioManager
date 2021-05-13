package hf.yz.hfradiomanager_v2.utils.Data.User;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import hf.yz.hfradiomanager_v2.utils.Data.DataItem;

@Entity(tableName = "Users")
public final class User implements DataItem {

    @PrimaryKey
    @ColumnInfo(name = "userid")
    @NonNull
    private String mId;

    @NonNull
    @ColumnInfo(name = "username")
    private String mUserName;

    @NonNull
    @ColumnInfo(name = "last_update")
    private Date mDate;

    @Ignore
    public User(String userName) {
        mId = UUID.randomUUID().toString();
        mUserName = userName;
        mDate = new Date(System.currentTimeMillis());
    }

    public User(@NonNull String mId, @NonNull String mUserName, @NonNull Date mDate) {
        this.mId = mId;
        this.mUserName = mUserName;
        this.mDate = mDate;
    }

    @NonNull
    public String getmId() {
        return mId;
    }

    @NonNull
    public String getmUserName() {
        return mUserName;
    }

    @NonNull
    public Date getmDate() {
        return mDate;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(mId, user.mId) &&
                Objects.equals(mUserName, user.mUserName) &&
                Objects.equals(mDate, user.mDate);
    }

    @NonNull
    @Override
    public String toString() {
        return "UserName: " + mUserName + ", ID: "+ mId + ", Last Update: " + mDate.toString();
    }
}
