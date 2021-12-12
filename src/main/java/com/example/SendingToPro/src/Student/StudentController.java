package com.example.SendingToPro.src.Student;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.config.BaseResponse;
import com.example.SendingToPro.src.Student.model.*;
import com.example.SendingToPro.src.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.SendingToPro.utils.JwtService;
import com.fasterxml.jackson.databind.JsonSerializable.Base;

import java.util.List;

import static com.example.SendingToPro.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/SendingToPro/students")
public class StudentController {
    
    @Autowired
    private final StudentProvider studentProvider;
    @Autowired
    private final StudentService studentService;
    @Autowired
    private final JwtService jwtService;

    public StudentController(StudentProvider studentProvider, StudentService studentService, JwtService jwtService) {
        this.studentProvider = studentProvider;
        this.studentService = studentService;
        this.jwtService = jwtService;
    }

    /**
     * 마이페이지 강의 추가 API
     * [POST] /students/mypage/lecture
     * @return BaseResponse<String>
     */
    // Body
    @ResponseBody
    @PostMapping("/mypage/lecture")
    public BaseResponse<String> addLectureToMyPage(@RequestBody PostClassReq postClassReq) {
        try {
            studentService.addLectureToMyPage(postClassReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 마이페이지 강의목록 조회 API
     * [GET] /students/mypage/lectures/:studentIndex
     * @return BaseResponse<List<GetStudentLectureRes>>
     */
    // Path-Variable
    @ResponseBody
    @GetMapping("/mypage/lectures/{studentIndex}")
    public BaseResponse<List<GetStudentLectureRes>> getStudentLectureList(@PathVariable("studentIndex") int studentIndex) {
        try {
            List<GetStudentLectureRes> getStudentLectureRes = studentProvider.getStudentLectureList(studentIndex);
            return new BaseResponse<>(getStudentLectureRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 양식 조회 API
     * [GET] /students/mypage/lectures/mailForms/:studentIndex/:lectureIndex
     * @return BaseResponse<GetMailFormRes>
     */
    //Path-Variable
    @ResponseBody
    @GetMapping("/mypage/lectures/mailForms/{studentIndex}/{lectureIndex}")
    public BaseResponse<GetMailFormRes> getMailForm(@PathVariable("studentIndex") int studentIndex, @PathVariable("lectureIndex") int lectureIndex) {
        try {
            GetMailFormReq getMailFormReq = new GetMailFormReq(lectureIndex, studentIndex);
            GetMailFormRes getMailFormRes = studentProvider.getMailForm(getMailFormReq);
            return new BaseResponse<>(getMailFormRes);
        } catch(BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 양식 수정 API
     * [PATCH] /students/mypage/lectures/mailForms/patch
     * @return BaseResponse<String>
     */
    // Body
    @ResponseBody
    @PatchMapping("/mypage/lectures/mailForms/patch")
    public BaseResponse<String> changeMailForm(@RequestBody PatchMailFormReq patchMailFormReq) {
        try {
            studentService.changeMailForm(patchMailFormReq);
            
            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원가입 API
     * [POST] /students
     * @return BaseResponse<PostStudentRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostStudentRes> createStudent(@RequestBody PostStudentReq postStudentReq) {
        try {
            PostStudentRes postStudentRes = studentService.createStudent(postStudentReq);
            return new BaseResponse<>(postStudentRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 로그인 API
     * [POST] /students/login
     * @return BaseResponse<PostLoginRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        try {
            PostLoginRes postLoginRes = studentService.login(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
