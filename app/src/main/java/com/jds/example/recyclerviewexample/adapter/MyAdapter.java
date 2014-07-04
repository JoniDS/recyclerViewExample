package com.jds.example.recyclerviewexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jds.example.recyclerviewexample.MyObjectTapListener;
import com.jds.example.recyclerviewexample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jonids on 04/07/14.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //out items array
    private ArrayList<MyObject> items = new ArrayList<MyObject>();

    //Our custom interface to handle the tap on items
    private MyObjectTapListener tapListener;

    //Ensure that ids are unique
    private int itemCounter = 1;

    /**
     * To set the listener we use when an image is clicked
     *
     * @param listener
     */
    public void setObjectTapListener(MyObjectTapListener listener) {
        tapListener = listener;
    }

    /**
     * Create new views
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /**
     * Fill the view with data from our adapter
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyObject item = items.get(position);
        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
        Picasso.with(holder.image.getContext()).load(item.getUrl()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item != null)
                    tapListener.itemTap(items.indexOf(item));
            }
        });

        holder.itemView.setTag(item);
    }

    /**
     * Will add a new item to our viewModel
     *
     * @param position
     * @param url
     */
    public void addItem(int position, String url) {
        items.add(position, new MyObject(itemCounter++, url));
        //Notify that a new item has been added
        notifyItemInserted(position);
    }

    /**
     * Return the item at the position
     *
     * @param position
     * @return
     */
    public MyObject getItemAt(int position) {
        if (position < items.size())
            return items.get(position);
        return null;
    }

    /**
     * Number of items in our adapter
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(MyObject item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Our view holder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }
}