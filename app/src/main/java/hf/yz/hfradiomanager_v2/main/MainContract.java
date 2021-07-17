package hf.yz.hfradiomanager_v2.main;

import android.content.Context;

import java.util.List;

import hf.yz.hfradiomanager_v2.BasePresenter;
import hf.yz.hfradiomanager_v2.BaseView;

public interface MainContract {

    public interface View extends  BaseView<Presenter>{

        void setOperationModeTextView(String mode);

        void setChannelIndexTextView(String index);

        void setOperationFrequencyTextView(String frequency);

        void setChannelNameTextView(String name);

        void setModulationModeTextView(String modulationMode);

        void setOutputPowerTextView(String pwr);

        void setChannelInfoVisibility(boolean visibility);

        void showMessage(String message);

        void showUserList(int serviceType,String[] users);

        void showDialog(String msg);

        boolean isActive();

    }

    public interface Presenter extends BasePresenter{

        void chnDown();

        void chnUp();

        void getFriendListFromRepo(int serviceType);

        void closeRepo();

        void test();

    }
}
