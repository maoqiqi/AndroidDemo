package com.software.march.bean;

import java.io.Serializable;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 联系人
 * @date 2017/1/20
 */
public class ContactBean implements Serializable {

    private String name;

    private String number;

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}