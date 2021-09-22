package com.example.medical.Doctor;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class doctor_adapter extends RecyclerView.Adapter<doctor_adapter.ViewHolder> {
    private List<Drawer_item> items;
    private Map<Class<? extends Drawer_item>, Integer> viewTypes;
    private SparseArray<Drawer_item> holderFactories;

    private OnItemSelectedListener listener;

    public doctor_adapter(List<Drawer_item> items) {
        this.items = items;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();

        processViewTypes();
    }

    private void processViewTypes() {

        int type = 0;
        for (Drawer_item item : items) {
            if (!viewTypes.containsKey(item.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = holderFactories.get(viewType).createViewHolder(parent);
        holder.drawerAdapter = this;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }

    public void setSelected(int position) {
        Drawer_item newChecked = items.get(position);
        if (!newChecked.isSelectable()) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            Drawer_item item = items.get(i);
            if (item.isChecked()) {
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }


        newChecked.setChecked(true);
        notifyItemChanged(position);

        if (listener != null) {
            listener.onItemSelected(position);
        }
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }
    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }



    abstract static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private doctor_adapter drawerAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            drawerAdapter.setSelected(getAdapterPosition());

        }
    }
}
