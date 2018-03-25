package net.dixq.caloriemanager.takencalories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.dixq.caloriemanager.R;

import java.util.ArrayList;

/**
 * Created by dixq on 2018/03/25.
 */

public class ListAdapter extends BaseAdapter {

    private ArrayList<TakenCalotiesData> _list;
    private Context _context;
    private LayoutInflater _layoutInflater;

    ListAdapter(Context context, ArrayList<TakenCalotiesData> list){
        _context = context;
        _list = list;
        _layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int i) {
        return _list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = _layoutInflater.inflate(R.layout.row_list_taken_calories,parent,false);
        }
        ((TextView)convertView.findViewById(R.id.txt_name)   ).setText(_list.get(position).name);
        ((TextView)convertView.findViewById(R.id.txt_calorie)).setText(_list.get(position).calorie);
        ((TextView)convertView.findViewById(R.id.txt_when)   ).setText(_list.get(position).when);
        ((TextView)convertView.findViewById(R.id.txt_num)    ).setText(_list.get(position).num);
        return convertView;
    }
}
