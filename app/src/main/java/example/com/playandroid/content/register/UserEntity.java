package example.com.playandroid.content.register;

import android.databinding.ViewDataBinding;

import java.util.List;

import example.com.playandroid.base.BaseEntity;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:24
 */
public class UserEntity extends BaseEntity {
    /**
     * collectIds : []
     * email :
     * icon :
     * id : 9987
     * password : 123456
     * token_pass :
     * type : 0
     * username : dnf123456
     */

    private String email;
    private String icon;
    private int id;
    private String password;
    private String token_pass;
    private int type;
    private String username;
    private List<String> collectIds;
    private String repassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken_pass() {
        return token_pass;
    }

    public void setToken_pass(String token_pass) {
        this.token_pass = token_pass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<String> collectIds) {
        this.collectIds = collectIds;
    }


    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }


    @Override
    public int getViewType() {
        return 0;
    }

    @Override
    public ViewDataBinding getDataBinding() {
        return null;
    }

    @Override
    public void setDataBinding(ViewDataBinding viewDataBinding) {

    }
}
