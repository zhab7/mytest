package com.myproject.patterns.creation.factorymethod;

/**
 * @author zhanjianjian
 * @since 2021/5/12
 */
public class VolunteerFactory implements LeiFengFactory {

    @Override
    public LeiFeng createLeiFeng() {
        return new Volunteer();
    }
}
