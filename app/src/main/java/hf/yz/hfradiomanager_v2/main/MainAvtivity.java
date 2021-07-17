package hf.yz.hfradiomanager_v2.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.TestCallBack;
import hf.yz.hfradiomanager_v2.TextAPI;
import hf.yz.hfradiomanager_v2.utils.ActivityUtils;
import hf.yz.hfradiomanager_v2.utils.Injection;

public class MainAvtivity extends AppCompatActivity {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.frm_main);

        if(mainFragment == null) {
            // Create the fragment
            mainFragment = MainFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mainFragment,R.id.frm_main);
        }

        mMainPresenter = new MainPresenter(mainFragment, Injection.provideUserLocalDataSource(this));



    }
}
