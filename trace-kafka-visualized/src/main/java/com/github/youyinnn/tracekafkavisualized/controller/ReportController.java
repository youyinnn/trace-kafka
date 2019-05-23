package com.github.youyinnn.tracekafkavisualized.controller;

import com.github.youyinnn.tracekafkaapi.utils.CodecStringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author youyinnn
 * Date 5/22/2019
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @RequestMapping("")
    String receive(HttpSession session,
                   HttpServletRequest request,
                   HttpServletResponse response){
        System.out.println("t :" + request.getHeader("t"));
        System.out.println("t :" + CodecStringUtil.verifyToken(request.getHeader("t")));
        System.out.println("r :" + request.getHeader("r"));
        System.out.println("r :" + CodecStringUtil.decryptMessage(request.getHeader("r").replaceAll("\n", "")));
        return "index page";
    }

}
