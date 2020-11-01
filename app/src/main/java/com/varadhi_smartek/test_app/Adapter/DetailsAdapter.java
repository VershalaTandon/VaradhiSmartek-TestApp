package com.varadhi_smartek.test_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.varadhi_smartek.test_app.GoogleMapActivity;
import com.varadhi_smartek.test_app.Model.DetailsModel;
import com.varadhi_smartek.test_app.R;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.NewsHolder> {
    Context context;
    List<DetailsModel> modelList;

    public DetailsAdapter(Context context, List<DetailsModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public DetailsAdapter.NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_details, parent, false);
        return new DetailsAdapter.NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailsAdapter.NewsHolder holder, final int position) {
        holder.tv_institute_name.setText("Institute Name : " + modelList.get(position).getInstitution_Name());
        holder.tv_poc.setText("POC Name : " + modelList.get(position).getPOC_Name());
        holder.tv_employee_id.setText("Employee ID : " + modelList.get(position).getMarketing_user_employee_id());

        holder.iv_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoogleMapActivity.class);
                intent.putExtra("imageAddress", modelList.get(position).getImage_address());
                intent.putExtra("addressLine1", modelList.get(position).getAddress_Line_1());
                intent.putExtra("addressLine2",modelList.get(position).getAddress_Line_2());
                intent.putExtra("landMark", modelList.get(position).getLand_Mark());
                intent.putExtra("city", modelList.get(position).getCity());
                intent.putExtra("state", modelList.get(position).getState());
                intent.putExtra("pincode", modelList.get(position).getPIN());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        ImageView iv_pin;
        TextView tv_institute_name, tv_poc, tv_employee_id;

        public NewsHolder(View itemView){
            super(itemView);

            tv_institute_name = itemView.findViewById(R.id.tv_institute_name);
            tv_poc = itemView.findViewById(R.id.tv_poc);
            tv_employee_id = itemView.findViewById(R.id.tv_employee_id);
            iv_pin = itemView.findViewById(R.id.iv_pin);
        }
    }
}
