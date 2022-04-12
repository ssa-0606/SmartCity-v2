package com.example.smartcity_v2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcity_v2.R;
import com.example.smartcity_v2.adapter.NewsItemAdapter;
import com.example.smartcity_v2.beans.ItemNewsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView;
    private RecyclerView recyclerView;

    private List<ItemNewsList> newsLists;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler);

        getNews();



        // Inflate the layout for this fragment
        return inflate;

    }

    public void getNews(){
        Thread thread = new Thread(()->{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://124.93.196.45:10001/prod-api/press/press/list?type="+mParam1).build();
            try {
                Call call1 = client.newCall(request);
                String jsonNews = call1.execute().body().string();
                JSONObject jsonObject1 = new JSONObject(jsonNews);
                JSONArray jsonNewsItems = jsonObject1.getJSONArray("rows");
                newsLists = new ArrayList<>();
                for (int j = 0; j < jsonNewsItems.length(); j++) {
                    JSONObject jsonNewsItem = jsonNewsItems.getJSONObject(j);
                    int newsId = jsonNewsItem.getInt("id");
                    String title = jsonNewsItem.getString("title");
                    String cover = jsonNewsItem.getString("cover");
                    String content = jsonNewsItem.getString("content");
                    int commentNum = jsonNewsItem.getInt("commentNum");
                    int likeNum = jsonNewsItem.getInt("likeNum");
                    int readNum = jsonNewsItem.getInt("readNum");
                    String publishDate = jsonNewsItem.getString("publishDate");
                    newsLists.add(new ItemNewsList(newsId,title,"http://124.93.196.45:10001"+cover,content,commentNum,likeNum,readNum,publishDate));
                }
                handler.sendEmptyMessage(87);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 87:
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                    recyclerView.setAdapter(new NewsItemAdapter(R.layout.news_item,newsLists));
                    break;
            }
        }
    };

}