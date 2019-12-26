package com.example.masood.phca.ChildList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masood.phca.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChildListAdapter extends FirestoreRecyclerAdapter<ChildItem, ChildListAdapter.NoteHolder> {

    public ChildListAdapter(@NonNull FirestoreRecyclerOptions<ChildItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull ChildItem model) {
        holder.textViewTitle.setText(model.getChildName());
        holder.textViewDescription.setText(model.getChildLastName());
        holder.textViewPriority.setText(String.valueOf(model.getPhone()));
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_childlist,
                parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.child_name);
            textViewDescription = itemView.findViewById(R.id.child_date);
            textViewPriority = itemView.findViewById(R.id.child_status);
        }
    }
}