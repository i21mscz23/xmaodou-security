package com.xmd.social.weibo.connect;


import com.xmd.social.weibo.api.Weibo;
import com.xmd.social.weibo.api.WeiboUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/27 上午9:06
 */
public class WeiboAdapter implements ApiAdapter<Weibo> {




    @Override
    public boolean test(Weibo api) {
        return true;
    }

    @Override
    public void setConnectionValues(Weibo api, ConnectionValues values) {
        WeiboUserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getScreen_name());
        values.setImageUrl(userInfo.getAvatar_large());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getIdstr());
    }

    @Override
    public UserProfile fetchUserProfile(Weibo api) {
        return null;
    }

    @Override
    public void updateStatus(Weibo api, String message) {

    }
}
