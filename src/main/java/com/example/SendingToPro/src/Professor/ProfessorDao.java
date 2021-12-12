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
        String getClassListQuery = "select pro.profName , l.subjectName,l.lecId from Professor as pro join Lecture as l on pro.profid=l.profId where pro.profName like concat('%',?,'%')  or l.subjectName like concat('%',?,'%');";
        Object[] getClassListParams = new Object[]{SearchKeyWord, SearchKeyWord};
        return this.jdbcTemplate.query(getClassListQuery,
            (rs,rowNum) -> new GetClassRes(
                rs.getString("profName"),
                rs.getString("subjectName")
            ),
            getClassListParams);
    }

    // GET 과목 정보 조회
    public GetClassDataRes getClassData(int lectureIndex) {
        String getClassDataQuery = "select P.profName, P.profPhoneNum , PE.email as profEmail ,L.subjectName , TB.bookImageUrl , TB.bookName, TB.author, TB.bookEdition, TB.bookPrice, LB.lecId, LB.bookId from Lecture as L join Professor as P on P.profid= L.profId join ProfessorEmail PE on P.profid = PE.profId join LectureBook LB on L.lecId = LB.lecId join TextBook TB on LB.bookId = TB.bookId where L.lecId=?;";
        return this.jdbcTemplate.queryForObject(getClassDataQuery,
            (rs,rowNum) -> new GetClassDataRes(
                rs.getString("profName"),
                rs.getString("profPhoneNum"),
                rs.getString("profEmail"),
                rs.getString("subjectName"),
                rs.getString("bookImageUrl"),
                rs.getString("bookName"),
                rs.getString("author"),
                rs.getString("bookEdition"),
                rs.getInt("bookPrice"),
                rs.getInt("lecId"),
                rs.getInt("bookId")
            ),
            lectureIndex);
    }

    // GET 특정 과목 게시판 조회
    public List<GetClassNoticeRes> getClassNoticeList(int lectureIndex) {
        String getClassNoticeListQuery = "select pro.profName, pb.boardId, pb.title,L.subjectName, date_format(pb.createdAt,'%Y-%m-%d') as createdAt from Professor as pro join ProfessorBoard as pb on pro.profid = pb.profId join Lecture L on pb.lecId = L.lecId where L.lecId = ? order by pb.createdAt;";
        return this.jdbcTemplate.query(getClassNoticeListQuery,
            (rs,rowNum) -> new GetClassNoticeRes(
                rs.getString("profName"),
                rs.getInt("boardId"),
                rs.getString("title"),
                rs.getString("subjectName"),
                rs.getTimestamp("createdAt")
            ),
            lectureIndex);
    }

    // POST 특정 과목 게시판 글쓰기  
    public int addClassNotice(PostClassNoticeNoEmailReq postClassNoticeNoEmailReq) {
        String addClassNoticeQuery = "insert into ProfessorBoard (profId,lecId,title,content) values (?,?,?,?);";
        Object[] addClassNoticeParams = new Object[]{postClassNoticeNoEmailReq.getProfId(), postClassNoticeNoEmailReq.getLecId(), 
        postClassNoticeNoEmailReq.getTitle(), postClassNoticeNoEmailReq.getContent()};
        this.jdbcTemplate.update(addClassNoticeQuery, addClassNoticeParams);

        String lastClassNoticeInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastClassNoticeInsertIdQuery, int.class);
    }

    // 특정 과목 게시판 이미지 넣기
    public void addClassNoticeImg(PostClassNoticeImgReq postClassNoticeImgReq) {
        String addClassNoticeImgQuery = "insert into BoardImage (boardId, ImageUrl) values (?,?);";
        Object[] addClassNoticeImgParams = new Object[]{postClassNoticeImgReq.getBoardId(), postClassNoticeImgReq.getImageUrl()};
        this.jdbcTemplate.update(addClassNoticeImgQuery, addClassNoticeImgParams);
    }

    // 삭제
    public int deleteClassNotice(DeleteClassNoticeReq deleteClassNoticeReq) {
        String deleteClassNoticeQuery = "update ProfessorBoard set status=1 where boardId=?;";
        String deleteClassNoticeImgQuery = "update BoardImage set status = 1 where boardId=?;";
        Object[] deleteClassNoticeParams = new Object[]{deleteClassNoticeReq.getBoardId()};

        this.jdbcTemplate.update(deleteClassNoticeImgQuery, deleteClassNoticeParams);
        return this.jdbcTemplate.update(deleteClassNoticeQuery,deleteClassNoticeParams); 
    }

    // GET 특정 게시글 정보 조회
    public GetClassNoticeDataRes getClassNoticeData(int boardIndex) {
        String getClassNoticeDataQuery = "select pro.profName, pb.boardId, pb.title, L.subjectName, pb.content, bi.ImageUrl from Professor as pro join ProfessorBoard as pb on pro.profid = pb.profId join Lecture as L on pb.lecId = L.lecId left join BoardImage as bi on bi.boardId = pb.boardId where pb.boardId = ? and pb.status=0 ;";
        return this.jdbcTemplate.queryForObject(getClassNoticeDataQuery, 
            (rs,rowNum) -> new GetClassNoticeDataRes(
                rs.getString("profName"),
                rs.getInt("boardId"),
                rs.getString("title"),
                rs.getString("subjectName"),
                rs.getString("content"),
                rs.getString("ImageUrl")
            ),
            boardIndex);
    }

}
