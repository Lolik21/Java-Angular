package com.bsuir.buspark;

import com.bsuir.buspark.bl.exception.validation.BusValidationException;
import com.bsuir.buspark.bl.validator.BusValidatorImpl;
import com.bsuir.buspark.entity.Bus;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("ALL")
@RunWith(JUnitParamsRunner.class)
public class BusValidatorTests {

    private BusValidatorImpl busValidator = new BusValidatorImpl();

    @Test
    @Parameters({"Maz 34 92,3941FAD,40",
            "Del 30 turbo,841FA92,100",
            "Tour 43 City, 83237124, 2",
            "Super Jet 41,9429FASS, 80",
            "Polotsk DIP,DIP293PD, 99"})
    public void validBusData(String model, String govNumber, int capacity) {
        Bus bus = new Bus();
        bus.setModel(model);
        bus.setGovNumber(govNumber);
        bus.setCapacity(capacity);

        busValidator.validate(bus);
    }

    @Test(expected = BusValidationException.class)
    @Parameters({"Maz 34 92 @#,3941FAD,40",
            "D%%el 30 turbo,841FA92,100",
            "*&Tour 43 City, 83237124, 20",
            "Super Jet ())))41,9429FASS, 80",
            "Polotsk.... DIP,DIP293PD, 123"})
    public void InvalidBusDataModel(String model, String govNumber, int capacity) {
        Bus bus = new Bus();
        bus.setModel(model);
        bus.setGovNumber(govNumber);
        bus.setCapacity(capacity);

        busValidator.validate(bus);
    }

    @Test(expected = BusValidationException.class)
    public void InvalidBusDataModelNull() {
        Bus bus = new Bus();
        bus.setModel(null);
        bus.setGovNumber("Some Number");
        bus.setCapacity(100);

        busValidator.validate(bus);
    }

    @Test(expected = BusValidationException.class)
    public void InvalidBusDataNumberNull() {
        Bus bus = new Bus();
        bus.setModel("Some Model");
        bus.setGovNumber(null);
        bus.setCapacity(100);

        busValidator.validate(bus);
    }

    @Test(expected = BusValidationException.class)
    public void InvalidBusDataNumberAndModelNull() {
        Bus bus = new Bus();
        bus.setModel(null);
        bus.setGovNumber(null);
        bus.setCapacity(100);

        busValidator.validate(bus);
    }


    @Test(expected = BusValidationException.class)
    @Parameters({"Maz 34 92,3941FAD___32,40",
            "Del 30 turbo,^^841FA92^^,100",
            "Tour 43 City, 8323$$$7124, 20",
            "Super Jet 41,9429FASS.222, 80",
            "Polots DIP,DIP293PD!!!, 123"})
    public void InvalidBusDataGovNumber(String model, String govNumber, int capacity) {
        Bus bus = new Bus();
        bus.setModel(model);
        bus.setGovNumber(govNumber);
        bus.setCapacity(capacity);

        busValidator.validate(bus);
    }

    @Test(expected = BusValidationException.class)
    @Parameters({"Maz 34 92,3941FAD32,-900000",
            "Del 30 turbo,841FA92,101",
            "Tour 43 City, 83237124, -1",
            "Super Jet 41,9429FASS222, 0",
            "Polots DIP,DIP293PD, -123"})
    public void InvalidBusDataCapacity(String model, String govNumber, int capacity) {
        Bus bus = new Bus();
        bus.setModel(model);
        bus.setGovNumber(govNumber);
        bus.setCapacity(capacity);

        busValidator.validate(bus);
    }
}