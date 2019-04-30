package com.example.silvia.recyclerviewadd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private int counter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names=this.getAllNames();

        mRecyclerView= findViewById(R.id.recyclerView);
        mLayoutManager= new LinearLayoutManager(this);
        mAdapter= new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {

                deleteName(position);

                Toast.makeText(MainActivity.this, name+ " " + position, Toast.LENGTH_SHORT).show();


            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.add_name:

                this.addName(0);
                return true;
                default:

                    return super.onOptionsItemSelected(item);

        }

    }



    private List<String> getAllNames()
    {

        return new ArrayList<String>()
        {{
            add("Alex");
            add("Juan");
            add("Sebas");
            add("Lucas");
            add("Damian");
            add("Juan");


        }};

    }


    private  void addName(int position)
    {
       names.add(position, "New Name"+ (++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position); //te lleva  al item  que  se eliminado


    }

    private void deleteName(int position)
    {
        names.remove(position);
        mAdapter.notifyItemRemoved(position);

    }
}
