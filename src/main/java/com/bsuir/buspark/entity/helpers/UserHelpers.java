package com.bsuir.buspark.entity.helpers;

import java.util.ArrayList;
import java.util.List;

public class UserHelpers {

    public List<Integer> getTicketsInts(String tickets) {
        List<Integer> ints = new ArrayList<>();
        if (tickets != null)
        {
            String[] strings = tickets.split(",");
            for (String str : strings)
            {
                if (!str.isEmpty())
                {
                    ints.add(Integer.valueOf(str));
                }
            }
        }
        return ints;
    }

    public String getTicketStr(List<Integer> tickets) {
        String result = new String();
        for (Integer ticket : tickets)
        {
            result = result + String.valueOf(ticket) + ",";
        }
        return result;
    }
}
