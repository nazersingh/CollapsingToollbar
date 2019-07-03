package com.nazer.collapsingtoollbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nazer.collapsingtoollbar.fragments.FragmentA;
import com.nazer.collapsingtoollbar.fragments.FragmentB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
     ViewPager mViewPager;
//     TabLayout mTabLayout;
    ArrayList<Fragment> arrayList;
    String name[]={"First","second"};
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private List<Student> studentList;

    // on scroll

    private static int current_page = 1;

    private int ival = 1;
    private int loadLimit = 20;
    private RecyclerView.Adapter mAdapter;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList<>();
        arrayList.add(new FragmentA());
        arrayList.add(new FragmentB());
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        collapsingToolbar=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("My Toolbar Tittle");
//        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
//        collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);

        //mTabLayout= (TabLayout) findViewById(R.id.tabs);

        ViewpagerAdapter viewpagerAdapter=new ViewpagerAdapter(getSupportFragmentManager(),name,arrayList);
        mViewPager.setAdapter(viewpagerAdapter);

       // mTabLayout.setupWithViewPager(mViewPager);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        studentList = new ArrayList<Student>();

        loadData(current_page);

        mAdapter = new CardViewDataAdapter(studentList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(
                mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do somthing...

                loadMoreData(current_page);

            }

        });
    }
    private void loadData(int current_page) {

        // I have not used current page for showing demo, if u use a webservice
        // then it is useful for every call request

        for (int i = ival; i <= loadLimit; i++) {
            Student st = new Student("Student " + i, "androidstudent" + i
                    + "@gmail.com", false);

            studentList.add(st);
            ival++;

        }

    }

    private void loadMoreData(int current_page) {

        // I have not used current page for showing demo, if u use a webservice
        // then it is useful for every call request

        loadLimit = ival + 20;

        for (int i = ival; i <= loadLimit; i++) {
            Student st = new Student("Student " + i, "androidstudent" + i
                    + "@gmail.com", false);

            studentList.add(st);
            ival++;
        }

        mAdapter.notifyDataSetChanged();

    }
    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pic);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbar.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorAccent)));
                collapsingToolbar.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
            }
        });
    }
}
