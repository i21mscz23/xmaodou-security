package com.xmd.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/31 下午2:27
 */
@Controller
@RequestMapping({"/connect"})
public class ConnectController {

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    @Autowired
    private ConnectionRepository connectionRepository;


    @GetMapping
    public Map connectionStatus() {

        Map<String,Object> map = new HashMap<>();
        Map<String, List<Connection<?>>> connections = this.connectionRepository.findAllConnections();
        map.put("providerIds", this.connectionFactoryLocator.registeredProviderIds());
        map.put("connectionMap", connections);
        return map;
    }
}
