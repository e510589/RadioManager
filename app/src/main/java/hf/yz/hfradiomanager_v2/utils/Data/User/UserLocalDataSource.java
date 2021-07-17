package hf.yz.hfradiomanager_v2.utils.Data.User;

import androidx.annotation.NonNull;

import java.util.List;

import hf.yz.hfradiomanager_v2.utils.AppExecutors;
import hf.yz.hfradiomanager_v2.utils.Data.DataItem;

public class UserLocalDataSource{


    public static final int USER_TYPE_SELF = 0;
    public static final int USER_TYPE_INDI_USER = 1;
    public static final int USER_TYPE_GROUP = 2;

    public interface OnUserLoadedCallBack{

        void OnUserLoaded(User user);

        void OnUserNotLoaded();

    }

    public interface OnDataLoadedCallBack {

        void OnDataLoaded(List<User> users);

        void OnDataNotAvailable();

    }

    public interface OnDataGetCallBack {

        void OnDataGet(DataItem item);

        void OnDataNotAvailable();

    }

    private static volatile UserLocalDataSource INSTANCE;

    private UserDao mUserDao;

    private AppExecutors mAppExecutors;


    //Prevent direct instantiation
    private UserLocalDataSource(@NonNull AppExecutors appExecutors,@NonNull UserDao userDao){
        mAppExecutors = appExecutors;
        mUserDao = userDao;
    }

    public static UserLocalDataSource getInstance(@NonNull AppExecutors appExecutors,@NonNull UserDao userDao){
        if(INSTANCE == null){
            synchronized (UserLocalDataSource.class) {
                INSTANCE = new UserLocalDataSource(appExecutors,userDao);
            }
        }
        return INSTANCE;
    }

    public void getSelfUserInfo(final OnUserLoadedCallBack callBack){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final User user = mUserDao.getSelfUser();
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (user == null){
                            callBack.OnUserNotLoaded();
                        }else {
                            callBack.OnUserLoaded(user);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }


    public void getAllData(final OnDataLoadedCallBack callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<User> users = mUserDao.getIndiUsers();
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(users.isEmpty()){
                            callBack.OnDataNotAvailable();
                        } else {
                            callBack.OnDataLoaded(users);
                        }
                    }
                });
            }
        };

        mAppExecutors.getDiskIO().execute(runnable);

    }


    public void getData(final String dataID, int index,final OnDataGetCallBack callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final User user = mUserDao.findUserByID(dataID);

                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(user!= null){
                            callBack.OnDataGet(user);
                        } else {
                            callBack.OnDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }


    public void deleteAllData() {

    }


    public void deleteData(final User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.deleteUser((User)user);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }


    public void saveData(final User user) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUser((User)user);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }


    public void updateData(final User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.updateUser((User)user);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }
}
