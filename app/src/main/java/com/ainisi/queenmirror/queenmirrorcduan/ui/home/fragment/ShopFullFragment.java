package com.ainisi.queenmirror.queenmirrorcduan.ui.home.fragment;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ainisi.queenmirror.common.base.BaseFragment;
import com.ainisi.queenmirror.queenmirrorcduan.R;
import com.ainisi.queenmirror.queenmirrorcduan.adapter.MyAdapter;
import com.ainisi.queenmirror.queenmirrorcduan.adapter.ProblemAdapter;
import com.ainisi.queenmirror.queenmirrorcduan.bean.ProblemBean;
import com.ainisi.queenmirror.queenmirrorcduan.bean.SortBean;
import com.ainisi.queenmirror.queenmirrorcduan.ui.home.activity.FullActivity;
import com.ainisi.queenmirror.queenmirrorcduan.utils.BaseRecyclerAdapter;
import com.ainisi.queenmirror.queenmirrorcduan.utils.CustomPopWindow;
import com.ainisi.queenmirror.queenmirrorcduan.utils.NoScrollViewPager;
import com.ainisi.queenmirror.queenmirrorcduan.utils.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFullFragment extends BaseFragment {
    @Bind(R.id.full_rb_sort)
    TextView hSort;
    @Bind(R.id.re_recommendable_projects)
    RecyclerView reProjects;
    @Bind(R.id.full_rb_sales)
    TextView hSales;
    @Bind(R.id.full_rb_distance)
    TextView hDistance;
    @Bind(R.id.full_rb_screen)
    TextView hscreen;
    @Bind(R.id.iv_sort)
    ImageView ivsort;
    @Bind(R.id.iv_sort1)
    ImageView ivsort1;
    @Bind(R.id.pager_home_full)
    NoScrollViewPager fullpager;

    //综合排序
    FullshortFragment sortFragment = new FullshortFragment();
    //销量最高
    FullsalesFragment salesFragment = new FullsalesFragment();
    //距离最近
    FulldistanFragment distanceFragment = new FulldistanFragment();
    //筛选
    FullscreenFragment screenFragment = new FullscreenFragment();
    private CustomPopWindow popWindow;
    private View popview1;
    private PopupWindow pop;
    private List<Fragment> pagerList = new ArrayList<>();
    List<SortBean> sortlist=new ArrayList<>();
    private List<ProblemBean> list = new ArrayList<>();
    String[] problem = {"销量最高", "价格最低", "距离最近", "优惠最多", "满减优惠", "新用最好", "用户最好"};

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_shop_full;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initfragment();
        initDate();


    }
    private void initfragment() {
        pagerList.add(sortFragment);
        pagerList.add(salesFragment);
        pagerList.add(distanceFragment);
        pagerList.add(screenFragment);
        ViewPager pager = new ViewPager(getFragmentManager(), pagerList, null);
        fullpager.setScanScroll(true);
        fullpager.setAdapter(pager);
    }

    private void initDate() {

        for (int i = 0; i <10 ; i++) {
            SortBean sortBean=new SortBean();
            sortlist.add(sortBean);
        }
        MyAdapter sortAdapter=new MyAdapter(getActivity(),sortlist,R.layout.re_shop_all);
        reProjects.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        reProjects.setAdapter(sortAdapter);
        sortAdapter.setOnItemClickListner(new BaseRecyclerAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), FullActivity.class));
                        break;
                }
            }
        });
        pop = new PopupWindow(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
        popview1 = View.inflate(getActivity(), R.layout.pop_myitem, null);
        initpop(popview1);
        pop.setContentView(popview1);
        pop.setBackgroundDrawable(new ColorDrawable(0));
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.CustomPopWindowStyle);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ivsort.setVisibility(View.VISIBLE);
                ivsort1.setVisibility(View.GONE);
            }
        });
        pop.dismiss();


    }

    private void initpop(View popview1) {
        final RecyclerView ce = popview1.findViewById(R.id.rc_popview);
        for (int i = 0; i < problem.length; i++) {
            ProblemBean problemBean = new ProblemBean();
            problemBean.setName(problem[i]);
            list.add(problemBean);
        }
        ProblemAdapter problemAdapter = new ProblemAdapter(getActivity(), list, R.layout.item_pop_sort);
        ce.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ce.setAdapter(problemAdapter);
        problemAdapter.setOnItemClickListner(new BaseRecyclerAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                hSort.setText(problem[position]);
                pop.dismiss();
            }
        });
    }


    @OnClick({R.id.full_rb_sort, R.id.full_rb_sales, R.id.full_rb_distance, R.id.full_rb_screen, R.id.iv_sort, R.id.iv_sort1})
    public void click(View view) {
        switch (view.getId()) {
//            //订单详情
//            case R.id.full_guijiao:
//                startActivity(new Intent(getActivity(), FullActivity.class));
//                break;
            //综合选择
            case R.id.iv_sort:
                ivsort.setVisibility(View.GONE);
                ivsort1.setVisibility(View.VISIBLE);
                pop.showAsDropDown(hSort);
                break;
            case R.id.iv_sort1:
                pop.dismiss();
                ivsort.setVisibility(View.VISIBLE);
                ivsort1.setVisibility(View.GONE);
                break;
            //综合排序
            case R.id.full_rb_sort:
                fullpager.setCurrentItem(0);
                break;
            //销量最高
            case R.id.full_rb_sales:
                fullpager.setCurrentItem(1);
                break;
            //距离最近
            case R.id.full_rb_distance:
                fullpager.setCurrentItem(2);
                break;
            //筛选
            case R.id.full_rb_screen:
                View popview = View.inflate(getActivity(), R.layout.pop_right, null);

                initview(popview);
                popWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                        .setView(popview)
                        .setFocusable(true)
                        .size(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.MATCH_PARENT)
                        .setOutsideTouchable(true)
                        .setAnimationStyle(R.style.CustomPopWindowStyle)
                        .create()

                        .showAtLocation(hscreen, Gravity.RIGHT, 0, 0);
                break;
            default:
                break;

        }


    }

    private void initview(final View popview) {
        TextView eliminateTv = popview.findViewById(R.id.tv_eliminate);
        TextView okTv = popview.findViewById(R.id.tv_ok);
        eliminateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dissmiss();

            }
        });
    }

}