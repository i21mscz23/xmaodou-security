package com.xmd.jwt;

import com.xmd.response.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/31 上午10:22
 */
@RestController
public class JwtController {


    @GetMapping("/jwt")
    public ServerResponse jwt(){
        return ServerResponse.createBySuccessData("jwt");
    }
}
