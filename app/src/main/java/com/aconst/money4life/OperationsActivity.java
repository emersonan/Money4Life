package com.aconst.money4life;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.aconst.money4life.adapters.OperationsAdapter;
import com.aconst.money4life.model.Operations;

import io.realm.Realm;
import io.realm.Sort;

public class OperationsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.operations_list);
        setUpRecyclerView();

        FloatingActionButton fabTariff = findViewById(R.id.fabOperations);
        fabTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OperationsActivity.this,
                        OperationsAddEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView() {
        OperationsAdapter adapter = new OperationsAdapter(this, realm.where(Operations.class).findAll()
                .sort("name", Sort.ASCENDING));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        realm.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ToDo: edit operation
    }
}
