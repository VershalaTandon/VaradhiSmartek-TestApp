package com.varadhi_smartek.test_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.varadhi_smartek.test_app.Adapter.DetailsAdapter;
import com.varadhi_smartek.test_app.Interface.Api;
import com.varadhi_smartek.test_app.Model.DetailsModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    Api api;
    ArrayList<DetailsModel> detailsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        detailsModels = new ArrayList<DetailsModel>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        load();

    }
    private void load() {
        try {
            Call<Object> call = api.get_booking_appointment_list("VI020PE0016", "All", "Employee", "2020-07-25");

            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if(!response.isSuccessful())
                        Log.e("API not success",response.toString()+response.message()+response.errorBody()+response.code());

                    Object result = response.body();
                    Log.e("Response", String.valueOf(result));
                    if (result == null) {
                        Toast.makeText(getApplicationContext(), "Something went wrong. Please try after sometime.", Toast.LENGTH_LONG).show();
                    } else {
                        getData(result);
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.e("Failed","t.printStackTrace()"+t.getCause()+t.getMessage());
                }
            });
        }
        catch (Exception e) {
            String TAG="tag";
            Log.e(TAG, e.getMessage());
        }
    }

    private void getData(Object o) {
        try{
            String jsonInString = new Gson().toJson(o);
            JSONObject response = new JSONObject(jsonInString);

            String message = response.getString("message");
            String status = response.getString("status");
            if(message.equalsIgnoreCase("List of appointments") && status.equalsIgnoreCase("Success")){
                JSONArray unassigned_Array = response.getJSONArray("unassigned");
                for(int i = 0; i < unassigned_Array.length(); i++){
                    JSONObject jsonObject = unassigned_Array.getJSONObject(i);
                    String institutionName = jsonObject.getString("Institution_Name");
                    String pocName = jsonObject.getString("POC_Name");
                    String employeeId = jsonObject.getString("marketing_user_employee_id");
                    String imageAddress = jsonObject.getString("image_address");
                    String addressLine1 = jsonObject.getString("Address_Line_1");
                    String addressLine2 = jsonObject.getString("Address_Line_2");
                    String landMark = jsonObject.getString("Land_Mark");
                    String city = jsonObject.getString("City");
                    String state = jsonObject.getString("State");
                    String pin = jsonObject.getString("PIN");

                    DetailsModel detailsModel = new DetailsModel(institutionName, pocName, employeeId, imageAddress, addressLine1,
                            addressLine2, landMark, city, state, pin);
                    detailsModels.add(detailsModel);
                }

                recycler_view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                DetailsAdapter adapter = new DetailsAdapter(MainActivity.this, detailsModels);
                recycler_view.setAdapter(adapter);
            }
        }
        catch (JSONException e){
            String TAG="tag";
            Log.e(TAG, e.getMessage());
        }
    }
}