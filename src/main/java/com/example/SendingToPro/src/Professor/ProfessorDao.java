package com.example.SendingToPro.src.Professor;

import com.example.SendingToPro.src.Professor.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProfessorDao {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // GET 과목 검색
    public List<GetClassRes> getClassList(String SearchKeyWord) {
        String getClassListQuery = "select pro.profName , l.subjectName from Professor as pro join Lecture as l on pro.profid=l.profId where pro.profName like '최종무%' ;";
        return this.jdbcTemplate.query(getClassListQuery,
            (rs,rowNum) -> new GetClassRes(
                rs.getString("profName"),
                rs.getString("subjectName")
            ),
            SearchKeyWord);
    }

    // GET 과목 정보 조회
    public GetClassDataRes getClassData(int lectureIndex) {
        String getClassDataQuery = "select profName,proE.email ,profPhoneNum,l.subjectName from Professor as pro join Lecture as l on pro.profid=l.profId join ProfessorEmail as proE on pro.profid=proE.profId where pro.profName like '%최종무%'  or l.subjectName like '%시스템%';";
        return this.jdbcTemplate.queryForObject(getClassDataQuery,
            (rs,rowNum) -> new GetClassDataRes(
                rs.getString("profName"),
                rs.getString("email"),
                rs.getString("profPhoneNum"),
                rs.getString("subjectName")
            ),
            lectureIndex);
    }

    // GET 특정 과목 게시판 조회
    public List<GetClassNoticeRes> getClassNoticeList(int lectureIndex) {
        String getClassNoticeListQuery = "select pro.profName, pb.boardId, pb.title, L.subjectName, pb.content, bi.ImageUrl from Professor as pro join ProfessorBoard as pb on pro.profid = pb.profId join Lecture as L on pb.lecId = L.lecId left join BoardImage as bi on bi.boardId = pb.boardId where L.lecId = ? order by pb.createdAt;";
        return this.jdbcTemplate.query(getClassNoticeListQuery,
            (rs,rowNum) -> new GetClassNoticeRes(
                rs.getString("profName"),
                rs.getInt("boardId"),
                rs.getString("title"),
                rs.getString("subjectName"),
                rs.getString("content"),
                rs.getString("ImageUrl")
            ),
            lectureIndex);
    }

    // POST 특정 과목 게시판 글쓰기  

    // GET 특정 게시글 정보 조회
    public GetClassNoticeDataRes getClassNoticeData(int boardIndex) {
        String getClassNoticeDataQuery = "select pro.profName, pb.boardId, pb.title, L.subjectName, pb.content, bi.ImageUrl, date_format(pb.createdAt,'%Y-%m-%d') from Professor as pro join ProfessorBoard as pb on pro.profid = pb.profId join Lecture as L on pb.lecId = L.lecId left join BoardImage as bi on bi.boardId = pb.boardId where pb.boardId = ?;";
        return this.jdbcTemplate.queryForObject(getClassNoticeDataQuery, 
            (rs,rowNum) -> new GetClassNoticeDataRes(
                rs.getString("profName"),
                rs.getInt("boardId"),
                rs.getString("title"),
                rs.getString("subjectName"),
                rs.getString("content"),
                rs.getString("ImageUrl"),
                rs.getString("createdAt")
            ),
            boardIndex);
    }

    






}
