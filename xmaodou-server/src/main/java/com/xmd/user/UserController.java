package com.xmd.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/31 下午2:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String me(Principal principal) {
        return principal.getName();
    }

}
