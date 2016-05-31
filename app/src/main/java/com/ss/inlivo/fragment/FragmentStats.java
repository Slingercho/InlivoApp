package com.ss.inlivo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.mikephil.charting.charts.BarChart;
import com.ss.inlivo.R;
import com.ss.inlivo.activity.MainActivity;
import com.ss.inlivo.fragment.controller.ChartController;
import com.ss.inlivo.listener.OnTaskCompletedListener;

/**
 * Created by NAME on 25.5.2016 г..
 */
public class FragmentStats extends Fragment implements OnTaskCompletedListener{

    public static final String TAG = FragmentStats.class.getSimpleName();

    private BarChart mBarChart;
    private DonutProgress mDonutProgress;

    public static FragmentStats newInstance() {

        FragmentStats fragmentStats = new FragmentStats();

        return fragmentStats;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBarChart = (BarChart) getView().findViewById(R.id.statsVitaminChart);

        ((MainActivity) getActivity()).getUserVitaminsTask(MainActivity.HEALTH_MODE, "1", this);

    }

    @Override
    public void onTaskCompleted() {

        mBarChart.setData(ChartController.getInstance((MainActivity) getActivity()).generateChart(((MainActivity) getActivity()).getVitamins()));

        mBarChart.animateY(2000);

        mDonutProgress = (DonutProgress) getView().findViewById(R.id.donut_progress);

        mDonutProgress.setProgress(Integer.valueOf(((MainActivity) getActivity()).getHealthIndex().getIndex()));

        mDonutProgress.setFinishedStrokeColor(ContextCompat.getColor(getActivity(), R.color.chart_color));
        mDonutProgress.setTextColor(ContextCompat.getColor(getActivity(), R.color.chart_color));
    }
}
