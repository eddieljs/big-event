package com.eddie.Validation;

import com.eddie.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {
    //<给那个注解提供规则，校验的数据类型是什么>

    /**
     *
     * @param value 将来要校验的数据
     * @param context context in which the constraint is evaluated
     *
     * @return true:通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if(value == null || value.isEmpty()){
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")){
        return true;
        }
        return false;
    }

}
