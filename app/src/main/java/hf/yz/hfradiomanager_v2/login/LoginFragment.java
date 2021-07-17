package hf.yz.hfradiomanager_v2.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.main.MainAvtivity;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private TextInputLayout til_username, til_password;

    private TextInputEditText tie_username, tie_password;

    private Button btn_login;

    private TextView tv_login;

    private ImageView imv;

    public LoginFragment(){

    }



    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Kevin","fragment onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Kevin","fragment onResume");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("Kevin","fragment onCreateView");

        View rootView = inflater.inflate(R.layout.login2_frag,container,false);

        imv = rootView.findViewById(R.id.img_logo);

        tv_login = rootView.findViewById(R.id.tv_login);

        til_username = rootView.findViewById(R.id.til_username);

        til_password = rootView.findViewById(R.id.til_password);

        tie_username = rootView.findViewById(R.id.tie_username);

        tie_password = rootView.findViewById(R.id.tie_password);

        btn_login = rootView.findViewById(R.id.btn_login);

        imageFadeIn(imv);

        return rootView;
    }

    @Override
    public void onUserInfoGet(String username) {
        tv_login.setText("Hello!"+username);
        textViewFadeIn(tv_login);
        tv_login.setVisibility(View.VISIBLE);
        viewFadein(til_password);
        viewFadein(btn_login);
        til_password.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.VISIBLE);
        btn_login.setOnClickListener(onClickListener_preset_user);
    }

    @Override
    public void onUserInfoNotAvaliable() {
        tv_login.setText("New guy?");
        textViewFadeIn(tv_login);
        tv_login.setVisibility(View.VISIBLE);
        til_username.setHint("New Username");
        til_password.setHint("New password");
        viewFadein(til_username);
        viewFadein(til_password);
        viewFadein(btn_login);
        til_username.setVisibility(View.VISIBLE);
        til_password.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.VISIBLE);
        btn_login.setOnClickListener(onClickListener_new_user);
    }

    private View.OnClickListener onClickListener_preset_user = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.login(tie_password.getText().toString());
        }
    };

    private View.OnClickListener onClickListener_new_user = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.newUserLogin(tie_username.getText().toString(),tie_password.getText().toString());
        }
    };

    private void imageFadeIn(ImageView view){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(2000);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Check if locol user info has been set
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPresenter.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(fadeIn);

    }

    private void textViewFadeIn(TextView textView){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(2000);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        textView.startAnimation(fadeIn);

    }

    private void viewFadein(View view){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(2000);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.setAnimation(fadeIn);
    }

    @Override
    public void clearEditText() {

    }

    @Override
    public void hideEditText(boolean state) {

    }

    @Override
    public void setLoginMsg(String msg) {
        tv_login.setText(msg);
    }


    @Override
    public void startMainActivity() {
        Log.d("Kevin", "About to switch Activity");

        Intent intent = new Intent(getContext(), MainAvtivity.class);
        //We can use intent.putExtra to decide what's are going to show in MainUI
        startActivity(intent);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
