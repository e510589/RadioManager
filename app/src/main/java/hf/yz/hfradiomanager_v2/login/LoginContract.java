package hf.yz.hfradiomanager_v2.login;

import hf.yz.hfradiomanager_v2.BasePresenter;
import hf.yz.hfradiomanager_v2.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void onUserInfoGet(String username);

        void onUserInfoNotAvaliable();

        void clearEditText();

        void hideEditText(boolean state);

        void setLoginMsg(String msg);

        void startMainActivity();

        boolean isActive();

    }

    interface Presenter extends BasePresenter{


        void login(String password);

        void newUserLogin(String username,String password);




    }
}
