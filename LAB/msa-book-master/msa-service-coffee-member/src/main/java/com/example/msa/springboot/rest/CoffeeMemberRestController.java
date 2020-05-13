package com.example.msa.springboot.rest;

import com.example.msa.springboot.repository.ICoffeeMemberMapper;
import com.example.msa.springboot.repository.dvo.MemberDVO;
import com.example.msa.springboot.rest.rvo.MemberRVO;
import com.example.msa.springboot.service.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class CoffeeMemberRestController {

    @Autowired
    ICoffeeMemberMapper iCoffeeMemberMapper;

    @Autowired
    ServiceConfig serviceConfig;

    @RequestMapping(value = "/coffeeMember/v1.0/greet", method = RequestMethod.GET)
    public String greet() {
        return serviceConfig.getGreeting();
    }

    @HystrixCommand
    @RequestMapping(value = "/coffeeMember/v1.0/{memberName}", method = RequestMethod.GET)
    public boolean coffeeMember(@PathVariable("memberName") String memberName) {

        MemberDVO memberDVO = new MemberDVO();
        memberDVO.setMemberName(memberName);

        if (iCoffeeMemberMapper.existsByMemberName(memberDVO)
                .getMemberName()
                .isEmpty()) return false;
        else return true;
    }

    @HystrixCommand
    @RequestMapping(value = "/coffeeMember/v1.1", method = RequestMethod.POST)
    public boolean coffeeMember(@RequestBody MemberRVO memberRVO) {

        MemberDVO memberDVO = new MemberDVO();
        memberDVO.setMemberName(memberRVO.getMemberName());

        if (iCoffeeMemberMapper.existsByMemberName(memberDVO)
                .getMemberName()
                .isEmpty()) return false;
        else return true;
    }

    @HystrixCommand(fallbackMethod = "fallbackFunction")
    @RequestMapping(value = "/fallbackTest", method = RequestMethod.GET)
    public String fallbackTest() throws Throwable {
        throw new Throwable("fallbackTest");
    }

    public String fallbackFunction() {
        return "fallbackFunction()";
    }

    @RequestMapping(value = "/createMemberTable", method = RequestMethod.PUT)
    public void createMemberTable() {
        iCoffeeMemberMapper.createMemberTable();
    }

    @RequestMapping(value = "/insertMemberData", method = RequestMethod.PUT)
    public void insertMemberData() {
        iCoffeeMemberMapper.insertMemberData();
    }
}
