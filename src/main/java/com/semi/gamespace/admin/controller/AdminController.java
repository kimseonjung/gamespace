package com.semi.gamespace.admin.controller;

import com.semi.gamespace.admin.model.dto.SimpleMemberDTO;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/memberList/{page}")
    public ModelAndView adminPage(ModelAndView mv, @PathVariable("page") String page){
        int amount = memberService.countAllUser(true);
        int lastPage = (int) Math.ceil((double)amount / 20) + 1; //[1]1~20, [2]21~40, [3]41~60, ...

        int idxFirst = 20*(Integer.parseInt(page)-1) + 1;
        int idxLast = 20*(Integer.parseInt(page));
        if(idxLast > amount) idxLast = amount;

        Map<String, String> search = new HashMap<>();
        search.put("idxFirst", idxFirst+"");
        search.put("idxLast", idxLast+"");
        search.put("isBlack", "Y");

        List<SimpleMemberDTO> targetList = memberService.findMemberUsingIndex(search);
        List<Map<String, String>> dataList = new ArrayList<>();
        for(int i = 0; i < targetList.size(); i++) {
            dataList.add(new HashMap<>());
            String targetMemberCode = targetList.get(i).getMemberCode();
            dataList.get(i).put("memberCode", targetMemberCode);
            dataList.get(i).put("memberNickname", targetList.get(i).getUserNickname());
            dataList.get(i).put("enrollDate", targetList.get(i).getEnrollDate().toString());
            dataList.get(i).put("memberStatus", targetList.get(i).getMemberStatus());
            Date banTmp = targetList.get(i).getBanDate();
            String banString = banTmp==null ? "-" : banTmp.toString();
            dataList.get(i).put("banDate", banString);
            dataList.get(i).put("historyBoard", memberService.countHistoryOfBoard(targetMemberCode)+"");
            dataList.get(i).put("historyComment", memberService.countHistoryOfComment(targetMemberCode)+"");
            System.out.println(dataList.get(i));
        }
        mv.setViewName("/admin/member");
        mv.addObject("dataList", dataList);
        mv.addObject("currPage", page);
        mv.addObject("lastPage", lastPage);
        return mv;
    }

    @GetMapping("/memberBan")
    public String adminMemberBan(String currPage, String targetCode, String banDateCode) {
        Date now = new Date(System.currentTimeMillis());
        long add = 0L;
        switch (Integer.parseInt(banDateCode)) {
            case 0: add = 1L; break;
            case 1: add = 3L; break;
            case 2: add = 7L; break;
            case 3: add = 15L; break;
            case 4: add = 30L; break;
            case 5: add = 90L; break;
            case 6: add = 0L; break;
        }
        add *= 24L;  //1day = 24hours
        add *= 60L;  //1hour = 60minutes
        add *= 60L;  //1minute = 60seconds
        add *= 1000L;//1second = 1000ms
        if(add > 0) {
            now.setTime(now.getTime()+add);
        }

        Map<String, String> banData = new HashMap<>();
        banData.put("targetCode", targetCode);
        banData.put("banDate", add>0 ? now.toString() : "2099-12-31");
        memberService.memberBanByCode(banData);
        return "redirect:/admin/memberList/"+currPage;
    }
//    @ResponseBody
//    @PostMapping("/selectMember")
//    public List<Map<String, String>> adminSelectAllMember() {
//        return null;
//    }
}
