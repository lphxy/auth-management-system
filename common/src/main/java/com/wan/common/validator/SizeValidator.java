package com.wan.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 校验数字大小
 *
 * Created by w1992wishes on 2017/9/8.
 */
public class SizeValidator extends ValidatorHandler<Integer> implements Validator<Integer> {

    private int min;

    private int max;

    private String fieldName;

    public SizeValidator(int min, int max, String fieldName) {
        this.min = min;
        this.max = max;
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, Integer integer) {
        if (null == integer || integer.intValue() > max || integer.intValue() < min) {
            context.addError(ValidationError.create(String.format("SizeValidator => invalid : min=%s, max=%s", min, max))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(integer));
            return false;
        }
        return true;
    }

}
