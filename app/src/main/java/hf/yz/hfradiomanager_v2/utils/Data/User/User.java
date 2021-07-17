package hf.yz.hfradiomanager_v2.utils.Data.User;

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
public final class User extends DataItem {

    @PrimaryKey
    @ColumnInfo(name = "userid")
    @NonNull
    private String mId;

    @ColumnInfo(name = "usertype")
    @NonNull
    private int userType;

    @NonNull
    @ColumnInfo(name = "username")
    private String mUserName;

    @Ignore
    public User(@NonNull int userType,@NonNull String userName) {
        mId = UUID.randomUUID().toString();
        this.userType = userType;
        mUserName = userName;
    }

    public User(@NonNull String mId, int userType, @NonNull String mUserName) {
        this.mId = mId;
        this.userType = userType;
        this.mUserName = mUserName;
    }

    @NonNull
    public String getMId() {
        return mId;
    }

    public int getUserType() {
        return userType;
    }

    @NonNull
    public String getMUserName() {
        return mUserName;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(mId, user.mId) &&
                Objects.equals(mUserName, user.mUserName);
    }

    @NonNull
    @Override
    public String toString() {
        return "UserName: " + mUserName + ", ID: "+ mId;
    }
}
