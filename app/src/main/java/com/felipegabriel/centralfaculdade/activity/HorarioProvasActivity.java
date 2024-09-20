package com.felipegabriel.centralfaculdade.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.adapter.ProvaAdapter;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioProvaDTO;
import com.felipegabriel.centralfaculdade.service.HorarioProvaService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HorarioProvasActivity extends AppCompatActivity {

    HorarioProvaService horarioProvaService;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_provas);
        setSupportActionBar(findViewById(R.id.toolbar_horarioProvas));

        horarioProvaService = new HorarioProvaService(this);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void init() {
        List<HorarioProvaDTO> provas = horarioProvaService.getHorariosProva(1, 8).stream().sorted(Comparator.comparing(HorarioProvaDTO::getDataProva)).collect(Collectors.toList());

        recyclerView = findViewById(R.id.rcProvas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProvaAdapter adapter = new ProvaAdapter(provas);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}