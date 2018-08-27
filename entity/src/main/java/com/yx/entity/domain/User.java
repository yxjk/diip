package com.yx.entity.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity(name = "DI_USER")
public class User {
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String userId;

    @Column(name = "USER_LOGIN")
    private String userLogin;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "LOGIN_TIME")
    private Timestamp loginTime;

    @Column(name = "STATUS")//S-正常，F-失效，L-锁定
    private String status;

    @Column(name = "USER_DESC")
    private String userDesc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(loginTime, user.loginTime) &&
                Objects.equals(status, user.status) &&
                Objects.equals(userDesc, user.userDesc) &&
                Objects.equals(userLogin, user.userLogin) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPwd, user.userPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, loginTime, status, userDesc, userLogin, userName, userPwd);
    }
}
