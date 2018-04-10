package com.ainisi.queenmirror.queenmirrorcduan.ui.home.home.orderfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ainisi.queenmirror.common.base.BaseFragment;
import com.ainisi.queenmirror.queenmirrorcduan.R;
import com.ainisi.queenmirror.queenmirrorcduan.adapter.MineAdapter;
import com.ainisi.queenmirror.queenmirrorcduan.adapter.MyAdapter;
import com.ainisi.queenmirror.queenmirrorcduan.ui.home.bean.SortBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by EWorld on 2018/3/6.
 * 全部订单
 */

public class WholeFragment extends BaseFragment{
    @Bind(R.id.recycle_whole)
    RecyclerView wholerecycle;
    private List<SortBean> list=new ArrayList<>();
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_shop_mall;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        for (int i = 0; i <10 ; i++) {
            SortBean sortBean=new SortBean();
            list.add(sortBean);
        }
        MineAdapter sbmitWholeAdapter=new MineAdapter(getActivity(),list,R.layout.item_sbmitrecycler);
        wholerecycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        wholerecycle.setAdapter(sbmitWholeAdapter);

    }
}
