package com.software.march.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description
 * @date 2016/12/27
 */
public class PersonBean implements Parcelable {

    private int id;

    private String userName;

    private String nickName;

    private int age;

    public PersonBean() {
        super();
    }

    public PersonBean(String userName, String nickName, int age) {
        this.userName = userName;
        this.nickName = nickName;
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

    @Override
    public int describeContents() {
        return 0;
    }

    // 将当前对象的属性数据打成包: 写到包对象中
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(userName);
        parcel.writeString(nickName);
        parcel.writeInt(age);
    }

    // 添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Creator<PersonBean> CREATOR = new Creator<PersonBean>() {

        // 解包: 读取包中的数据并封装成对象
        @Override
        public PersonBean createFromParcel(Parcel in) {
            return new PersonBean(in);
        }

        // 返回一个指定大小的对象容器
        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };

    protected PersonBean(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        nickName = in.readString();
        age = in.readInt();
    }
}