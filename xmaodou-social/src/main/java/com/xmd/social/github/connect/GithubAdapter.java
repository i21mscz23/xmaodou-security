package com.xmd.social.github.connect;

import com.xmd.social.github.api.Github;
import com.xmd.social.github.api.GithubInfo;
import com.xmd.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 上午9:51
 */
public class GithubAdapter implements ApiAdapter<Github> {
    @Override
    public boolean test(Github api) {
        return true;
    }

    @Override
    public void setConnectionValues(Github api, ConnectionValues values) {
        GithubInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getLogin());
        values.setImageUrl(userInfo.getAvatar_url());
        values.setProfileUrl(null);
        values.setProviderUserId(String.valueOf(userInfo.getId()));
    }

    @Override
    public UserProfile fetchUserProfile(Github api) {
        return null;
    }

    @Override
    public void updateStatus(Github api, String message) {

    }
}
