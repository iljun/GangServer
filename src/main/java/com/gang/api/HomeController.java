package com.gang.api;

import com.gang.domain.GameInfo.GameInfo;
import com.gang.domain.GameInfo.GameInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iljun on 2017-06-01.
 */
@RestController
@RequestMapping("/api/Home")
@Slf4j
@Api(value = "메인화면 API", description = "메인화면 API", basePath= "/api/Home")
public class HomeController {

    @Autowired
    private GameInfoService gameInfoService;

    @ApiOperation(value = "오늘 게임정보 API", notes = "오늘 게임 정보 API")
    @RequestMapping(value = "gameInfo", method = RequestMethod.GET)
    public List<GameInfo> todayGameInfo() throws IOException {

            return gameInfoService.todayGame();




    }

    @RequestMapping(value = "exception")
    public void exception(){
        throw new IllegalArgumentException("Getting article problem.");
    }
}
