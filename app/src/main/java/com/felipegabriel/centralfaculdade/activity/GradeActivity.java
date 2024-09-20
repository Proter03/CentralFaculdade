package com.felipegabriel.centralfaculdade.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.adapter.GradeAdapter;
import com.felipegabriel.centralfaculdade.domain.dto.GradeDTO;
import com.felipegabriel.centralfaculdade.service.GradeCurricularService;

import java.util.List;

public class GradeActivity extends AppCompatActivity {

    GradeCurricularService gradeCurricularService;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        setSupportActionBar(findViewById(R.id.toolbar_grade));

        gradeCurricularService = new GradeCurricularService(this);

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
        List<GradeDTO> grade = gradeCurricularService.getGradePorIdCurso(1);

        recyclerView = findViewById(R.id.rcGrade);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        GradeAdapter adapter = new GradeAdapter(grade);
        recyclerView.setAdapter(adapter);
    }
}