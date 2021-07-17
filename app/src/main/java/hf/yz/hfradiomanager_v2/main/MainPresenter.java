package hf.yz.hfradiomanager_v2.main;

import androidx.annotation.NonNull;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

import hf.yz.hfradiomanager_v2.utils.Data.DataItem;
import hf.yz.hfradiomanager_v2.utils.Data.User.User;
import hf.yz.hfradiomanager_v2.utils.Data.User.UserLocalDataSource;

public class MainPresenter implements MainContract.Presenter {

    private UserLocalDataSource userLocalDataSource;
    private final MainContract.View mMainView;


    public MainPresenter(@NonNull MainContract.View mMainView, UserLocalDataSource userLocalDataSource) {

        this.mMainView = mMainView;
        mMainView.setPresenter(this);

        this.userLocalDataSource = userLocalDataSource;
    }

    @Override
    public void start() {

    }

    @Override
    public void getFriendListFromRepo(final int serviceType) {


        userLocalDataSource.getAllData(new UserLocalDataSource.OnDataLoadedCallBack() {
            @Override
            public void OnDataLoaded(List<User> users) {
                String[] nameList = new String[users.size()];
                ArrayList<User> userList = new ArrayList<>();
                for (User user:users){
                    userList.add(user);
                }

                for(int i = 0 ;i<nameList.length;i++){
                    nameList[i] = userList.get(i).getMUserName();
                }

                mMainView.showUserList(serviceType,nameList);
            }

            @Override
            public void OnDataNotAvailable() {
                mMainView.showMessage("It seems you have no friends.");
            }
        });
    }

    @Override
    public void closeRepo() {
    }

    @Override
    public void chnDown() {

    }

    @Override
    public void chnUp() {

    }

    public void test(){
        userLocalDataSource.saveData(new User(UserLocalDataSource.USER_TYPE_INDI_USER,"Ruting"));
        userLocalDataSource.saveData(new User(UserLocalDataSource.USER_TYPE_INDI_USER,"Alice"));
    }
}
