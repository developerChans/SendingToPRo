package com.example.SendingToPro.src.Student;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.src.Student.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.SendingToPro.config.secret.Secret;
import com.example.SendingToPro.utils.AES128;
import com.example.SendingToPro.utils.JwtService;

import javax.sql.DataSource;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@Service
public class StudentService {
    
    private final StudentDao studentDao;
    private final StudentProvider studentProvider;
    private final JwtService jwtService;
    
    @Autowired
    public StudentService(StudentDao studentDao, StudentProvider studentProvider, JwtService jwtService) {
        this.studentDao = studentDao;
        this.studentProvider = studentProvider;
        this.jwtService = jwtService;
    }

    public void addLectureToMyPage(PostClassReq postClassReq) throws BaseException {
        try {
            int result = studentDao.addLectureToMyPage(postClassReq);
            if(result == 0) {
                throw new BaseException(POST_CLASS_ERROR);
            }
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void changeMailForm(PatchMailFormReq patchMailFormReq) throws BaseException {
        try {
            int result = studentDao.changeMailForm(patchMailFormReq);
            if(result == 0) {
                throw new BaseException(MODIFY_FAIL_MAIL_FORM);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostStudentRes createStudent(PostStudentReq postStudentReq) throws BaseException {
        String pwd;
        try {
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postStudentReq.getPassword());
            postStudentReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            int studentIndex = studentDao.createStudent(postStudentReq);
            String jwt = jwtService.createJwt(studentIndex);
            return new PostStudentRes(jwt, studentIndex);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
        Student student = studentDao.getPwd(postLoginReq);
        String pwd;
        try {
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(student.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(postLoginReq.getPassword().equals(pwd)) {
            int studentIndex = studentDao.getPwd(postLoginReq).getStudId();
            String jwt = jwtService.createJwt(studentIndex);
            return new PostLoginRes(jwt, studentIndex);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }



}
