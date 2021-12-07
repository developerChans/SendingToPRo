package com.example.SendingToPro.src.Professor;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.src.Professor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@Service
public class ProfessorService {
    private final ProfessorDao professorDao;
    private final ProfessorProvider professorProvider;

    @Autowired
    public ProfessorService(ProfessorDao professorDao, ProfessorProvider professorProvider) {
        this.professorDao = professorDao;
        this.professorProvider = professorProvider;
    }
}
