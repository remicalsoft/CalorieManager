package net.dixq.caloriemanager.takencalories;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import net.dixq.caloriemanager.R;
import net.dixq.caloriemanager.common.Define;
import net.dixq.caloriemanager.common.OkCancelDialog;
import net.dixq.caloriemanager.common.PrefUtils;
import net.dixq.caloriemanager.search.CalorieSearchActivity;

import java.util.ArrayList;

/**
 * Created by dixq on 2018/03/25.
 */

public class TakenCaloriesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    public static final String INTENT_TAG_YEAR  = "intent_tag_year";
    public static final String INTENT_TAG_MONTH = "intent_tag_month";
    public static final String INTENT_TAG_DATE  = "intent_tag_date";

    private ListAdapter _listAdapterToday, _listAdapterHistory;
    private ListView _listViewToday, _listViewHistory;
    private Button _btnAdd, _btnSearch;
    private EditText _editName, _editCalorie;
    private Spinner _spinnerWhen, _spinnerNum;
    private int _year, _month, _date;
    private static final String DELM = "'";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_calories);

        _year = getIntent().getIntExtra(INTENT_TAG_YEAR, 0);
        _month = getIntent().getIntExtra(INTENT_TAG_MONTH, 0);
        _date = getIntent().getIntExtra(INTENT_TAG_DATE, 0);

        _btnAdd = findViewById(R.id.btn_add);
        _btnAdd.setOnClickListener(this);
        _btnSearch = findViewById(R.id.btn_search_calories);
        _btnSearch.setOnClickListener(this);
        _editName = findViewById(R.id.edit_eaten_name);
        _editCalorie = findViewById(R.id.edit_calorie);
        _spinnerWhen = findViewById(R.id.spinner_when);
        _spinnerNum = findViewById(R.id.spinner_num);
        _listViewToday = findViewById(R.id.list_taken_today);
        _listViewToday.setOnItemClickListener(this);
        _listViewToday.setOnItemLongClickListener(this);
        _listViewHistory = findViewById(R.id.list_taken_history);
        _listViewHistory.setOnItemClickListener(this);
        _listViewHistory.setOnItemLongClickListener(this);

        getSupportActionBar().setTitle(_year+"/"+_month+"/"+_date+"の摂取カロリー");

        rebuildList();
    }

    @Override
    public void onClick(View view) {
        if(view == _btnAdd){
            onClickAdd();
        }
        if(view == _btnSearch){
            onClickSearch();
        }
    }

    private void rebuildList(){
        {
            ArrayList<TakenCalotiesData> list = new ArrayList<>();
            String tag = String.format(PrefUtils.PREF_TAG_TAKEN_DATA, _year, _month, _date);
            String[] dat = PrefUtils.read(this, tag);
            if(dat!=null) {
                for(String d : dat){
                    String[] split = d.split(DELM);
                    if(split!=null && split.length==4) {
                        list.add(new TakenCalotiesData(split[0], split[1], split[2], split[3]));
                    }
                }
            }
            _listAdapterToday = new ListAdapter(this,list);
            ((ListView)findViewById(R.id.list_taken_today)).setAdapter(_listAdapterToday);
        }
        {
            ArrayList<TakenCalotiesData> list = new ArrayList<>();
            String[] dat = PrefUtils.read(this, PrefUtils.PREF_TAG_HISTORY);
            if(dat!=null) {
                for(String d : dat){
                    String[] split = d.split(DELM);
                    if(split!=null && split.length==4) {
                        list.add(new TakenCalotiesData(split[0],split[1],split[2],split[3]));
                    }
                }
            }
            _listAdapterHistory = new ListAdapter(this,list);
            ((ListView)findViewById(R.id.list_taken_history)).setAdapter(_listAdapterHistory);
        }
    }

    private void onClickAdd(){
        if(_editName.getText().toString().length()==0){
            Toast.makeText(this, "食べた物の名前を入力してください", Toast.LENGTH_LONG).show();
        } else if(_editCalorie.getText().toString().length()==0){
            Toast.makeText(this, "カロリーを入力してください", Toast.LENGTH_LONG).show();
        } else {
            addData();
        }
    }

    private void onClickSearch(){
        if(_editName.getText().toString().length()==0){
            Toast.makeText(this, "食べた物の名前を入力してください", Toast.LENGTH_LONG).show();
        } else {
            goSearch();
        }
    }

    private void goSearch(){
        Intent intent = new Intent(this, CalorieSearchActivity.class);
        intent.putExtra(CalorieSearchActivity.INTENT_TAG_SEARCH_KEYWORD, _editName.getText().toString());
        startActivityForResult(intent, Define.REQUEST_CODE_CALORIE_SEARCH);
    }

    private void addData(){
        String name = _editName.getText().toString();
        String calorie = _editCalorie.getText().toString();
        String when = (String) _spinnerWhen.getSelectedItem();
        String num = (String) _spinnerNum.getSelectedItem();
        String dat = name+DELM+calorie+DELM+when+DELM+num;
        PrefUtils.add(this, PrefUtils.PREF_TAG_HISTORY, dat);
        String tag = String.format(PrefUtils.PREF_TAG_TAKEN_DATA, _year, _month, _date);
        PrefUtils.add(this, tag, dat);
        rebuildList();
    }

    @Override
    public boolean onItemLongClick(AdapterView listView, View view, int position, long id) {
        new OkCancelDialog(this, "削除しますか？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(listView==_listViewToday){
                    String tag = String.format(PrefUtils.PREF_TAG_TAKEN_DATA,_year,_month,_date);
                    PrefUtils.remove(TakenCaloriesActivity.this, tag, position);
                }
                if(listView==_listViewHistory){
                    PrefUtils.remove(TakenCaloriesActivity.this, PrefUtils.PREF_TAG_HISTORY, position);
                }
                rebuildList();
            }
        }, null);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

}
