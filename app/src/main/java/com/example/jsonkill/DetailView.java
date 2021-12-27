package com.example.jsonkill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) return;
        ItemModel itemModel = (ItemModel) bundle.get("item_model");
        TextView textName = findViewById(R.id.textView_name);
        TextView textUsername = findViewById(R.id.textView_username);
        TextView textPhone = findViewById(R.id.textView_phone);
        TextView textAddress = findViewById(R.id.textView_Address);
        TextView textCompany = findViewById(R.id.textView_company);
        TextView textEmail = findViewById(R.id.textView_email);
        StringBuilder address, company;
        address = new StringBuilder();
        company = new StringBuilder();
        JSONObject JSONAddress, JSONCompany;
        try {
            JSONAddress = new JSONObject(itemModel.getAddress());
            JSONCompany = new JSONObject(itemModel.getCompany());
            address.append("Street: ").append(JSONAddress.getString("street")).append("\n")
                    .append("Suite: ").append(JSONAddress.getString("suite")).append("\n")
                    .append("City: ").append(JSONAddress.getString("city")).append("\n")
                    .append("Zipcode: ").append(JSONAddress.getString("zipcode"));
            company.append("Name: ").append(JSONCompany.getString("name")).append("\n")
                    .append("Catch Phrase: ").append(JSONCompany.getString("catchPhrase")).append("\n")
                    .append("BS: ").append(JSONCompany.getString("bs"));
            textAddress.setText(address);
            textCompany.setText(company);
        } catch (Exception e) {
            e.printStackTrace();
        }

        textName.setText(itemModel.getName());
        textUsername.setText(itemModel.getUserName());
        textPhone.setText(itemModel.getPhone());
        textEmail.setText(itemModel.getEmail());
    }
}