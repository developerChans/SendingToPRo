package com.example.SendingToPro.src.Book;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.src.Book.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 로거
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// 보안
// import com.example.SendingToPro.config.secret.Secret;
// import com.example.SendingToPro.utils.AES128;
// import com.example.SendingToPro.utils.JwtService;

import java.util.List;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@Service
public class BookProvider {
    private final BookDao bookDao;
    
    @Autowired
    public BookProvider(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public GetLectureBookDataRes getLectureBookData(int lectureIndex) throws BaseException {
        try {
            GetLectureBookDataRes getLectureBookDataRes = bookDao.getLectureBookData(lectureIndex);
            return getLectureBookDataRes;
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetBookSellListRes> getBookSellList(int lectureIndex) throws BaseException {
        try{
            List<GetBookSellListRes> getBookSellListRes = bookDao.getBookSellList(lectureIndex);
            return getBookSellListRes;
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
