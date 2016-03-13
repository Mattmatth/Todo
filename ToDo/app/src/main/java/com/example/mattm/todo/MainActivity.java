package com.example.mattm.todo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    List<Task> tasks;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mListView = (ListView) findViewById(R.id.mylistview);
        tasks= new ArrayList<Task>();
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        ShowListtasks();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(onAddingListener());

    }


            private View.OnClickListener onAddingListener() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.add_task);
                        dialog.setTitle("Add a Task");
                        dialog.setCancelable(false);
                        EditText tasktext = (EditText) dialog.findViewById(R.id.tasktext);
                        View btnAdd = dialog.findViewById(R.id.btn_ok);
                        View btnCancel = dialog.findViewById(R.id.btn_cancel);


                        btnAdd.setOnClickListener(onConfirmListener(tasktext, dialog));
                        btnCancel.setOnClickListener(onCancelListener(dialog));

                        dialog.show();
                    }
                };
            }

            private View.OnClickListener onConfirmListener(final EditText tasktext, final Dialog dialog) {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Task task = new Task(tasktext.getText().toString().trim(), false);
                        tasks.add(task);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                };
            }

            private View.OnClickListener onCancelListener(final Dialog dialog) {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                };
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }


            private List<Task> genererTasks() {

                tasks.add(new Task("Florent", false));
                tasks.add(new Task("Kevin", false));
                tasks.add(new Task("Logan", false));
                tasks.add(new Task("Matthieu", true));
                tasks.add(new Task("Willy", false));
                return tasks;
            }

            private void ShowListtasks() {
                tasks = genererTasks();
                adapter = new TaskAdapter(MainActivity.this, tasks);
                mListView.setAdapter(adapter);
            }

        }
