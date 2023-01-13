package com.semi.gamespace.member.controller;

import com.semi.gamespace.authentication.model.dto.SpaceUser;
import com.semi.gamespace.common.model.dto.ImageDTO;
import com.semi.gamespace.common.model.service.ImageService;
import com.semi.gamespace.member.model.dto.FollowDTO;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final ImageService imageService;

    @Autowired
    public MemberController(MemberService memberService, ImageService imageService) {
        this.memberService = memberService;
        this.imageService = imageService;
    }

    @GetMapping("/login")
    public void memberLoginForm() {}

    //social - google : http://localhost:8001/oauth2/authorization/google
    /* 구글 로그인 API [POST] */
    @ResponseBody
    @GetMapping("/login/social/success")
    public void socialLoginProgress(@RequestParam String code){

    }

    @GetMapping("/regist")
    public void memberRegistForm() {}

    @GetMapping("/denied")
    public String memberAccessDenied() { return "/common/error/denied"; }

    @GetMapping("/insert/success")
    public void memberRegistSuccess() {}

    @GetMapping("/insert/failure")
    public void memberRegistFailure() {}

    @GetMapping("/user/{id}")
    public ModelAndView memberMyPageMain(ModelAndView mv, @PathVariable("id") String userId) {
        MemberDTO member = memberService.findMemberById(userId);
        int historyBoard = memberService.countHistoryOfBoard(member.getMemberCode());
        int followFrom = memberService.countFollowFromByCode(member.getMemberCode());
        int followTo = memberService.countFollowToByCode(member.getMemberCode());
        int historyComment = memberService.countHistoryOfComment(member.getMemberCode());
        ImageDTO image = imageService.selectProfileByCode(member.getMemberCode());

        mv.setViewName("/member/myPage");
        mv.addObject("image", image);
        mv.addObject("member", member);
        mv.addObject("followFrom", followFrom);
        mv.addObject("followTo", followTo);
        mv.addObject("historyBoard", historyBoard);
        mv.addObject("historyComment", historyComment);
        return mv;
    }

    @GetMapping("/userSetting")
    public Model memberProfilePage(Model model, Principal principal) {
        //인증된 사용자 정보를 통해 member정보 불러오기
        MemberDTO member = memberService.findMemberById(principal.getName());
        //팔로우 받은 수
        int followFrom = memberService.countFollowFromByCode(member.getMemberCode());
        //팔로우 건 수
        int followTo = memberService.countFollowToByCode(member.getMemberCode());
        //프로필
        ImageDTO image = imageService.selectProfileByCode(member.getMemberCode());

        model.addAttribute("image", image);
        model.addAttribute("member", member);
        model.addAttribute("followFrom", followFrom);
        model.addAttribute("followTo", followTo);
        return model;
    }

    @GetMapping("/update/info")
    public Model memberInfoUpdatePart1(Model model, Principal principal) {
        MemberDTO member = memberService.findMemberById(principal.getName());
        ImageDTO image = imageService.selectProfileByCode(member.getMemberCode());

        String[] addressData = member.getUserAddress().split("\\^");
        model.addAttribute("member", member);
        model.addAttribute("image", image);
        for(int i = 0; i < 3; i++) {
            model.addAttribute("address"+i, addressData[i]);
        }
        return model;
    }

    @ResponseBody
    @PostMapping("/userSetting")
    public Map<String, String> memberSiteLinkModify(@RequestParam Map<String, String> linkAttribute) throws IOException {

        memberService.updateMemberSiteLink(linkAttribute);

        System.out.println("11111111111111111111111111111");
        return linkAttribute;
    }

    @ResponseBody
    @PostMapping("/userSetting/initLinkModify")
    public Map<String, String> memberSiteLinkModifyInit(@RequestParam Map<String, String> linkCode) throws IOException {
        return linkCode;
    }

    @PostMapping("/update/info")
    public String memberInfoUpdatePart1Submit(HttpServletRequest request, MemberDTO updateMember) {
        String newAddress = request.getParameter("update-zipcode") +
                "^" + request.getParameter("update-address1") +
                "^" + request.getParameter("update-address2");
        updateMember.setUserAddress(newAddress);

        if(memberService.updateMember(updateMember)) {
            return "redirect:/member/userSetting";
        }
        return "redirect:/member/update/info";
    }
    @PostMapping ("/regist")
    public String registMember(HttpServletRequest request, MemberDTO newMember) throws Exception {
        //우편번호, 주소, 상세주소 합치는 과정
        String newAddress = request.getParameter("regist-zipcode") +
                "^" + request.getParameter("regist-address1") +
                "^" + request.getParameter("regist-address2");
        newMember.setUserAddress(newAddress);
        newMember.setUserSiteLink1("");
        newMember.setUserSiteLink2("");
        newMember.setUserSiteLink3("");
        newMember.setUserSiteLink4("");
        newMember.setUserSiteLink5("");
        newMember.setUserSiteLink6("");

        //reCAPTCHA
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        JSONObject recaptchaJson = getJSONResponse(gRecaptchaResponse);

        System.out.println("다음 멤버 추가를 시도합니다 : " + newMember);
        String result = "";
        if(memberService.registMember(newMember)) {
            result = "/success";
        } else {
            result = "/failure";
        }
        return "redirect:/member/insert"+result;
    }

    private JSONObject getJSONResponse(String gRecaptchaResponse) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String secretKey = "6LeYBncjAAAAAMuo6VdXMQQ_aG5r_aw7lHBcw_ej";

        String response = getResponse(url, secretKey, gRecaptchaResponse);
        JSONObject json = getJSONObject(response);

        return json;
    }

    // get Response
    private String getResponse(String url, String secretKey, String gRecaptchaResponse) {
        String response = "";
        try {
            URL urlObject = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) urlObject.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            String param = "secret=" + secretKey + "&response=" + gRecaptchaResponse;

            DataOutputStream stream = new DataOutputStream(conn.getOutputStream());
            stream.writeBytes(param);
            stream.flush();
            stream.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while((inputLine = br.readLine()) != null) {
                response += inputLine;
            }
            br.close();
        } catch (Exception e) {

        }
        return response;
    }

    // get JSONObject from String
    private JSONObject getJSONObject(String jsonString) {
        JSONObject json = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(jsonString);
        } catch (Exception e) {

        }
        return json;
    }
}
