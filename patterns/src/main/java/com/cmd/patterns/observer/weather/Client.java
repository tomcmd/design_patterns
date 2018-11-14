package com.cmd.patterns.observer.weather;

/**
 * @author: tomcmd
 * @create: 2018/11/14 1:25 PM
 */
public class Client {
    public static void main(String[] args){
        //1.创建目标
        ConcreteWeatherSubject weatherSubject = new ConcreteWeatherSubject();
        //2.创建观察者
        ConcreteObserver observerGirl = new ConcreteObserver("黄明女朋友","约会去！");
        ConcreteObserver observerMun = new ConcreteObserver("老妈","是一个购物的好日子！");

        //3.注册观察者
        weatherSubject.attach(observerGirl);
        weatherSubject.attach(observerMun);
        //4.目标发布天气
        weatherSubject.setWeatherContent("明天天气晴朗，蓝天白云，启文28度");
    }
}
