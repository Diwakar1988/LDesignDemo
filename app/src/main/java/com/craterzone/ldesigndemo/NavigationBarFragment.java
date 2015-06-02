package com.craterzone.ldesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.craterzone.ldesigndemo.adapters.AccountAdapter;
import com.craterzone.ldesigndemo.modal.Account;

import java.util.ArrayList;


public class NavigationBarFragment extends Fragment implements  AccountAdapter.ItemClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private RecyclerView recyclerView;
    private AccountAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_navigation_bar, null, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.drawer_list_view);
        adapter = new AccountAdapter(getActivity(), getData());
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar) {
        this.drawerLayout = drawerLayout;
        this.toolbar = toolbar;
        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        drawerLayout.setDrawerListener(toggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                toggle.syncState();
            }
        });

    }

    public ArrayList<Account> getData() {
        ArrayList<Account> data = new ArrayList<Account>();
        data.add(new Account("Diwakar Mishra", "98982301", "SAVING"));
        data.add(new Account("Babul Prabhakar", "98982302", "CURRENT"));
        data.add(new Account("Piyush Sharma", "98982303", "SALARY"));
        data.add(new Account("Pankaj Kumar", "98982304", "LOAN"));
        data.add(new Account("Vivek Pratap Singh", "98982305", "SAVING"));
        data.add(new Account("Lawraj Gadhigonkar", "98982306", "CURRENT"));
        data.add(new Account("Shivani Gaur", "98982307", "SALARY"));
        data.add(new Account("Vishal Gaur", "98982308", "LOAN"));
        return data;
    }

    @Override
    public void onItemClicked(View view, int position) {
        Toast.makeText(getActivity(),"Position: "+position,Toast.LENGTH_SHORT).show();
        getActivity().startActivity(new Intent(getActivity(),SubActivity.class));
    }
}
