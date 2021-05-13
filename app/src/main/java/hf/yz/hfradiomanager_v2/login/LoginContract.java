package hf.yz.hfradiomanager_v2.login;

import hf.yz.hfradiomanager_v2.BasePresenter;
import hf.yz.hfradiomanager_v2.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter>{

        void clearEditText();

        void hideEditText(boolean state);

        void setLoginMsg(String msg);

        void setProcedureMsg(String Msg);

        void hidProcedureMsg(boolean state);

        void setProgress(int progress);

        void hidProgressBar(boolean state);

        void hideButton(boolean state);

        void showMainPageUI();

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void checkIO();

        void runBootProcedure();

        void sendPassword(String password);




    }
}
