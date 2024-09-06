package com.example.simulado;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simulado.adapter.AdapterList;
import com.example.simulado.helper.ModelDAO;
import com.example.simulado.model.Materias;

import java.util.ArrayList;
import java.util.List;

public class TelaMaterias extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Materias> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_materias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerMaterias);

        ModelDAO dao = new ModelDAO(TelaMaterias.this);

        list = dao.listar();

        recyclerView.setLayoutManager(new LinearLayoutManager(TelaMaterias.this));
        recyclerView.setHasFixedSize(true);

        AdapterList adapterList = new AdapterList(list, TelaMaterias.this);

        recyclerView.setAdapter(adapterList);

    }
}