package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.BusService;
import com.bsuir.buspark.bl.CityService;
import com.bsuir.buspark.bl.TicketService;
import com.bsuir.buspark.doc.PDFReporter;
import com.bsuir.buspark.entity.Bus;
import com.bsuir.buspark.entity.City;
import com.bsuir.buspark.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/docs/")
public class DocsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BusService busService;

    @Autowired
    private CityService cityService;

    private PDFReporter pdfReporter = new PDFReporter();

    @RequestMapping(value = "/getTicketsPDFReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getTicketsPDFReport() {
        return getInputStreamResourceResponseEntity(ticketService.getAll());
    }

    @RequestMapping(value = "/getInternationalTicketsPDFReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getInternationalTicketsPDFReport() {
        return getInputStreamResourceResponseEntity(ticketService.getAllInternational());
    }

    @RequestMapping(value = "/getNotInternationalTicketsPDFReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getNotInternationalTicketsPDFReport() {
        return getInputStreamResourceResponseEntity(ticketService.getAllNotInternational());
    }


    private ResponseEntity<InputStreamResource> getInputStreamResourceResponseEntity(List<Ticket> all) {
        List<Ticket> ticketList = all;
        ByteArrayInputStream byteArrayInputStream = pdfReporter.ticketReport(ticketList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=intTicketReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @RequestMapping(value = "/getCitiesPDFReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getCitiesPDFReport() {
        List<City> cities = cityService.getAll();
        ByteArrayInputStream byteArrayInputStream = pdfReporter.cityReport(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=intTicketReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @RequestMapping(value = "/getBusesPDFReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getBusesPDFReport() {
        List<Bus> buses = busService.getAll();
        ByteArrayInputStream byteArrayInputStream = pdfReporter.busReport(buses);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=intTicketReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }
}
