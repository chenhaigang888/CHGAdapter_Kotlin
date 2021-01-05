//package com.example.chgadapter_kotlin_demo.weibo.VH;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import com.chg.adapter.EventTransmissionListener;
//import com.chg.adapter.ViewHolder;
//import com.example.chgadapter_kotlin_demo.R;
//import com.example.chgadapter_kotlin_demo.weibo.model.FuncItem;
//
//public class FuncItemViewHolder extends ViewHolder<FuncItem> {
//    private ImageView icon;
//    private TextView name;
//
//    public FuncItemViewHolder(@NonNull View itemView, EventTransmissionListener eventTransmissionListener, ViewGroup parent) {
//        super(itemView, eventTransmissionListener, parent);
//
//    }
//
//    @Override
//    public void onCreated() {
//        icon = findViewById(R.id.funcIcon);
//        name = findViewById(R.id.funcName);
//    }
//
//    @Override
//    public void onBindViewHolder(FuncItem model) {
//        super.onBindViewHolder(model);
//
//        icon.setImageResource(model.getIcon());
//        name.setText(model.getName());
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getEventTransmissionListener().onEventTransmission(this,getModel(),0,null);
//            }
//        });
//    }
//}
