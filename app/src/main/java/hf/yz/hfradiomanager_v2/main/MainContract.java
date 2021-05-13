package hf.yz.hfradiomanager_v2.main;

import android.content.Context;

import hf.yz.hfradiomanager_v2.BasePresenter;
import hf.yz.hfradiomanager_v2.BaseView;
import hf.yz.hfradiomanager_v2.utils.db.DBHelper;

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

        boolean isActive();

    }

    public interface Presenter extends BasePresenter{

        void chnDown();

        void chnUp();

        String[] getFriendListFromRepo();

        void closeRepo();

    }
}
