package com.example.smartcity_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.smartcity_v2.adapter.ServiceAdapter;
import com.example.smartcity_v2.adapter.ViewPagerAdapter;
import com.example.smartcity_v2.beans.ItemNewsCategory;
import com.example.smartcity_v2.beans.ServiceList;
import com.example.smartcity_v2.fragments.HomeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {

    private ViewPager2 viewPager2;
    private List<HomeFragment> fragments;
    private TabLayout tabLayout;
    private List<String> list;
    private ViewFlipper viewFlipper;
    private List<String> imgs;
    private List<ServiceList> serviceLists;
    private RecyclerView recyclerView;


    private List<ItemNewsCategory> itemNewsCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.vf);
        viewPager2 = (ViewPager2) findViewById(R.id.view_page);
        tabLayout = (TabLayout) findViewById(R.id.tab_out);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_service);
        fragments = new ArrayList<>();
        list = new ArrayList<>();

        getLunBo();
        getService();
        getNews();




    }


    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(itemNewsCategories.get(position).getName());
    }

    public void getLunBo(){
        Thread thread = new Thread(()->{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://124.93.196.45:10001/prod-api/api/rotation/list").build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String data = response.body().string();
                    imgs = new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray rows = jsonObject.getJSONArray("rows");
                        for (int i = 0; i < rows.length(); i++) {
                            JSONObject jsonObject1 = rows.getJSONObject(i);
                            String advImg = jsonObject1.getString("advImg");
                            imgs.add("http://124.93.196.45:10001"+advImg);
                        }
                        handler.sendEmptyMessage(2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        });
        thread.start();
    }

    public void getService(){
        Thread thread = new Thread(()->{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://124.93.196.45:10001/prod-api/api/service/list").build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String data = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray rows = jsonObject.getJSONArray("rows");
                        serviceLists = new ArrayList<>();
                        for (int i = 0; i < rows.length(); i++) {
                            JSONObject jsonObject1 = rows.getJSONObject(i);
                            int id = jsonObject1.getInt("id");
                            String serviceName = jsonObject1.getString("serviceName");
                            String serviceType = jsonObject1.getString("serviceType");
                            String imgUrl = jsonObject1.getString("imgUrl");
                            int sort = jsonObject1.getInt("sort");
                            serviceLists.add(new ServiceList(id,serviceName,serviceType,sort,"http://124.93.196.45:10001"+imgUrl));
                        }
                        handler.sendEmptyMessage(3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        thread.start();
    }

    public void getNews(){
        Thread thread = new Thread(()->{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://124.93.196.45:10001/prod-api/press/category/list").build();
            try {
                String jsonBody = client.newCall(request).execute().body().string();
                JSONObject jsonData = new JSONObject(jsonBody);
                JSONArray jsonCategories = jsonData.getJSONArray("data");
                itemNewsCategories = new ArrayList<>();
                for (int i = 0; i < jsonCategories.length(); i++) {
                    JSONObject jsonCategory = jsonCategories.getJSONObject(i);
                    int jsonCategoryId = jsonCategory.getInt("id");
                    String name = jsonCategory.getString("name");
                    int sort = jsonCategory.getInt("sort");
                    itemNewsCategories.add(new ItemNewsCategory(jsonCategoryId,name,sort));
                }
                handler.sendEmptyMessage(1);
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
                case 1:
                    for (int i = 0; i < itemNewsCategories.size(); i++) {
                        HomeFragment fragment = HomeFragment.newInstance(String.valueOf(itemNewsCategories.get(i).getId()));
                        fragments.add(fragment);
                        list.add(String.valueOf(i));
                    }
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(MainActivity.this);
                    viewPagerAdapter.setFragments(fragments);

                    viewPager2.setAdapter(viewPagerAdapter);

                    new TabLayoutMediator(tabLayout,viewPager2,MainActivity.this).attach();
                    break;
                case 2:
                    for (String img : imgs) {
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(MainActivity.this).load(img).into(imageView);
                        viewFlipper.addView(imageView);
                    }

                    break;
                case 3:
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),6));
                    recyclerView.setAdapter(new ServiceAdapter(R.layout.service_layout,serviceLists));
                    break;
            }
        }
    };

}