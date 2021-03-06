package com.tels.assignment.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tels.assignment.R;
import com.tels.assignment.adapter.TransformerDataAdapter;
import com.tels.assignment.connection.RetrofitClient;
import com.tels.assignment.connection.TransformerApi;
import com.tels.assignment.model.Transformer;
import com.tels.assignment.model.Transformers;
import com.tels.assignment.presenter.BattleLogic;
import com.tels.assignment.utility.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 * Main activity is responsible to display list of item  and refrech on pull
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_rfview)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private List<Transformer> mTransformersArrayList;
    private ActionBar mActionBar;

    private String mToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_list);
        ButterKnife.bind(this);

        mActionBar = getSupportActionBar();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            loadJSON();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadJSON();
    }





    @OnClick(R.id.btn_create)
    public void createClicked(View view) {
        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
        intent.putExtra(AppConstants.CREATE_TYPE, AppConstants.CREATE_TYPE_CREATE);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_reset)
    public void resetClicked(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Token","");
        editor.apply();
    }

    @OnClick(R.id.btn_battle)
    public void battleClicked(View view) {
        int result = BattleLogic.startBattle(mTransformersArrayList);

        new AlertDialog.Builder(MainActivity.this)
                .setMessage(result)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }


    private void loadJSON() {
        TransformerApi requestInterface = RetrofitClient.getInstance(MainActivity.this).create(TransformerApi.class);
        Call<Transformers> call = requestInterface.getData();

        call.enqueue(new Callback<Transformers>() {
            @Override
            public void onResponse(Call<Transformers> call, Response<Transformers> response) {
                if(response.body()!=null) {
                    mTransformersArrayList = response.body().getTransformers();
                    TransformerDataAdapter adapter = new TransformerDataAdapter(response.body().getTransformers(), MainActivity.this);
                    mRecyclerView.setAdapter(adapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onFailure(Call<Transformers> call, Throwable error) {
                Log.e("TAG",error.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
