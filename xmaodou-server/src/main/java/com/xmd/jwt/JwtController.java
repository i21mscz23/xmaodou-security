package com.xmd.jwt;

import com.xmd.annotation.AnonymousAccess;
import com.xmd.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/31 上午10:22
 */
@RestController
public class JwtController {

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @GetMapping("/jwt")
    public ServerResponse jwt(){
        return ServerResponse.createBySuccessData("jwt");
    }

    @GetMapping("/annotation")
    @AnonymousAccess
    public ServerResponse annotation(){
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/me")
    public ServerResponse me(Authentication user, HttpServletRequest request){
        return ServerResponse.createBySuccessData(user);
    }

}
