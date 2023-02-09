package com.xmd.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * ConnectController 请求视图时404，通过此处理直接响应对应内容
 */
@Component("connect/status")
public class ConnectionStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) map.get("connectionMap");

        Map<String,Boolean> result = new HashMap<>();
        for(Map.Entry<String,List<Connection<?>>> m: connections.entrySet()){
            String key = m.getKey();
            List<Connection<?>> value = m.getValue();
            result.put(key, CollectionUtils.isEmpty(value)?false:true);
        }

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
