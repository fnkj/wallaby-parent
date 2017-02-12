package com.shaobing.wallaby.core.ognl.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version $$: Humen.java v 0.1 2016/11/29 17:21 luyb Exp $$
 */
class Humen {
    private String      name;
    private String      sex;
    private int         age;
    private List<Humen> friends = new ArrayList<Humen>();

    public Humen() {

    }

    public Humen(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Humen> getFriends() {
        return friends;
    }

    public void setFriends(List<Humen> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Humen [name=" + name + ", sex=" + sex + ", age=" + age + "]";
    }
}