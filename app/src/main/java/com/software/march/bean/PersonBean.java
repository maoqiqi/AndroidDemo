package com.software.march.bean;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2016/12/27
 */
public class PersonBean {

    private int id;

    private String userName;

    private String nickName;

    private int age;

    public PersonBean() {
        super();
    }

    public PersonBean(String nickName, String userName, int age) {
        this.nickName = nickName;
        this.userName = userName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "age=" + age +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}