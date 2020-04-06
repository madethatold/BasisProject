package com.example.basisproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.basisproject.R;

public class AFragment extends Fragment {
    private TextView mTvTitle;
    //    private Activity mActivity;
    private Button mbtnchange,mbtnreset,mbtnMessage;
    private BFragment bFragment;
    private IOnMessageClick listener;

    //构造方法 传参给Fragment
    public static AFragment newInstance (String title){
        AFragment fragment=new AFragment();//实例化
        Bundle bundle=new Bundle();
        bundle.putString("title",title);//key_参数(此处为形参)
        fragment.setArguments(bundle);//放bundle进fragment
        return fragment;
    }


    public interface IOnMessageClick{
        void onClick(String text);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_a,container,false);//相当于Activity.java中setContentView的作用 即设置布局文件

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTvTitle=view.findViewById(R.id.tv_title);
        mbtnchange=view.findViewById(R.id.btn_change);
        mbtnreset=view.findViewById(R.id.btn_reset);
        mbtnMessage=view.findViewById(R.id.btn_message);

        //用newInstance传参数
        if(getArguments()!=null){
            mTvTitle.setText(getArguments().getString("title"));//接收来自Activity的数据
        }

        //替换fragment
        mbtnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bFragment==null){
                    bFragment=new BFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
            }
        });

        //设置text
        mbtnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTitle.setText("new 2019_ncov");
            }
        });

        //通过fragment设置activity中的文字
        mbtnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContainerActivity)getActivity()).setData("fine");
//                listener.onClick("fine");
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener= (IOnMessageClick) context;
        }catch (ClassCastException e){
            throw  new ClassCastException("Activity must implements IOnMessageClick");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消异步
    }
}
