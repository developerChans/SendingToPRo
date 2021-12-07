package com.example.SendingToPro.src.Professor;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.src.Professor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@Service
public class ProfessorProvider {
    private final ProfessorDao professorDao;

    @Autowired
    public ProfessorProvider(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    public List<GetClassRes> getClasList(String SearchKeyWord) throws BaseException {
        try {
            List<GetClassRes> getClassRes = professorDao.getClassList(SearchKeyWord);
            return getClassRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetClassDataRes getClassData(int lectureIndex) throws BaseException {
        try {
            GetClassDataRes getClassDataRes = professorDao.getClassData(lectureIndex);
            return getClassDataRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetClassNoticeRes> getClassNoticeList(int lectureIndex) throws BaseException {
        try {
            List<GetClassNoticeRes> getClassNoticeRes = professorDao.getClassNoticeList(lectureIndex);
            return getClassNoticeRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetClassNoticeDataRes getClassNoticeData(int boardIndex) throws BaseException {
        try {
            GetClassNoticeDataRes getClassNoticeDataRes = professorDao.getClassNoticeData(boardIndex);
            return getClassNoticeDataRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
