package com.bsuir.buspark.bl.validator;

import com.bsuir.buspark.bl.exception.validation.TicketValidationException;
import com.bsuir.buspark.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketValidatorImpl implements Validator {

    @Override
    public void validate(Object classToValidate) {
        Ticket ticket = (Ticket) classToValidate;

        if (ticket == null)
        {
            throw new TicketValidationException("Ticket is null");
        }

        if (ticket.getCount() <= 0)
        {
            throw new TicketValidationException("Invalid ticket count. Ticket count can be 1 - 100.");
        }

        if (ticket.getCount() > 100)
        {
            throw new TicketValidationException("Invalid ticket count. Ticket count can be 1 - 100.");
        }

        if (ticket.getArrivalCity() == null)
        {
            throw new TicketValidationException("Arrival city cannot be null.");
        }

        if (ticket.getDepartmentCity() == null)
        {
            throw new TicketValidationException("Department city cannot be null.");
        }


        if (ticket.getArrivalTime() == null)
        {
            throw new TicketValidationException("Arrival time cannot be null.");
        }

        if (ticket.getDepartmentTime() == null)
        {
            throw new TicketValidationException("Department time cannot be null.");
        }

        if (ticket.getBus() == null)
        {
            throw new TicketValidationException("Bus cannot be null.");
        }

        if (ticket.getDistance() < 1 || ticket.getDistance() > 10000)
        {
            throw new TicketValidationException("Distance can only be from 1 - 10000 km long");
        }

        if (ticket.getDriver() == null)
        {
            throw new TicketValidationException("Driver cannot be null");
        }

        if (!ticket.getIsInternational().equalsIgnoreCase("International"))
        {
            if (!ticket.getIsInternational().equalsIgnoreCase("notInternational"))

            throw new TicketValidationException("International field can only be International or notInternational");
        }

        if (ticket.getArrivalTime() == null)
        {
            throw new TicketValidationException("ArrivalTime is null");
        }

        if (ticket.getDepartmentTime() == null)
        {
            throw new TicketValidationException("DepartmentTime is null");
        }

        if (ticket.getDepartmentTime().after(ticket.getArrivalTime()))
        {
            throw new TicketValidationException("DepartmentTime is after ArrivalTime");
        }
    }
}
