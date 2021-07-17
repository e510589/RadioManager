package hf.yz.hfradiomanager_v2.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hf.yz.hfradiomanager_v2.R;
import hf.yz.hfradiomanager_v2.TestCallBack;
import hf.yz.hfradiomanager_v2.TextAPI;
import hf.yz.hfradiomanager_v2.chat.ChatActivity;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    public static MainFragment getInstance(){
        return  new MainFragment();
    }

    private RelativeLayout rlChannelInfo;

    private TextView tvOpMode, tvChnIndex, tvOpFreq, tvChnName, tvModulationMode, tvOutputPwr, tvFunctionName;

    public static final int SERVICE_TYPE_CHAT = 0;
    public static final int SERVICE_TYPE_PHONE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.main_frag,container,false);

        rlChannelInfo = rootView.findViewById(R.id.rl_main_a_c);

        tvOpMode = rootView.findViewById(R.id.tv_op_mode);
        tvChnIndex = rootView.findViewById(R.id.tv_channel_index);
        tvOpFreq = rootView.findViewById(R.id.tv_freq);
        tvChnName = rootView.findViewById(R.id.tv_channel_name);
        tvModulationMode = rootView.findViewById(R.id.tv_channel_modulation);
        tvOutputPwr = rootView.findViewById(R.id.tv_channel_pwr);
        tvFunctionName = rootView.findViewById(R.id.tv_function_name);


        Button btnChnDown = rootView.findViewById(R.id.btn_channel_down);
        Button btnChnUp = rootView.findViewById(R.id.btn_channel_up);

        btnChnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.chnDown();
            }
        });

        btnChnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.chnUp();
            }
        });

        final ViewPager2 viewPager2 = rootView.findViewById(R.id.vp_image_slider);



        final List<SliderItem> list = new ArrayList<>();
        list.add(new SliderItem("CHAT",R.drawable.imv_chat));
        list.add(new SliderItem("VOICE",R.drawable.imv_phone));
        list.add(new SliderItem("SETTING",R.drawable.imv_setting));

        viewPager2.setAdapter(new SliderAdapter(list, viewPager2, new OnSliderViewClicked() {
            @Override
            public void onClicked() {
                int imageID = list.get(viewPager2.getCurrentItem()).getImage();

                switch (imageID){
                    case R.drawable.imv_chat:
                        mPresenter.getFriendListFromRepo(SERVICE_TYPE_CHAT);
                        break;
                    case R.drawable.imv_phone:
                        mPresenter.getFriendListFromRepo(SERVICE_TYPE_PHONE);
                        break;
                    case R.drawable.imv_setting:

                        new AlertDialog.Builder(Objects.requireNonNull(MainFragment.this.getContext())).setMessage("setting").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.test();
                                dialog.dismiss();
                            }
                        }).show();

                        break;
                }

            }
        }));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tvFunctionName.setText(list.get(position).getName());
            }
        });


        TextAPI api = new TextAPI(16,12);
        api.registerCallBack(new TestCallBack() {
            @Override
            public void onFinished() {
                tvChnIndex.setText("Keep Grooving");
            }

            @Override
            public void onFailed() {
                tvChnIndex.setText("Keep Shit");
            }
        });

        api.getResult();

        return rootView;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

        if(presenter != null){
            mPresenter = presenter;
        }

    }

    @Override
    public void setOperationModeTextView(String mode) {
        if(!mode.equals(""))
            tvOpMode.setText(mode);
    }

    @Override
    public void setChannelIndexTextView(String index) {
        if(!index.equals(""))
            tvChnIndex.setText(index);
    }

    @Override
    public void setOperationFrequencyTextView(String freq) {
        if(!freq.equals(""))
            tvOpFreq.setText(freq);

    }

    @Override
    public void setChannelNameTextView(String name) {
        if(!name.equals(""))
            tvChnName.setText(name);
    }

    @Override
    public void setModulationModeTextView(String modulationMode) {
        if(!modulationMode.equals(""))
            tvModulationMode.setText(modulationMode);
    }

    @Override
    public void setOutputPowerTextView(String pwr) {
        if(!pwr.equals(""))
            tvOutputPwr.setText(pwr);
    }

    @Override
    public void setChannelInfoVisibility(boolean visibility) {
        if (visibility)
            rlChannelInfo.setVisibility(View.VISIBLE);
        else
            rlChannelInfo.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void showUserList(final int serviceType, final String[] users) {
        new AlertDialog.Builder(Objects.requireNonNull(MainFragment.this.getContext())).setSingleChoiceItems(users, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(serviceType){
                    case SERVICE_TYPE_CHAT:

                        Intent intent = new Intent(getContext(), ChatActivity.class);
                        intent.putExtra(ChatActivity.CURRENT_TARGET_USER_ID, users[which]);
                        startActivity(intent);
                        dialog.dismiss();

                        break;
                    case SERVICE_TYPE_PHONE:
                        break;
                }
            }
        }).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    @Override
    public void showDialog(String msg) {
        new AlertDialog.Builder(Objects.requireNonNull(MainFragment.this.getContext())).setMessage(msg).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }


}
