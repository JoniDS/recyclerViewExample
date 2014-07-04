package com.jds.example.recyclerviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jds.example.recyclerviewexample.adapter.MyAdapter;

import java.util.Random;


public class MainActivity extends Activity {

    RecyclerView recyclerView;
    //Our adapter
    private MyAdapter mAdapter;
    //RecyclerView needs a LayoutManager
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] sources = {
            "http://lorempixel.com/600/250/",
            "http://lorempixel.com/600/250/sports",
            "http://lorempixel.com/600/200/sports/Dummy-Text",
            "http://lorempixel.com/600/200/nature",
            "http://lorempixel.com/600/200/food",
    };

    public MainActivity() {
    }

    /**
     * Returns a random number from min to max
     *
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        //Create an instance of our adapter
        mAdapter = new MyAdapter();

        //Custom listener for when an image is tapped
        mAdapter.setObjectTapListener(new MyObjectTapListener() {
            @Override
            public void itemTap(int position) {
                Toast.makeText(getApplicationContext(), "tapped " + position, Toast.LENGTH_SHORT).show();
            }
        });

        //Assign our adapter to the recyclerView
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                if (mAdapter != null) {
                    mAdapter.addItem(0, sources[randInt(0, sources.length - 1)]);
                    //Scroll to the added item
                    recyclerView.scrollToPosition(0);
                }
                return true;
            case R.id.action_remove:
                if (mAdapter != null && mAdapter.getItemCount() > 0) {
                    mAdapter.removeItem(mAdapter.getItemAt(0));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
