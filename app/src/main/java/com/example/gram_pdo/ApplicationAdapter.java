package com.example.gram_pdo;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplicationAdapter extends FirebaseRecyclerAdapter<ServiceviewModel, ApplicationAdapter.myviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    DatabaseReference mserviceref;
    RecyclerView row_recview;
    UserApplicationAdapter museradapter;
    public ApplicationAdapter(@NonNull FirebaseRecyclerOptions<ServiceviewModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ServiceviewModel model) {
        final String postkey = getRef(position).getKey();
        holder.heading.setText(model.getServicename());
        Log.d("Hello", "Hello");

        mserviceref = FirebaseDatabase.getInstance().getReference().child("Services").child(postkey).child("Applications");
        FirebaseRecyclerOptions<UserApplicationModel> options1 =
                new FirebaseRecyclerOptions.Builder<UserApplicationModel>()
                        .setQuery(mserviceref, UserApplicationModel.class)
                        .build();

        museradapter = new UserApplicationAdapter(options1, context, postkey);
        row_recview.setAdapter(museradapter);
        museradapter.startListening();
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_row, parent, false);
        return new ApplicationAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView heading;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.heading_id);
            row_recview = (RecyclerView) itemView.findViewById(R.id.id_rowrecycleview);
            row_recview.setLayoutManager(new LinearLayoutManager(context));
        }
    }


}
