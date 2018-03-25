package net.dixq.caloriemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.dixq.caloriemanager.common.Define;
import net.dixq.caloriemanager.takencalories.TakenCaloriesActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Graph _graph;
    private Calendar _calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _calendar = Calendar.getInstance();
        initialize();
    }

    private void initialize(){
        _graph = new Graph(findViewById(R.id.graph_mini));
        findViewById(R.id.btn_prev_data).setOnClickListener(this);
        findViewById(R.id.btn_data).setOnClickListener(this);
        findViewById(R.id.btn_next_data).setOnClickListener(this);
        findViewById(R.id.btn_taken_calories).setOnClickListener(this);
    }

    private void onClickPrevData(){
    }

    private void onClickData(){
    }

    private void onClickNextData(){
    }

    private void onClickTakenCalories(){
        Intent intent = new Intent(this, TakenCaloriesActivity.class);
        intent.putExtra(TakenCaloriesActivity.INTENT_TAG_YEAR, _calendar.get(Calendar.YEAR));
        intent.putExtra(TakenCaloriesActivity.INTENT_TAG_MONTH, _calendar.get(Calendar.MONTH));
        intent.putExtra(TakenCaloriesActivity.INTENT_TAG_DATE, _calendar.get(Calendar.DATE));
        startActivityForResult(intent, Define.REQUEST_CODE_TAKEN_CALORIE);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.btn_prev_data)){ onClickPrevData(); }
        if(v==findViewById(R.id.btn_data)     ){ onClickData(); }
        if(v==findViewById(R.id.btn_next_data)){ onClickNextData(); }
        if(v==findViewById(R.id.btn_taken_calories)){ onClickTakenCalories(); }
    }

}
