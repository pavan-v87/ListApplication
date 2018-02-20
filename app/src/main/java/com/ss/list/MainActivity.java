package com.ss.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.login.Login;
import com.login.LoginModule;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    private ListView mListView;
    private ProgressBar mProgress;
    private TextView mEmptyText;

    @Inject Login login;
    @Inject ListServiceApi listServiceApi;
    @Inject ListPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainComponent.builder().listPresenterModule().loginModule(new LoginModule()).build().inject(this);
        //checkLogin();
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mEmptyText = (TextView) findViewById(R.id.emptyText);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, DetailsFragments.newInstance(item), "DETAIL").addToBackStack(null).commit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.fetchList();
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgress.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setErrorMessage(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEmptyText.setText(msg);
                mListView.setEmptyView(mEmptyText);
            }
        });
    }

    @Override
    public void setItems(final List<Item> items) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CustomAdaptor adapter = new CustomAdaptor(getApplicationContext(), items);
                mListView.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }
}
