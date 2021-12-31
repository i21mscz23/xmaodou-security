package com.xmd.service;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/24 下午1:33
 */
public class CacheClientDetailsService extends JdbcClientDetailsService {

    public CacheClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     *
     * @param clientId
     * @return
     * @throws InvalidClientException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
