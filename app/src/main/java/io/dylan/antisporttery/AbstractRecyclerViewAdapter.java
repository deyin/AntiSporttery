package io.dylan.antisporttery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRecyclerViewAdapter<T extends ItemObject> extends RecyclerView.Adapter<AbstractRecyclerViewAdapter.AbstractViewHolder> {

    private List<T> mDataList = new ArrayList<>();

    protected final Context mContext;

    public AbstractRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public final Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AbstractViewHolder viewHolder = getViewHolder(parent, viewType);
        viewHolder.initViews();
        return viewHolder;
    }

    protected abstract AbstractViewHolder getViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder holder, int position) {
        T item = mDataList.get(position);
        if (item != null) {
            holder.initItemView(item, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemViewType();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static abstract class AbstractViewHolder<T extends ItemObject> extends RecyclerView.ViewHolder {

        public AbstractViewHolder(View itemView) {
            super(itemView);
        }

        protected abstract void initViews();

        protected abstract void initItemView(@NonNull T item, int position);
    }

    public void dataListChanged(List<T> mDataList) {
        this.mDataList.clear();
        this.mDataList.addAll(mDataList);
        this.notifyDataSetChanged();
    }
}