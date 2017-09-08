package com.wan.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 校验不为null
 *
 * Created by w1992wishes on 2017/9/8.
 */
public class NotNullValidator extends ValidatorHandler<String> implements Validator<String> {

    private String fieldName;

    public NotNullValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (null == s) {
            context.addError(ValidationError.create(String.format("NotNullValidator => invalid"))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(s));
            return false;
        }
        return true;
    }

}