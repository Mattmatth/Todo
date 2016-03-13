package com.example.mattm.todo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context,0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_task,parent, false);
        }

        TaskViewHolder viewHolder = (TaskViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TaskViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checked);
            convertView.setTag(viewHolder);
        }


        Task task = getItem(position);
        viewHolder.text.setText(task.getText());
        viewHolder.checkBox.setChecked(task.isBool());

        return convertView;
    }

    private class TaskViewHolder{

        public TextView text;
        public CheckBox checkBox;

    }
}

