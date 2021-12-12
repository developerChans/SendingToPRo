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
        String getBookSellListQuery = "SELECT Sell.sellId, Sell.studId, case when Sell.contactState = 1 then Student.studPhoneNum else Student.studEmail end as Contact ,case when Sell.bookState = 0 then '나쁨' when Sell.bookState = 1 then '보통' else '좋음' end as BookState ,Sell.sellPrice ,case when Sell.sellState = 0 then '판매중' when Sell.sellState = 1 then '판매취소' else '판매완료' end as SellState, Sell.sellPrice, Sell.sellorText, TB.publisher, TB.bookName, TB.author, TB.bookEdition FROM Sell JOIN Student ON Student.studId = Sell.studId join TextBook TB on Sell.bookId = TB.bookId join LectureBook as LB on TB.bookId=LB.bookId WHERE LB.lecId = ?;";
        return this.jdbcTemplate.query(getBookSellListQuery,
            (rs,rowNum) -> new GetBookSellListRes(
                rs.getInt("sellId"),
                rs.getInt("studId"),
                rs.getString("contact"),
                rs.getString("bookState"),
                rs.getString("sellState"),
                rs.getInt("sellPrice"),
                rs.getString("sellorText"),
                rs.getString("publisher"),
                rs.getString("bookName"),
                rs.getString("author"),
                rs.getString("bookEdition")     
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
