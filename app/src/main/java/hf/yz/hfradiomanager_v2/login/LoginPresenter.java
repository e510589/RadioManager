package hf.yz.hfradiomanager_v2.login;

import android.util.Log;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View mLoginView) {

        this.mLoginView = mLoginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void checkIO() {

    }

    @Override
    public void runBootProcedure() {

    }

    @Override
    public void sendPassword(String password) {

        Log.d("Kevin", "SendPassword");
        mLoginView.showMainPageUI();

    }

    @Override
    public void start() {

    }
}
