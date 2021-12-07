package com.example.SendingToPro.src.Book;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.config.BaseResponse;
import com.example.SendingToPro.src.Book.model.*;
import com.example.SendingToPro.src.Book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 로거
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// 보안
// import com.example.SendingToPro.utils.JwtService;

import java.util.List;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/SendingToPro/books")
public class BookController {
    
    @Autowired
    private final BookProvider bookProvider;
    @Autowired
    private final BookService bookService;
    
    public BookController(BookProvider bookProvider, BookService bookService) {
        this.bookProvider = bookProvider;
        this.bookService = bookService;
    }

    /**
     * 판매책 등록 API
     * [POST] /books/sell
     * @return BaseResponse<PostBookSellRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/sell")
    public BaseResponse<PostBookSellRes> addBookSell(@RequestBody PostBookSellReq postBookSellReq) {
        try {
            PostBookSellRes postBookSellRes = bookService.addBookSell(postBookSellReq);
            return new BaseResponse<>(postBookSellRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 강의 교재 조회 API
     * [GET] /books/lecture/:lectureIndex
     * @return BaseResponse<GetLectureBookDataRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/lecture/{lectureIndex}")
    public BaseResponse<GetLectureBookDataRes> getLectureBookData(@PathVariable("lectureIndex") int lectureIndex) {
        try {
            GetLectureBookDataRes getLectureBookDataRes = bookProvider.getLectureBookData(lectureIndex);
            return new BaseResponse<>(getLectureBookDataRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 판매 교재 조회 API
     * [GET] /books/sell/notice/lecture/:lectureIndex
     * @return BaseResponse<List<GetBookSellListRes>>
     */
    // Path-Variable
    @ResponseBody
    @GetMapping("/sell/notice/lecture/{lectureIndex}")
    public BaseResponse<List<GetBookSellListRes>> getBookSellList(@PathVariable("lectureIndex") int lectureIndex) {
        try {
            List<GetBookSellListRes> getBookSellListRes = bookProvider.getBookSellList(lectureIndex);
            return new BaseResponse<>(getBookSellListRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 판매책 상태 수정 API
     * [PATCH] /books/sell/notice/:sellIndex
     * @return BaseResponse<String>
     */
    // Path-Variable
    @ResponseBody
    @PatchMapping("/sell/notice/{sellIndex}")
    public BaseResponse<String> changeBookSellData(@PathVariable("sellIndex") int sellIndex, @RequestBody BookSellNotice bookSellNotice) {
        try {
            PatchBookSellStatusReq patchBookSellStatusReq = new PatchBookSellStatusReq(bookSellNotice.getBookState(), bookSellNotice.getSellPrice(),
            bookSellNotice.getSellState(), bookSellNotice.getContactState(), bookSellNotice.getSellorText(), sellIndex);
            bookService.changeBookSellData(patchBookSellStatusReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
        

    }


}
