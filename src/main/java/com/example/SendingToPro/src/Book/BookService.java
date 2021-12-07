package com.example.SendingToPro.src.Book;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.src.Book.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

// 로거
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// 보안
// import com.example.SendingToPro.config.secret.Secret;
// import com.example.SendingToPro.utils.AES128;
// import com.example.SendingToPro.utils.JwtService;

import javax.sql.DataSource;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@Service
public class BookService {
    private final BookDao bookDao;
    private final BookProvider bookProvider;
    
    @Autowired
    public BookService(BookDao bookDao, BookProvider bookProvider) {
        this.bookDao = bookDao;
        this.bookProvider = bookProvider;
    }

    public PostBookSellRes addBookSell(PostBookSellReq postBookSellReq) throws BaseException {
        try {
            int bookSellIndex = bookDao.addBookSell(postBookSellReq);
            return new PostBookSellRes(bookSellIndex);
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void changeBookSellData(PatchBookSellStatusReq patchBookSellStatusReq) throws BaseException {
        try {
            int result = bookDao.changeBookSellData(patchBookSellStatusReq);
            if(result == 0) {
                throw new BaseException(MODIFY_FAIL_SELLDATA);
            }
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
