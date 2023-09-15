package leave.meet.playbours.manage.login.dto;

import java.io.Serializable;

/**
 * 로그인용 임시 dto
 **/
public class Member implements Serializable {
    private String userId;

    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}