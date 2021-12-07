package com.example.SendingToPro.src.Student;

import com.example.SendingToPro.src.Student.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class StudentDao {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // POST 강의 추가 DAO
    public int addLectureToMyPage(PostClassReq postClassReq) {
        String addLectureToMyPageQuery = "INSERT INTO CourseTake(lecId,studId) VALUES (?,?);";
        Object[] addLectureToMyPageParams = new Object[]{postClassReq.getLecId(), postClassReq.getStudId()};
        this.jdbcTemplate.update(addLectureToMyPageQuery, addLectureToMyPageParams);

        String lastLectureInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastLectureInsertIdQuery, int.class);
    }

    // GET 마이페이지 조회
    public List<GetStudentLectureRes> getStudentLectureList(int studentIndex) {
        String getStudentLectureListQuery = "SELECT Lecture.lecId,Professor.profName,Lecture.subjectName FROM Lecture JOIN Professor ON Lecture.profId=Professor.profId JOIN CourseTake ON CourseTake.lecId=Lecture.lecId WHERE CourseTake.studId = ?;";
        return this.jdbcTemplate.query(getStudentLectureListQuery,
            (rs,rowNum) -> new GetStudentLectureRes(
                rs.getInt("lecId"),
                rs.getString("profName"),
                rs.getString("subjectName")
            ),
            studentIndex);
    }

    // 양식 조회
    public GetMailFormRes getMailForm(GetMailFormReq getMailFormReq) {
        String getMailFormQuery = "SELECT emailForm FROM CourseTake WHERE lecId = ? and studId = ?;";
        Object[] getMailFormParams = new Object[]{getMailFormReq.getLecId(), getMailFormReq.getStudId()};
        return this.jdbcTemplate.queryForObject(getMailFormQuery, 
            (rs,rowNum) -> new GetMailFormRes(
                rs.getString("emailForm")
            ),getMailFormParams);
    }

    // 양식 추가
    public int addMailForm(PostMailFormReq postMailFormReq) {
        String addMailFormQuery = "INSERT INTO CourseTake(lecId, studId, emailForm) VALUES (?,?,?);";
        Object[] addMailFormParams = new Object[]{postMailFormReq.getLecId(), postMailFormReq.getStudId(), postMailFormReq.getEmailForm()};
        this.jdbcTemplate.update(addMailFormQuery, addMailFormParams);

        String lastFormInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastFormInsertIdQuery, int.class);

    }

    // 양식 수정
    public int changeMailForm(PatchMailFormReq patchMailFormReq) {
        String changeMailFormQuery = "UPDATE CourseTake SET emailForm = ? WHERE lecId = ? and studId = ?;";
        Object[] changeMailFormParams = new Object[]{patchMailFormReq.getEmailForm(), patchMailFormReq.getLecId(), patchMailFormReq.getStudId()};
        
        return this.jdbcTemplate.update(changeMailFormQuery, changeMailFormParams);
        
    }

    // 로그인
    //public Student 

}
