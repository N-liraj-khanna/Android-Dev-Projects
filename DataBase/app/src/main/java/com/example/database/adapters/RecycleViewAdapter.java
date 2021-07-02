package com.example.database.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.Model.Contact;
import com.example.database.R;
import com.example.database.View.DetailsPage;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecycleViewAdapter(Context context, ArrayList<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contact_cell_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Contact c = contactList.get(position);

        viewHolder.phoneNumber.setText(c.getPhoneNumber());
        viewHolder.contactName.setText(c.getName());
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView contactName;
        public TextView phoneNumber;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            contactName=itemView.findViewById(R.id.name);
            phoneNumber=itemView.findViewById(R.id.phone_number);
            imageView=itemView.findViewById(R.id.contact_img);
        }

        @Override
        public void onClick(View v) {
            Contact c = contactList.get(v.getId());

            Intent intent = new Intent(context, DetailsPage.class);
            intent.putExtra("name",c.getName());
            intent.putExtra("phoneNum",c.getPhoneNumber());

            context.startActivity(intent);
        }
    }
}
