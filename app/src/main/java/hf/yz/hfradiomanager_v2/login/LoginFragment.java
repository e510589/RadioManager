package hf.yz.hfradiomanager_v2.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.main.MainAvtivity;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private TextView tv_login,tv_procedure;

    private EditText edt_password;

    private ProgressBar pgb_login;

    private Button btn_send;

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

        View rootView = inflater.inflate(R.layout.login_frag,container,false);

        tv_login = rootView.findViewById(R.id.tv_login_msg);

        tv_procedure = rootView.findViewById(R.id.tv_procedure);

        edt_password = rootView.findViewById(R.id.edt_password);

        pgb_login = rootView.findViewById(R.id.pgb_login);

        btn_send = rootView.findViewById(R.id.btn_login);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Kevin", "Button Login Clicked!");
                mPresenter.sendPassword(edt_password.getText().toString());
            }
        });

        return rootView;
    }



    @Override
    public void clearEditText() {
        edt_password.setText("");
    }

    @Override
    public void hideEditText(boolean state) {
        if(state)
            edt_password.setVisibility(View.GONE);
        else
            edt_password.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLoginMsg(String msg) {
        tv_login.setText(msg);
    }

    @Override
    public void setProcedureMsg(String Msg) {
        tv_procedure.setText(Msg);
    }

    @Override
    public void hidProcedureMsg(boolean state) {
        if(state)
            tv_procedure.setVisibility(View.GONE);
        else
            tv_procedure.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProgress(int progress) {
        pgb_login.setProgress(progress);
    }

    @Override
    public void hidProgressBar(boolean state) {
        if(state)
            pgb_login.setVisibility(View.GONE);
        else
            pgb_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton(boolean state) {
        if(state)
            btn_send.setVisibility(View.GONE);
        else
            btn_send.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMainPageUI() {
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
