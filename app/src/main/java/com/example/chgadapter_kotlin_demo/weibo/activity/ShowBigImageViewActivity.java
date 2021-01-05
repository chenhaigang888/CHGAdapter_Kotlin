//package com.example.chgadapter_kotlin_demo.weibo.activity;
//
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.PagerSnapHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.chg.adapter.EventTransmissionListener;
//import com.chg.adapter.Model;
//import com.example.chgadapter_kotlin_demo.R;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class ShowBigImageViewActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.show_big_image_view);
//
//        recyclerView = findViewById(R.id.recyclerView);
//
//        LinearLayoutManager manager = new LinearLayoutManager(ShowBigImageViewActivity.this);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(manager);
//
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
//
//
//        HashMap map = (HashMap) getIntent().getExtras().get("sources");
//        recyclerView.setModels((List<Model>) map.get("sources"));
//        recyclerView.scrollToPosition((Integer) map.get("position"));
//        recyclerView.setEventTransmissionListener(new EventTransmissionListener() {
//            @org.jetbrains.annotations.Nullable
//            @Override
//            public Object onEventTransmissionListener(@org.jetbrains.annotations.Nullable Object target, @org.jetbrains.annotations.Nullable Object params, int eventId, @org.jetbrains.annotations.Nullable CallBack callBack) {
//                return null;
//            }
//
//        });
//
//
//    }
//}
