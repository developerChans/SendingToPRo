package com.example.SendingToPro.src.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import com.example.SendingToPro.src.Book.model.*;

import java.util.List;

@Repository
public class BookDao {
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 판매책 등록
    public int addBookSell(PostBookSellReq postBookSellReq) {
        String addBookSellQuery = "insert into Sell (bookId, studId, bookState, sellPrice, sellState, sellorText) values (?,?,?,?,?,?);";
        Object[] addBookSellParams = new Object[]{postBookSellReq.getBookId(), postBookSellReq.getStudId(),
            postBookSellReq.getBookState(), postBookSellReq.getSellPrice(), postBookSellReq.getSellState(), postBookSellReq.getSellorText()};
        this.jdbcTemplate.update(addBookSellQuery, addBookSellParams);
        
        String lastBookSellInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastBookSellInsertIdQuery, int.class);
    }

    // 강의 교재 조회
    public GetLectureBookDataRes getLectureBookData(int lectureIndex) {
        String getLectureBookDataQuery = "SELECT TextBook.bookId,TextBook.bookName,TextBook.author,TextBook.bookPrice,TextBook.bookEdition, TextBook.bookImageUrl FROM LectureBook JOIN TextBook ON TextBook.bookId = LectureBook.bookId WHERE LectureBook.lecId = ?;";
        return this.jdbcTemplate.queryForObject(getLectureBookDataQuery, 
            (rs,rowNum) -> new GetLectureBookDataRes(
                rs.getInt("bookId"),
                rs.getString("bookName"),
                rs.getString("author"),
                rs.getInt("bookPrice"),
                rs.getString("bookEdition"),
                rs.getString("bookImageUrl")),
                lectureIndex);
    }

    // 판매 교재 조회
    public List<GetBookSellListRes> getBookSellList(int lectureIndex) {
        String getBookSellListQuery = "";
        return this.jdbcTemplate.query(getBookSellListQuery,
            (rs,rowNum) -> new GetBookSellListRes(
                rs.getInt("studId"),
                rs.getString("bookName"),
                rs.getString("author"),
                rs.getString("bookEdition"),
                rs.getInt("bookPrice"),
                rs.getString("bookState"),
                rs.getString("contact"),
                rs.getString("sellorText"),
                rs.getString("sellState")
            ),
            lectureIndex);
    }
    
    // 판매책 상태 수정
    public int changeBookSellData(PatchBookSellStatusReq patchBookSellStatusReq) {
        String changeBookSellDataQuery = "UPDATE Sell SET bookState = ?, sellPrice = ?, sellState = ?, contactState = ?, sellorText =? WHERE sellId = ?;";
        Object[] changeBookSellDataParams = new Object[]{
            patchBookSellStatusReq.getBookState(), patchBookSellStatusReq.getSellPrice(),
            patchBookSellStatusReq.getSellState(), patchBookSellStatusReq.getContactState(),
            patchBookSellStatusReq.getSellorText(), patchBookSellStatusReq.getSellId()
        };
        return this.jdbcTemplate.update(changeBookSellDataQuery, changeBookSellDataParams);
    }



}
