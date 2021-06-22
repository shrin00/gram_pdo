package com.example.gram_pdo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserApplicationAdapter extends FirebaseRecyclerAdapter<UserApplicationModel, UserApplicationAdapter.myviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    String servicepostkey;
    public UserApplicationAdapter(@NonNull FirebaseRecyclerOptions<UserApplicationModel> options, Context context, String servicepostkey) {
        super(options);
        this.context = context;
        this.servicepostkey = servicepostkey;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserApplicationModel model) {
        final String postkey = getRef(position).getKey();
        holder.tName.setText(model.getName());
        holder.tEmail.setText(model.getEmail());
        holder.tName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewUserApplication.class);
                intent.putExtra("postkey", postkey);
                intent.putExtra("servicepostkey", servicepostkey);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userapplication_row, parent, false);
        return new UserApplicationAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView tName, tEmail;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tName = (TextView) itemView.findViewById(R.id.userApplicationName_id);
            tEmail = (TextView) itemView.findViewById(R.id.userApplicationEmail_id);
        }
    }
}
