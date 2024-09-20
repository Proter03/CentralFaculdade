package com.felipegabriel.centralfaculdade.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.adapter.HorariosAdapter;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioAulaDTO;
import com.felipegabriel.centralfaculdade.service.HorarioAulaService;

import java.util.List;

public class HorariosAulaActivity extends AppCompatActivity {

    HorarioAulaService horarioAulaService;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_aula);
        setSupportActionBar(findViewById(R.id.toolbar_horariosAula));

        horarioAulaService = new HorarioAulaService(this);

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
        List<HorarioAulaDTO> horarios = horarioAulaService.getHorariosAula(8, 1);

        recyclerView = findViewById(R.id.rcHorarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HorariosAdapter adapter = new HorariosAdapter(horarios);
        recyclerView.setAdapter(adapter);
    }
}