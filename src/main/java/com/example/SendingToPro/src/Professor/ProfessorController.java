package com.example.SendingToPro.src.Professor;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.config.BaseResponse;
import com.example.SendingToPro.src.Professor.model.*;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.example.SendingToPro.src.Professor.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/SendingToPro/professors")
public class ProfessorController {
    
    @Autowired
    private final ProfessorProvider professorProvider;
    @Autowired
    private final ProfessorService professorService;

    public ProfessorController(ProfessorProvider professorProvider, ProfessorService professorService) {
        this.professorProvider = professorProvider;
        this.professorService = professorService;
    }

    /**
     * 과목 검색 API
     * [GET] /professors/lectures
     * @return BaseResponse<List<GetClassRes>>
     */
    // Query String   검색 트랜젝션 넣으면 좋을듯
    @ResponseBody
    @GetMapping("/lectures")
    public BaseResponse<List<GetClassRes>> getClassList(@RequestParam("searchKeyWord") String searchKeyWord) {
        try {
            List<GetClassRes> getClassRes = professorProvider.getClasList(searchKeyWord);
            return new BaseResponse<>(getClassRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 과목 정보 조회 API
     * [GET] /professors/lectures/data/:lectureIndex
     * @return BaseResponse<GetClassDataRes>
     */
    //Path-Variable
    @ResponseBody
    @GetMapping("/lectures/data/{lectureIndex}")
    public BaseResponse<GetClassDataRes> getClassData(@PathVariable("lectureIndex") int lectureIndex) {
        try {
            GetClassDataRes getClassDataRes = professorProvider.getClassData(lectureIndex);
            return new BaseResponse<>(getClassDataRes);
        } catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 특정 과목 게시판 조회 API
     * [GET] /professors/lectures/notices/:lectureIndex
     * @return BaseResponse<List<GetClassNoticeRes>>
     */
    // Path-Variable
    @ResponseBody
    @GetMapping("/lectures/notices/{lectureIndex}")
    public BaseResponse<List<GetClassNoticeRes>> getClassNoticeList(@PathVariable("lectureIndex") int lectureIndex) {
        try {
            List<GetClassNoticeRes> getClassNoticeRes = professorProvider.getClassNoticeList(lectureIndex);
            return new BaseResponse<>(getClassNoticeRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 특정 과목 게시판 글쓰기 API
     * [POST] /professors/lectures/notices
     * @return BaseResponse<PostClassNoticeRes>
     */
    @ResponseBody
    @PostMapping("/lectures/notices")
    public BaseResponse<PostClassNoticeRes> addClassNotice(@RequestBody PostClassNoticeReq postClassNoticeReq) {
        try {
            PostClassNoticeRes postClassNoticeRes = professorService.addClassNotice(postClassNoticeReq);
            return new BaseResponse<>(postClassNoticeRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 특정 과목 게시판 글 삭제 API
     * [PATCH] /professors/lectures/notices/delete/:boardIndex
     * @return BaseResponse<String>
     */
    //Path-Variable
    @ResponseBody
    @PatchMapping("/lectures/notices/delete/{boardIndex}")
    public BaseResponse<String> deleteClassNotcie(@PathVariable("boardIndex") int boardIndex) {
        try {
            DeleteClassNoticeReq deleteClassNoticeReq = new DeleteClassNoticeReq(boardIndex);
            professorService.deleteClassNotice(deleteClassNoticeReq);

            String result ="";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 특정 게시글 정보 조회 API
     * [GET] /professors/lectures/notices/data/:boardIndex
     * @return BaseResponse<GetClassNoticeDataRes>
     */
    // Path-Variable
    @ResponseBody
    @GetMapping("/lectures/notices/data/{boardIndex}")
    public BaseResponse<GetClassNoticeDataRes> getClassNoticeData(@PathVariable("boardIndex") int boardIndex) {
        try {
            GetClassNoticeDataRes getClassNoticeDataRes = professorProvider.getClassNoticeData(boardIndex);
            return new BaseResponse<>(getClassNoticeDataRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    } 
}
