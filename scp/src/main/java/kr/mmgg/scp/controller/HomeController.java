package kr.mmgg.scp.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.mmgg.scp.dto.HomeViewDto;
import kr.mmgg.scp.dto.ProjectDetailReceiveTaskDto;
import kr.mmgg.scp.service.HomeServicelmpl;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

    private HomeServicelmpl homeServiceImpl;

    // SCP-100페이지
    @Transactional
    @RequestMapping(value = "/homeview/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<HomeViewDto>> homeview(@PathVariable Long userId) {
        List<HomeViewDto> hvList = homeServiceImpl.homeView(userId);
        return (hvList != null) ? ResponseEntity.status(HttpStatus.OK).body(hvList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
