package com.bsuir.buspark.bl.validator;

import com.bsuir.buspark.bl.exception.validation.BusValidationException;
import com.bsuir.buspark.entity.Bus;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class BusValidatorImpl implements Validator {

    @Override
    public void validate(Object classToValidate) {
        Bus bus = (Bus)classToValidate;

        if (bus == null)
        {
            throw new BusValidationException("Bus is null");
        }

        Pattern patternLogin = Pattern.compile("^[a-z,A-Z,0-9]+$");

        if (!patternLogin.matcher(bus.getGovNumber()).matches()){
            throw new BusValidationException("Gov number pattern mismatch");
        }

        if (!patternLogin.matcher(bus.getModel()).matches()){
            throw new BusValidationException("Gov number pattern mismatch");
        }

        if (bus.getCapacity() < 1 || bus.getCapacity() > 100)
        {
            throw new BusValidationException("Bus capacity can be only 1 - 100");
        }
    }
}
