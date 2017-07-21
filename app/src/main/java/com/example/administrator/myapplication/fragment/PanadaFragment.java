package com.example.administrator.myapplication.fragment;

import android.view.View;
import android.widget.ListView;


import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.PanadaFragmentAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.common.Urls;
import com.example.administrator.myapplication.model.callback.NetWorkCallBack;
import com.example.administrator.myapplication.model.entity.PanadaBean;
import com.example.administrator.myapplication.model.http.PanadaIhttpImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/10.
 */

public class PanadaFragment extends BaseFragment {
    @BindView(R.id.mPanada_listView)
    ListView mPanadaListView;
    private PanadaIhttpImpl panadaIhttp;
    private List<PanadaBean.ListBean> list;
    private PanadaFragmentAdapter adapter;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panada;
    }

    @Override
    protected void init(View view) {
        list = new ArrayList<>();
        panadaIhttp = new PanadaIhttpImpl();
        adapter = new PanadaFragmentAdapter(getContext(), list);
        mPanadaListView.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        panadaIhttp.showImage(Urls.PANADA, null, new NetWorkCallBack<PanadaBean>() {
            @Override
            public void onSuccess(PanadaBean Data) {
                list.addAll(Data.getList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMsg, String ErrorCode) {

            }
        });
    }
}
