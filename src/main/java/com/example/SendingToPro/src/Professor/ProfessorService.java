package com.example.SendingToPro.src.Professor;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.config.BaseResponseStatus;
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

    public PostClassNoticeRes addClassNotice(PostClassNoticeReq postClassNoticeReq) throws BaseException {
        try {
            PostClassNoticeNoEmailReq postClassNoticeNoEmailReq = new PostClassNoticeNoEmailReq(postClassNoticeReq.getProfId(), postClassNoticeReq.getLecId(), postClassNoticeReq.getTitle(), postClassNoticeReq.getContent());
            int classNoticeIndex = professorDao.addClassNotice(postClassNoticeNoEmailReq);
            if(postClassNoticeReq.getImageUrl() == null) {
                return new PostClassNoticeRes(classNoticeIndex);
            }
            PostClassNoticeImgReq postClassNoticeImgReq = new PostClassNoticeImgReq(classNoticeIndex, postClassNoticeReq.getImageUrl());
            professorDao.addClassNoticeImg(postClassNoticeImgReq);
            return new PostClassNoticeRes(classNoticeIndex);
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteClassNotice(DeleteClassNoticeReq deleteClassNoticeReq) throws BaseException {
        try {
            int result = professorDao.deleteClassNotice(deleteClassNoticeReq);
            if(result ==0) {
                throw new BaseException(BaseResponseStatus.MODIFY_FAIL_CLASS_NOTICE);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
