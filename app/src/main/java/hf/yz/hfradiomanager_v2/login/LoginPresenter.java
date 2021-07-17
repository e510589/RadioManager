package hf.yz.hfradiomanager_v2.login;

import android.util.Log;

import androidx.annotation.NonNull;

import hf.yz.hfradiomanager_v2.main.MLog;
import hf.yz.hfradiomanager_v2.utils.Data.User.User;
import hf.yz.hfradiomanager_v2.utils.Data.User.UserLocalDataSource;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    private UserLocalDataSource userLocalDataSource;

    public LoginPresenter(@NonNull LoginContract.View mLoginView,@NonNull UserLocalDataSource userLocalDataSource) {

        this.mLoginView = mLoginView;
        this.userLocalDataSource =userLocalDataSource;
        mLoginView.setPresenter(this);
    }

    @Override
    public void login(String password) {
        Log.d("Kevin", "SendPassword");
        mLoginView.startMainActivity();
    }

    @Override
    public void newUserLogin(String username, String password) {

        MLog.showDebug(this.toString(),"User: "+ username +" Password: " + password);

        if (username.equals("")){
            mLoginView.setLoginMsg("Your name please...");
        }else if (username.length() <3){
            mLoginView.setLoginMsg("Your name is unacceptable");
        }else if (password.equals("")){
            mLoginView.setLoginMsg("Where is your password?");//Where is your password?
        }else if (password.length() < 10){
            mLoginView.setLoginMsg("Weak password");//Password weak as your dick
        }else {
            //Write to database
            mLoginView.startMainActivity();
        }
    }

    private void getSelfUserInfo(){
        userLocalDataSource.getSelfUserInfo(new UserLocalDataSource.OnUserLoadedCallBack() {
            @Override
            public void OnUserLoaded(User user) {
                mLoginView.onUserInfoGet(user.getMUserName());
            }

            @Override
            public void OnUserNotLoaded() {
                mLoginView.onUserInfoNotAvaliable();
            }
        });
    }

    @Override
    public void start() {
        getSelfUserInfo();
    }
}
