package com.ando.financial.flipnet.controller;

import com.ando.financial.flipnet.model.request.WithdrawRequest;
import com.ando.financial.flipnet.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WithdrawController {

    @Autowired
    WithdrawService withdrawService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/withdraw")
    public String withdraw(@ModelAttribute("withdrawRequest")WithdrawRequest withdrawRequest){
        withdrawService.withdraw(withdrawRequest);
        return "index";
    }

}
