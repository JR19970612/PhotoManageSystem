package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 笔记
 * Authentication（实现类有：UsernamePasswordAuthenticationToken...）顶级接口主要变量的意义
 * principal:       认证之前存放的是客户端提交密码的用户名，认证通过之后存放的是数据库内的用户详情信息
 * credentials:    存放客户端提交密码，该数据从客户端获取
 * authoritials:   经过认证后存放的权限信息
 * details:    主要客户端主机信息（包括IP和sessionId）
 * authenticated:   boolean类型表示该Authentication对象是否经过认证
 */

/**
 * @program: com.ydb.entity
 * @description: Person
 * @author: Jun
 * @create: 2018-12-11 15:45
 **/
public class Person implements Serializable, UserDetails {
    @JsonView(SuccessView.class)
    private Integer personId;

    @JsonView(SuccessView.class)
    private String personName;

    @JsonView(SuccessView.class)
    private String personPassword;

    @JsonView(SuccessView.class)
    private String openId;

    @JsonView(SuccessView.class)
    private String personAvatarUrl;

    //用户的所有角色
    private List<Role> roles;

    //用户有权访问的所有url，不持久化到数据库
    private Set<ResourecesUrl> urls = new HashSet<>();

    public Person() {
    }

    public Person(Integer personId, String personName, String personPassword, String openId, String personAvatarUrl, List<Role> roles, Set<ResourecesUrl> urls) {
        this.personId = personId;
        this.personName = personName;
        this.personPassword = personPassword;
        this.openId = openId;
        this.personAvatarUrl = personAvatarUrl;
        this.roles = roles;
        this.urls = urls;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword == null ? null : personPassword.trim();
    }

    public String getPersonAvatarUrl() {
        return personAvatarUrl;
    }

    public void setPersonAvatarUrl(String personAvatarUrl) {
        this.personAvatarUrl = personAvatarUrl == null ? null : personAvatarUrl.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Set<ResourecesUrl> getUrls() {
        for (Role role : this.roles) {
            for (ResourecesType resourecesType : role.getResourecesTypes()) {
                for (ResourecesUrl resourecesUrl : resourecesType.getResourecesUrls()) {
                    urls.add(resourecesUrl);
                }
            }
        }
        return urls;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return personPassword;
    }

    @Override
    public String getUsername() {
        return personName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personPassword='" + personPassword + '\'' +
                ", openId='" + openId + '\'' +
                ", personAvatarUrl='" + personAvatarUrl + '\'' +
                ", roles=" + roles +
                ", urls=" + urls +
                '}';
    }
}

