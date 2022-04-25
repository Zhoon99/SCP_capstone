package kr.mmgg.scp.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.response.HomeViewDto;
import kr.mmgg.scp.service.HomeServicelmpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HomeController {

    private HomeServicelmpl homeServiceImpl;

    // SCP-100페이지
    @Transactional
    @RequestMapping(value = "/homeview/{userId}", method = RequestMethod.GET)
    public ResultDto<List<HomeViewDto>> homeview(@PathVariable Long userId) {
        return homeServiceImpl.homeView(userId);
    }

}
