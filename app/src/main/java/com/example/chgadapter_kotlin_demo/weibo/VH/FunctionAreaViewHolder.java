//package com.example.chgadapter_kotlin_demo.weibo.VH;
//
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.chg.adapter.EventTransmissionListener;
//import com.chg.adapter.ViewHolder;
//import com.example.chgadapter_kotlin_demo.R;
//import com.example.chgadapter_kotlin_demo.weibo.model.FunctionArea;
//
//import java.util.List;
//
//public class FunctionAreaViewHolder extends ViewHolder<FunctionArea> {
//
//    private RecyclerView recycleView;
//
//    public FunctionAreaViewHolder(@NonNull View itemView, EventTransmissionListener eventTransmissionListener, ViewGroup parent) {
//        super(itemView, eventTransmissionListener, parent);
//        recycleView = findViewById(R.id.funcRecycleView);
//
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recycleView.setLayoutManager(manager);
//    }
//
//    @Override
//    public void onCreated() {
//
//    }
//
//    @Override
//    public void onBindViewHolder(FunctionArea model) {
//        super.onBindViewHolder(model);
////        FunctionArea functionArea = (FunctionArea) model;
//
//        recycleView.setModels((List) model.getFuncItems());
//        recycleView.setEventTransmissionListener(getEventTransmissionListener());
//    }
//}
