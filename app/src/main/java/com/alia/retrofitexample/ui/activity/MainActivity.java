package com.alia.retrofitexample.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alia.retrofitexample.R;
import com.alia.retrofitexample.model.PostModel;
import com.alia.retrofitexample.network.RetrofitService;
import com.alia.retrofitexample.ui.adapter.PostsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    List<PostModel> posts = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        subscribe(RetrofitService.getInstance().getApi().getData("bash", 1), postCollection -> {
            posts.addAll(postCollection);
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }
}
