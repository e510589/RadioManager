package hf.yz.hfradiomanager_v2.login;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.utils.ActivityUtils;
import hf.yz.hfradiomanager_v2.utils.Injection;

public class LoginActivity extends AppCompatActivity {

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frm_login);
        if(loginFragment == null) {
            // Create the fragment
            loginFragment = LoginFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.frm_login);
        }
        mLoginPresenter = new LoginPresenter(loginFragment, Injection.provideUserLocalDataSource(this));
    }
}
