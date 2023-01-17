package com.semi.gamespace.member.controller;

import com.semi.gamespace.admin.model.dto.SimpleMemberDTO;
import com.semi.gamespace.authentication.model.dto.SpaceUser;
import com.semi.gamespace.common.model.dto.ImageDTO;
//import com.semi.gamespace.common.model.service.EmailService;
import com.semi.gamespace.common.model.service.ImageService;
import com.semi.gamespace.config.ImageConfiguration;
import com.semi.gamespace.member.model.dto.FollowDTO;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final ImageService imageService;
//    private final EmailService emailService;

    @Autowired
    public MemberController(MemberService memberService, ImageService imageService) {
        this.memberService = memberService;
        this.imageService = imageService;
    }
    @Autowired
//    public MemberController(MemberService memberService, ImageService imageService, EmailService emailService) {
//        this.memberService = memberService;
//        this.imageService = imageService;
//        this.emailService = emailService;
//    }

    @GetMapping("/login")
    public void memberLoginForm() {}

    @GetMapping("/login/social/success")
    public String socialLoginProgress(){
        return "/main/index";
    }

    @GetMapping("/regist")
    public void memberRegistForm() {}

    @GetMapping("/findId")
    public void memberFindAccountId() {}

    @GetMapping("/findPwd")
    public void memberFindAccountPwd() {}

    @GetMapping("/denied")
    public String memberAccessDenied() { return "/common/error/denied"; }

    @GetMapping("/insert/success")
    public void memberRegistSuccess() {}

    @GetMapping("/insert/failure")
    public void memberRegistFailure() {}

    @GetMapping("/user/{id}")
    public ModelAndView memberMyPageMain(ModelAndView mv, @PathVariable("id") String userId, Principal principal) {
        MemberDTO member = memberService.findMemberById(userId);
        if(member == null) {
            mv.setViewName("/common/error/memberNotFound");
            return mv;
        }
        int historyBoard = memberService.countHistoryOfBoard(member.getMemberCode());
        int followFrom = memberService.countFollowFromByCode(member.getMemberCode());
        int followTo = memberService.countFollowToByCode(member.getMemberCode());
        int followStatus = 0;   //1 : false, 2 : true
        if(principal != null && principal.getName() != member.getUserId()) {
            MemberDTO currUser = memberService.findMemberById(principal.getName());
            Map<String, String> followInfo = new HashMap<>();
            followInfo.put("current", currUser.getMemberCode());
            followInfo.put("target", member.getMemberCode());
            followStatus = memberService.checkFollowState(followInfo);//팔로우 상태를 저장
        }
        int historyComment = memberService.countHistoryOfComment(member.getMemberCode());
        ImageDTO image = imageService.selectProfileByCode(member.getMemberCode());

        mv.setViewName("/member/myPage");
        mv.addObject("image", image);
        mv.addObject("member", member);
        mv.addObject("followFrom", followFrom);
        mv.addObject("followTo", followTo);
        mv.addObject("followStatus", followStatus);
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
//        ImageDTO image = imageService.selectProfileByCode(member.getMemberCode());

//        model.addAttribute("image", image);
        model.addAttribute("member", member);
        model.addAttribute("followFrom", followFrom);
        model.addAttribute("followTo", followTo);
        return model;
    }

    @GetMapping("/update/modifyPwd")
    public String memberModifyPwd() {return "/member/modifyPassword";}

    @GetMapping("/leave")
    public void memberLeave() {};

    @GetMapping("/update/info")
    public Model memberInfoUpdatePart1(Model model, Principal principal) {
        MemberDTO member = memberService.findMemberById(principal.getName());
        ImageDTO image = imageService.selectProfileByCode(member.getMemberCode());

        String[] addressData = member.getUserAddress().split("\\^");
        model.addAttribute("member", member);
        model.addAttribute("image", image);
        if(addressData.length == 3) {
            for(int i = 0; i < 3; i++) {
                model.addAttribute("address"+i, addressData[i]);
            }
        } else {
            for(int i = 0; i < 3; i++) {
                model.addAttribute("address"+i, "");
            }
        }

        return model;
    }

    @GetMapping("/user/{id}/follow/{dir}/{page}")
    public ModelAndView adminPage(ModelAndView mv,
                                  Principal principal,
                                  @PathVariable("id") String id,
                                  @PathVariable("dir") String dir,
                                  @PathVariable("page") String page,
                                  @RequestParam(required = false) String items){
        //조회중인 사용자
        MemberDTO currUser = memberService.findMemberById(id);
        MemberDTO loginUser = new MemberDTO();
        //팔로우 받은 수
        int followFrom = memberService.countFollowFromByCode(currUser.getMemberCode());
        //팔로우 건 수
        int followTo = memberService.countFollowToByCode(currUser.getMemberCode());
        int followStatus = 0;   //1 : false, 2 : true
        if(principal.getName() != null) {
            loginUser = memberService.findMemberById(principal.getName());
            Map<String, String> followInfo = new HashMap<>();
            followInfo.put("current", loginUser.getMemberCode());
            followInfo.put("target", currUser.getMemberCode());
            followStatus = memberService.checkFollowState(followInfo);//팔로우 상태를 저장
        }

        if(items==null) items = "10";

        int itemPerList = Integer.parseInt(items);   // 예) 페이지 당 20개 표시

        Map<String, String> searched = new HashMap<>();
        searched.put("fromUserCode", dir.equals("from") ? "1" : currUser.getMemberCode());
        searched.put("toUserCode", dir.equals("to") ? "1" : currUser.getMemberCode());    //1 -> 조회 안되게!

        int amount = memberService.countAllFollowUser(searched);
        int lastPage = (int) Math.ceil((double)amount / itemPerList); //[1]1~20, [2]21~40, [3]41~60, ...

        int idxFirst = itemPerList*(Integer.parseInt(page)-1) + 1;
        int idxLast = itemPerList*(Integer.parseInt(page));
        if(idxLast > amount) idxLast = amount;

        int pageIdx = (Integer.parseInt(page)-1)/10*10+1;
        int pageEnd = (Math.min(lastPage, pageIdx+9));
        int pagePrev = (Integer.parseInt(page)-1)/10*10;    //[0]1~10, [10]11~20, [20]21~30, ...
        int pageNext = pagePrev + 11;                       //[11]1~10, [21]11~20, [31]21~30, ...

        Map<String, String> findMap = new HashMap<>();
        findMap.put("currUserCode", currUser.getMemberCode());
        findMap.put("idxFirst", idxFirst+"");
        findMap.put("idxLast", idxLast+"");

        List<String> targetList = new ArrayList<>();
        if(dir.equals("from")) targetList = memberService.findFollowerUsingIndex(findMap);
        if(dir.equals("to")) targetList = memberService.findFollowingUsingIndex(findMap);
        List<Map<String, String>> dataList = new ArrayList<>();
        for(int i = 0; i < targetList.size(); i++) {
            dataList.add(new HashMap<>());
            MemberDTO member = memberService.findMemberByCode(targetList.get(i));
            Map<String, String> followMap = new HashMap<>();
            String codeKey1 = dir.equals("from") ? "toUserCode" : "fromUserCode";
            String codeKey2 = dir.equals("to") ? "toUserCode" : "fromUserCode";
            followMap.put(codeKey1, currUser.getMemberCode());
            followMap.put(codeKey2, targetList.get(i));
            dataList.get(i).put("followDate", memberService.getFollowDate(followMap));
            dataList.get(i).put("userId", member.getUserId());
            dataList.get(i).put("userNickname", member.getUserNickname());
            dataList.get(i).put("enrollDate", member.getEnrollDate().toString());
            dataList.get(i).put("historyBoard", memberService.countHistoryOfBoard(member.getMemberCode())+"");
            dataList.get(i).put("historyComment", memberService.countHistoryOfComment(member.getMemberCode())+"");
            System.out.println(dataList.get(i));
        }


        mv.setViewName("/member/follow");
        mv.addObject("member", currUser);
        mv.addObject("dataList", dataList);
        mv.addObject("dataSize", dataList.size());
        mv.addObject("followFrom", followFrom);
        mv.addObject("followTo", followTo);
        mv.addObject("followStatus", followStatus);
        mv.addObject("currPage", page);
        mv.addObject("lastPage", lastPage);
        mv.addObject("pageIdx", pageIdx);
        mv.addObject("pageEnd", pageEnd);
        mv.addObject("pagePrev", pagePrev);
        mv.addObject("pageNext", pageNext);
        mv.addObject("dir", dir);
        System.out.println(mv);
        return mv;
    }

//    @GetMapping("/debug/insertMember")
//    public String debugInsertMember(Model model, Principal principal) {
//        MemberDTO member = new MemberDTO();
//
//        for(int i = 10; i <= 999; i++) {
//            member.setUserId("user" + i);
//            member.setUserPwd("pass" + i);
//            member.setUserNickname("test" + i);
//            member.setUserName("name" + i);
//            member.setUserPhone("010" + String.format("%08d", (int) (Math.random()*100000000)));
//            member.setUserEmail("test" + i + "@test.com");
//            member.setUserBirthday((int)(Math.random()*50+1973) + "-" +
//                    String.format("%02d",(int)(Math.random()*12) + 1) + "-" +
//                    String.format("%02d",(int)(Math.random()*30) + 1));
//            member.setUserGender(Math.random()*3 < 1 ? "U" : (Math.random()*2 < 1 ? "M" : "F"));
//            member.setUserAddress(String.format("%05d", (int) (Math.random()*100000))+"^my test address " + i + "^멤버의 상세주소 (한글과 괄호) " + i);
//            member.setUserIntroduce((Math.random()*2<1 ? "hello" : "hi") + ", " + i + " world!");
//            System.out.println(member);
//            try {
//                memberService.registMember(member);
//            } catch (Exception e) {
//                System.out.println("error!! (i = " + i + ")");
//                break;
//            }
//        }
//        return "redirect:/";
//    }

//    @GetMapping("/debug/insertFollow")
//    public String debugInsertFollow() {
//        Map<String, String> conn = new HashMap<>();
//        String followReq = "";
//        String followTar = "";
//
//        for(int i = 4; i <= 993; i++) {
//            followReq = "MEM_" + i;
//            for(int j = 4; j <= 993; j++) {
//                if(Math.random()*100 < 33) {
//                    if(i == j) continue;
//                    followTar = "MEM_" + j;
//                    conn.put("requestCode", followReq);
//                    conn.put("targetCode", followTar);
//                    memberService.insertFollowConnect(conn);
//                }
//            }
//        }
//        return "redirect:/";
//    }

    @ResponseBody
    @PostMapping("/findId")
    public Map<String, String> memberFindIdVerify(@RequestParam Map<String, String> dataId) {
        String selectedId = memberService.findMemberId(dataId);
        Map<String, String> resultAttribute = new HashMap<>();

        if(selectedId == null) resultAttribute.put("resultId", "userNotFound");
        else resultAttribute.put("resultId", selectedId);

        return resultAttribute;
    }

//    @ResponseBody
//    @PostMapping("/findPwd")
//    public Map<String, String> memberFindPwdVerify(@RequestParam Map<String, String> dataPwd) {
//        String selectedEmail = memberService.findMemberForEmailSend(dataPwd);
//        Map<String, String> resultAttribute = new HashMap<>();
//
//        if(selectedEmail == null) resultAttribute.put("resultEmail", "userNotFound");
//        else {
//            String code = "";
//            try {
//                code = emailService.sendSimpleMessage(selectedEmail);
//            } catch (MessagingException | UnsupportedEncodingException e) {
//                throw new RuntimeException(e);
//            }
//            resultAttribute.put("resultEmail", selectedEmail);
//            resultAttribute.put("emailCode", code);
//        }
//
//        return resultAttribute;
//    }

    @ResponseBody
    @PostMapping("/findPwdDirect")
    public Map<String, String> memberFindPwdVerify(Principal principal, @RequestParam Map<String, String> input) {
        MemberDTO currUser = memberService.findMemberById(principal.getName());
        String password = input.get("inputPass");

        Map<String, String> resultAttribute = new HashMap<>();
        if(!memberService.memberPasswordIsMatch(currUser, password)) resultAttribute.put("findResult", "passwordNotMatch");
        else resultAttribute.put("findResult", "passwordMatch");

        return resultAttribute;
    }

    @PostMapping("/leave")
    public String memberLeaveProcess(@RequestParam String userLeave, Principal principal) {
        MemberDTO currUser = memberService.findMemberById(principal.getName());
        if(!memberService.memberPasswordIsMatch(currUser, userLeave)) return "/member/leave";
        memberService.leaveMemberByCode(currUser.getMemberCode());
        memberService.deleteFollowAll(currUser.getMemberCode());
        return "redirect:/member/logout";
    }

    @ResponseBody
    @PostMapping("/userSetting")
    public Map<String, String> memberSiteLinkModify(@RequestParam Map<String, String> linkAttribute) {
        memberService.updateMemberSiteLink(linkAttribute);

        System.out.println("memberSiteLinkModify()");
        return linkAttribute;
    }

    @ResponseBody
    @PostMapping("/myPage")
    public Map<String, String> memberFollowToggleMethod(@RequestParam Map<String, String> followData, Principal principal) {
        MemberDTO currUser = memberService.findMemberById(principal.getName());
        //기존false : 추가모드, 기존true : 삭제모드
        boolean isAdd = followData.get("isFollow").equals("F");

        Map<String, String> codeData = new HashMap<>();
        codeData.put("requestCode", currUser.getMemberCode());
        codeData.put("targetCode", followData.get("userCode"));
        if(isAdd) memberService.insertFollowConnect(codeData);
        else memberService.deleteFollowConnect(codeData);

        Map<String, String> returnValue = new HashMap<>();
        returnValue.put("beforeFollow", followData.get("beforeFollow"));
        return returnValue;
    }

    @ResponseBody
    @PostMapping("/userSetting/initLinkModify")
    public Map<String, String> memberSiteLinkModifyInit(@RequestParam Map<String, String> linkCode) throws IOException {
        return linkCode;
    }

    @PostMapping("/update/info")
    public String memberInfoUpdatePart1Submit(HttpServletRequest request, MemberDTO updateMember) {
        //기본정보 수정
        String newAddress = request.getParameter("update-zipcode") +
                "^" + request.getParameter("update-address1") +
                "^" + request.getParameter("update-address2");
        updateMember.setUserAddress(newAddress);

        if(memberService.updateMember(updateMember)) {
            return "redirect:/member/userSetting";
        }
        return "redirect:/member/update/info";
    }

    @ResponseBody
    @PostMapping("/update/profile")
    public Map<String, String> memberInfoUpdateProfile(@RequestParam MultipartFile image, Principal principal) {
        MemberDTO currUser = memberService.findMemberById(principal.getName());
        ImageConfiguration imageConfig = ImageConfiguration.builder()
                .maxFileSize(1024 * 1024 * 10)  //10MB
                .encodingType("UTF-8")
                .fileType("BODY")   //check('TITLE', 'BODY'), 나눠서 구현할 사람 전용
                .imagePath("/src/main/resources/static/image/attachment/original/")     //이미지 저장 경로
                .thumbnailPath("/src/main/resources/static/image/attachment/thumbnail/")   //썸네일 저장 경로
                .refCode(currUser.getMemberCode())  //참조 코드 - 이 사진이 어떤 code를 참조하는지 기입
                .build();
        if(imageService.insertMemberProfile(imageConfig, image)) {
            System.out.println("profile 등록 성공");
        } else {
            System.out.println("profile 등록 실패");
        }

        return null;
    }

    @ResponseBody
    @PostMapping("/regist/idcheck")
    public Map<String, String> memberRegistIdCheck(@RequestParam Map<String, String> input) {
        String inputId = input.get("userId");
        Map<String, String> output = new HashMap<>();
        if(memberService.registIdCheck(inputId)) {  //true : 가능, false : 중복
            output.put("canUse", "true");
        } else {
            output.put("canUse", "false");
        }

        return output;
    }

    @ResponseBody
    @PostMapping("/regist/nicknamecheck")
    public Map<String, String> memberRegistNicknameCheck(@RequestParam Map<String, String> input) {
        String inputNickname = input.get("userNickname");
        Map<String, String> output = new HashMap<>();
        if(memberService.registNicknameCheck(inputNickname)) {  //true : 가능, false : 중복
            output.put("canUse", "true");
        } else {
            output.put("canUse", "false");
        }

        return output;
    }

    @ResponseBody
    @PostMapping("/regist/emailcheck")
    public Map<String, String> memberRegistEmailCheck(@RequestParam Map<String, String> input) {
        String inputEmail = input.get("userEmail");
        Map<String, String> output = new HashMap<>();
        if(memberService.registEmailCheck(inputEmail)) {  //true : 가능, false : 중복
            output.put("canUse", "true");
        } else {
            output.put("canUse", "false");
        }

        return output;
    }

    @PostMapping("/update/modifyPwd")
    public String memberModifyPaassword(@RequestParam Map<String, String> input, Principal principal) {
        MemberDTO currUser = memberService.findMemberById(principal.getName());

        Map<String, String> data = new HashMap<>();
        data.put("userCode", currUser.getMemberCode());
        data.put("userData", input.get("userNewPwd"));

        memberService.updateMemberPassword(data);

        return "redirect:/member/userSetting";
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
