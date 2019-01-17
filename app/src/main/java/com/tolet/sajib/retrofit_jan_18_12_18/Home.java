package com.tolet.sajib.retrofit_jan_18_12_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tolet.sajib.retrofit_jan_18_12_18.Api.KitchenApi;
import com.tolet.sajib.retrofit_jan_18_12_18.model.ModelTwo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KitchenlistAdapter adapter;
    private ArrayList<ModelTwo> kitchenLists;
    SearchView searchView;
    String baseUrl="http://apptitive.com/projects/web/foodpeon_api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadData();
        setupRecyclerView();

    }

    private void loadData() {
        Gson gson = new GsonBuilder().setLenient().create();
        Log.d("" + gson, "JSON");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Log.i("JSON", "yahooo  " + gson);

        KitchenApi retroFIT = retrofit.create(KitchenApi.class);

        Call<List<ModelTwo>> call = retroFIT.getkitchens();

        call.enqueue(new Callback<List<ModelTwo>>() {
            @Override
            public void onResponse(final Call<List<ModelTwo>> call, final Response<List<ModelTwo>> response) {
                Log.i("JSON", "yahoooaa  " + response);
                try{
                    kitchenLists = new ArrayList<>(response.body());
                    if (!kitchenLists.isEmpty()) {
                        adapter = new KitchenlistAdapter(kitchenLists, getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Home.this, "Not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<ModelTwo>> call, Throwable t) {
                Log.i("JSON", "yahooobb  " + call);

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG)
                        .show();

            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
