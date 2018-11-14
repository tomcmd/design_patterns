package com.cmd.patterns.observer;

/**
 * 具体的目标对象，负责把有关状态存入到相应的观察者对象中
 * @author: tomcmd
 * @create: 2018/11/14 11:30 AM
 */
public class ConcreteSubject extends Subject {

    //目标对象的状态
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
        this.notifyObservers();
    }
}
