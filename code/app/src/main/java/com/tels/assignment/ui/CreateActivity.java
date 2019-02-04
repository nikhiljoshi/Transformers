package com.tels.assignment.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tels.assignment.R;
import com.tels.assignment.connection.RetrofitClient;
import com.tels.assignment.connection.TransformerApi;
import com.tels.assignment.model.Transformer;
import com.tels.assignment.model.TransformerRequest;
import com.tels.assignment.model.UpdateTransformer;
import com.tels.assignment.utility.AppConstants;
import com.tels.assignment.utility.InputFilterNumber;

import org.parceler.Parcels;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    private boolean isEdit = false;
    private String editID = null;
    private Transformer mTransformer;
    private  UpdateTransformer mUpdateTransformer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Button btnCreateEdit = findViewById(R.id.btn_create_or_edit);
        Button btnAuto = findViewById(R.id.btn_auto);
        Button btnCancel = findViewById(R.id.btn_cancel);

        final EditText edtStrength = findViewById(R.id.edt_strength);
        final EditText edtIntelligence = findViewById(R.id.edt_intelligence);
        final EditText edtSpeed = findViewById(R.id.edt_speed);
        final EditText edtEndurance = findViewById(R.id.edt_endurance);
        final EditText edtRank = findViewById(R.id.edt_rank);
        final EditText edtCourage = findViewById(R.id.edt_courage);
        final EditText edtFirepower = findViewById(R.id.edt_firepower);
        final EditText edtSkill = findViewById(R.id.edt_skill);
        final EditText edtName = findViewById(R.id.edt_name);
        final Spinner spnTeam = findViewById(R.id.spn_team);

        InputFilter[] inputFilters = new InputFilter[] { new InputFilterNumber(1, 10) };
        edtStrength.setFilters(inputFilters);
        edtIntelligence.setFilters(inputFilters);
        edtSpeed.setFilters(inputFilters);
        edtEndurance.setFilters(inputFilters);
        edtRank.setFilters(inputFilters);
        edtCourage.setFilters(inputFilters);
        edtFirepower.setFilters(inputFilters);
        edtSkill.setFilters(inputFilters);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.team_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTeam.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String type = extras.getString(AppConstants.CREATE_TYPE);

            if (type.equals(AppConstants.CREATE_TYPE_CREATE)) {
                isEdit = false;
                btnCreateEdit.setText(R.string.btn_create);
            }
            else if (type.equals(AppConstants.CREATE_TYPE_EDIT)) {
                isEdit = true;
                btnCreateEdit.setText(R.string.btn_save);
                mTransformer = (Transformer) Parcels.unwrap(getIntent().getParcelableExtra("Transformer"));
                mUpdateTransformer = new UpdateTransformer(mTransformer);


                if (mTransformer != null) {
                    edtName.setText(mTransformer.getName());
                    edtStrength.setText(mTransformer.getStrength() + "");
                    edtIntelligence.setText(mTransformer.getIntelligence()  + "");
                    edtEndurance.setText(mTransformer.getEndurance()  + "");
                    edtCourage.setText(mTransformer.getCourage() + "");
                    edtRank.setText(mTransformer.getRank() + "");
                    edtSpeed.setText(mTransformer.getSpeed() + "");
                    edtFirepower.setText(mTransformer.getFirepower() + "");
                    edtSkill.setText(mTransformer.getSkill() + "");

                    if (mTransformer.getTeam() == AppConstants.TR_ATTR_TEAM_AUTOBOT)
                        spnTeam.setSelection(0);
                    else if (mTransformer.getTeam() == AppConstants.TR_ATTR_TEAM_DECEPTICON)
                        spnTeam.setSelection(1);
                    else
                        Log.w("CreateEdit","Edit: No team is matched for the Transformer");
                }
            }
        }

        btnCancel.setOnClickListener(v -> finish());

        btnAuto.setOnClickListener(v -> {
            Random random = new Random();

            edtStrength.setText((random.nextInt(10) + 1) + "");
            edtIntelligence.setText((random.nextInt(10) + 1) + "");
            edtSpeed.setText((random.nextInt(10) + 1) + "");
            edtEndurance.setText((random.nextInt(10) + 1) + "");
            edtRank.setText((random.nextInt(10) + 1) + "");
            edtCourage.setText((random.nextInt(10) + 1) + "");
            edtFirepower.setText((random.nextInt(10) + 1) + "");
            edtSkill.setText((random.nextInt(10) + 1) + "");
        });

        btnCreateEdit.setOnClickListener(v -> {
            TransformerRequest transformer = new TransformerRequest();

            if (edtCourage.getText().toString().equals("") || edtEndurance.getText().toString().equals("") || edtFirepower.getText().toString().equals("")
                    || edtIntelligence.getText().toString().equals("") || edtRank.getText().toString().equals("") || edtSkill.getText().toString().equals("")
                    || edtSpeed.getText().toString().equals("") || edtStrength.getText().toString().equals("")) {
                new AlertDialog.Builder(CreateActivity.this)
                        .setMessage(R.string.msg_fail_create_invalid_data)
                        .setNeutralButton(R.string.msg_btn_ok, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        }).show();
            }
            else {
                transformer.setName(edtName.getText().toString());
                transformer.setStrength(Integer.parseInt(edtStrength.getText().toString()));
                transformer.setIntelligence(Integer.parseInt(edtIntelligence.getText().toString()));
                transformer.setSpeed(Integer.parseInt(edtSpeed.getText().toString()));
                transformer.setEndurance(Integer.parseInt(edtEndurance.getText().toString()));
                transformer.setCourage(Integer.parseInt(edtCourage.getText().toString()));
                transformer.setRank(Integer.parseInt(edtRank.getText().toString()));
                transformer.setFirepower(Integer.parseInt(edtFirepower.getText().toString()));
                transformer.setSkill(Integer.parseInt(edtSkill.getText().toString()));

                if (spnTeam.getSelectedItem().toString().equals(getResources().getString(R.string.txt_autobot)))
                    transformer.setTeam("A");
                else if (spnTeam.getSelectedItem().toString().equals(getResources().getString(R.string.txt_decepticon)))
                    transformer.setTeam("D");
                else
                    Log.e("CreateEdit", "no class is matched selected team");


                TransformerApi requestInterface = RetrofitClient.getInstance(CreateActivity.this).create(TransformerApi.class);



                if (isEdit) {
                    requestInterface.updateTransformers(mUpdateTransformer).enqueue(new Callback<UpdateTransformer>() {
                        @Override
                        public void onResponse(Call<UpdateTransformer> call, Response<UpdateTransformer> response) {
                            Log.e("TAG", "post submitted to API." + response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<UpdateTransformer> call, Throwable t) {

                        }
                    });
                }else
                {
                    requestInterface.createTransformers(transformer).enqueue(new Callback<TransformerRequest>() {
                        @Override
                        public void onResponse(Call<TransformerRequest> call, Response<TransformerRequest> response) {
                            Log.e("TAG", "post submitted to API." + response.body().toString());

                        }

                        @Override
                        public void onFailure(Call<TransformerRequest> call, Throwable t) {
                            Log.e("TAG", "poserror." + t.getMessage());
                        }
                    });
                }

            }
        });
    }
}
