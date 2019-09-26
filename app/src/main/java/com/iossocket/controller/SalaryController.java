package com.iossocket.controller;

import com.iossocket.beans.AutoWired;
import com.iossocket.service.SalaryService;
import com.iossocket.web.mvc.Controller;
import com.iossocket.web.mvc.RequestMapping;
import com.iossocket.web.mvc.RequestParam;

@Controller
public class SalaryController {

    @AutoWired
    private SalaryService salaryService;

    @RequestMapping("/salary")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("experience") String experience) {
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
