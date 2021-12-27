package com.example.jsonkill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    RecyclerView recyclerView;
    List<ItemModel> itemModels;
    ItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("TAG", "Here");
        itemModels = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemModels, this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        new Download().execute("https://lebavui.github.io/jsons/users.json");

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailView.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item_model", itemModels.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    class Download extends AsyncTask<String, Void, List<ItemModel>> {
        @Override
        protected void onPreExecute() {


        }

        @Override
        protected List<ItemModel> doInBackground(String... strings) {
            try {

                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                String jsonString = stringBuilder.toString();
//                List<ItemModel> items = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String userName = jsonObject.getString("username");
                    String email = jsonObject.getString("email");
                    String address = jsonObject.getString("address");
                    String phone = jsonObject.getString("phone");
                    String company = jsonObject.getString("company");
                    String avatar = jsonObject.getString("avatar");
                    ItemModel itemModel = new ItemModel(id, avatar, userName, email, name, company, phone, address);
                    itemModels.add(itemModel);
                }
                return itemModels;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ItemModel> itemModels) {
            if (itemModels != null) {
                itemAdapter.notifyDataSetChanged();
            }
            Log.v("TAG", itemModels.size() + "");
        }
    }
}