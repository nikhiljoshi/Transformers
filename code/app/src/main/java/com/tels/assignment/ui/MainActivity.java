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
import android.widget.Button;
import android.widget.Toast;

import com.tels.assignment.R;
import com.tels.assignment.adapter.TransformerDataAdapter;
import com.tels.assignment.connection.RetrofitClient;
import com.tels.assignment.connection.TransformerApi;
import com.tels.assignment.model.Transformer;
import com.tels.assignment.model.Transformers;
import com.tels.assignment.presenter.BattleLogic;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 * Main activity is responsible to display list of item  and refrech on pull
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Transformer> mTransformersArrayList;
    private ActionBar mActionBar;
    private CompositeDisposable mCompositeDisposable;

    private String mToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);


        initView();

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
        mCompositeDisposable = new CompositeDisposable();





    }

    @Override
    protected void onResume() {
        super.onResume();
        loadJSON();
    }



    /***
     * This method is used initilize/bind view with activity
     */
    private void initView()
    {
        mActionBar = getSupportActionBar();
        mRecyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        mSwipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.swipe_rfview);
        Button btnCreate = findViewById(R.id.btn_create);
        Button btnReset = findViewById(R.id.btn_reset);
        Button btnBattle = findViewById(R.id.btn_battle);

        btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,CreateActivity.class);
            startActivity(intent);
            finish();
        });

        btnReset.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Token","");
            editor.apply();
        });

        btnBattle.setOnClickListener(v -> {
            int result = BattleLogic.startBattle(mTransformersArrayList);

            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(result)
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();

        });



    }


    private void loadJSON() {
        TransformerApi requestInterface = RetrofitClient.getInstance(MainActivity.this).create(TransformerApi.class);
        Call<Transformers> call = requestInterface.getData();

        call.enqueue(new Callback<Transformers>() {
            @Override
            public void onResponse(Call<Transformers> call, Response<Transformers> response) {
                mTransformersArrayList = response.body().getTransformers();
                TransformerDataAdapter adapter = new TransformerDataAdapter(response.body().getTransformers(), MainActivity.this);
                mRecyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
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
        mCompositeDisposable.clear();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
