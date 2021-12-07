package com.example.SendingToPro.src.Professor;

import com.example.SendingToPro.config.BaseException;
import com.example.SendingToPro.config.BaseResponse;
import com.example.SendingToPro.src.Professor.model.*;
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
     * [GET] /professors/lecture
     * @return BaseResponse<List<GetClassRes>>
     */
    // Query String   검색 트랜젝션 넣으면 좋을듯
    @ResponseBody
    @PostMapping("/lecture")
    public BaseResponse<List<GetClassRes>> getClassList(@RequestParam("searchKeyWord") String searchKeyWord) {
        try {
            List<GetClassRes> getClassRes = professorProvider.getClasList(searchKeyWord);
            return new BaseResponse<>(getClassRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
