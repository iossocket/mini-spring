package com.iossocket;

import com.iossocket.web.mvc.Controller;
import com.iossocket.web.mvc.RequestMapping;

@Controller
public class SalaryController {

    @RequestMapping("/salary")
    public String getSalary() {
        return "test test";
    }
}
