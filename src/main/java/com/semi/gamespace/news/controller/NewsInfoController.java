package com.semi.gamespace.news.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.gamespace.game.model.dto.CategoryDTO;
import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.game.model.dto.TagDTO;
import com.semi.gamespace.game.model.service.GameInfoService;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import com.semi.gamespace.news.model.dto.NewsComDTO;
import com.semi.gamespace.news.model.dto.NewsDTO;
import com.semi.gamespace.news.model.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = {"/news", "/"})
public class NewsInfoController {

    private final NewsInfoService newsInfoService;
    private final GameInfoService gameInfoService;

    private final MemberService memberService;
    private  final MessageSource messageSource;

    @Autowired
    public NewsInfoController(NewsInfoService newsInfoService,GameInfoService gameInfoService, MemberService memberService, MessageSource messageSource) {
        this.newsInfoService = newsInfoService;
        this.gameInfoService = gameInfoService;
        this.memberService = memberService;
        this.messageSource = messageSource;
    }



    @GetMapping("/news")
    public ModelAndView selectAllNewsList(ModelAndView mv){
        List<NewsDTO> newsList = newsInfoService.selectAllNewsList();
        newsList.stream().forEach(newsDTO -> System.out.println("news = " + newsDTO) );
        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();
        gameInfoList.stream().forEach(GameInfoDTO -> System.out.println("games = " + GameInfoDTO) );

        mv.addObject("gameInfoList", gameInfoList);
        mv.addObject("newsList", newsList);
        mv.setViewName("news/news");

        return mv;
    }


    @ResponseBody
    @GetMapping(value = "/gameCodeNews",produces = "application/json")
    public ResponseEntity selectAllGameCodeNews(@RequestBody Map<String,Object> objectMap){


        Map<String,Object> map = new HashMap<>();
        System.out.println(objectMap);
        map = newsInfoService.selectAllGameCodeNews();

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/newsDetail")
    public  ModelAndView newsDetail(ModelAndView mv, String newsCode){
        NewsDTO newsDTO = newsInfoService.newsDetail(newsCode);


        mv.addObject("newsComList",newsInfoService.getNewsCom(newsCode));

        mv.addObject("detail", newsDTO);
        mv.setViewName("news/newsDetail");

        return mv;
    }
    @PostMapping("/uploadNewsCom")
    public  ModelAndView uploadNewsCom(ModelAndView mv, NewsComDTO newsComDTO,String newsCode){
        newsInfoService.uploadNewsCom(newsComDTO);

        mv.setViewName("redirect:/news/newsDetail?newsCode="+newsCode);
        return mv;
    }


    @PostMapping("/newsDetail")
    public  ModelAndView newsDetail(ModelAndView mv,NewsDTO newsDTO, NewsComDTO newsComDTO,String newsCode){

        Map<String, String> newsCom = new HashMap<String, String>();
        newsCom.put("newsComCode",newsComDTO.getNewsComCode());
        newsCom.put("newsCode",newsCode);
        newsCom.put("newsCom",newsComDTO.getNewsCom());


        newsInfoService.updateNewsCom(newsCom);



        mv.setViewName("redirect:/news/newsDetail?newsCode="+newsCode);
        return mv;
    }
    @PostMapping("/deleteNewsCom")
    public  ModelAndView deleteNewsCom(ModelAndView mv, String newsComCode,String newsCode){


        newsInfoService.deleteNewsCom(newsComCode);

        mv.setViewName("redirect:/news/newsDetail?newsCode="+newsCode);
        return mv;
    }


    @GetMapping("newsInsert")
    public ModelAndView registPage(ModelAndView mv){
        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();


        mv.addObject("gameInfoList", gameInfoList);
        mv.setViewName("news/newsInsert");

        return mv;
    }

    @PostMapping("newsInsert")
    public ModelAndView registNewsInfo(ModelAndView mv, NewsDTO newNewsInfo, RedirectAttributes rttr, Locale locale, Principal principal) throws Exception{

        MemberDTO memberInfo = memberService.findMemberById(principal.getName());
//        newNewsInfo.setMemberCode(memberInfo.getMemberCode());
        newNewsInfo.setMemberName(memberInfo.getMemberCode());

        newsInfoService.registNewsInfo(newNewsInfo);


        mv.setViewName("redirect:/news/news");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registNewsInfo", null, locale));


        return mv;
    }
    @GetMapping("newsUpdate")
    public ModelAndView updateNewsInfoForm(ModelAndView mv, HttpServletRequest request){
        String newsCode = request.getParameter("newsCode");
        NewsDTO newsDTO = newsInfoService.newsDetail(newsCode);
        mv.addObject("detail", newsDTO);
        mv.addObject("update", newsInfoService.getNewsCode(newsCode));
        mv.setViewName("news/newsUpdate");

        return mv;
    }

    @PostMapping("/newsUpdate")
    public  ModelAndView updateNewsInfo(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr, Locale locale) throws Exception {
        NewsDTO newsDTO = newsInfoService.newsDetail(request.getParameter("newsCode"));
        newsDTO.setNewsTitle(request.getParameter("newsTitle"));
        newsDTO.setNewsContent(request.getParameter("newsContent"));
        newsInfoService.updateNewsInfo(newsDTO);
        mv.setViewName("redirect:/news/news");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateNewsInfo", null, locale));

        return mv;
    }

    @GetMapping("/newsDelete")
    public ModelAndView deleteNewsInfo(ModelAndView mv, String newsCode){
        newsInfoService.deleteNewsInfo(newsCode);
        mv.setViewName("redirect:/news/news");

        return mv;
    }


    @PostMapping(value = "tag", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String gameCodeNewsList(Model model, NewsDTO newsDTO,
                                         @RequestParam(value = "gameCode[]")List<String> gameCode) throws Exception{

        gameCode.remove(0);
//        System.out.println(newsDTO);

        Map<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("gameCode", gameCode.isEmpty() ? null : gameCode);
        List<GameInfoDTO> gameCodeNewsList = newsInfoService.gameCodeNewsList(dataMap);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("gameCodeNewsList", gameCodeNewsList);

        String jsonString = gson.toJson(map);

//        System.out.println(jsonString);



        return jsonString;
    }



//    debug insertNewsInfo --------------------------------------------------------------------------------------------
//@GetMapping("/debug/insertNewsInfo")
//public String debugInsertNews(Model model, Principal principal) {
//    NewsDTO news = new NewsDTO();
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////    String transDate = dateFormat.format();
//    String newsContent = new String("지금 로아에서 가장 강력한 매력을 지닌 빌런은 군단장들과 카마인, 카제로스 정도입니다. 그런데 군단장들은 이미 모두 소모되었고(물론 쿠크세이튼, 아브렐슈드는 레이드 후에도 살아있지만) 카멘과 카제로스가 이미 공개되었습니다. 유저들의 입장에서는 메인 빌런들이 \"벌써\" 소모되는 느낌이지요. 그런데 스마게는 이들의 이야기가 1부에 지나지 않으며, 앞으로 더 많은 이야기가 있다고 합니다. 문제는 그 이야기들이 뭐 하나 제대로 드러나지 않았다는 것입니다.\n" +
//            "\n" +
//            "스마게 입장에서는 이런 저런 떡밥들을 통해 이야기를 알리고 있다고 생각하겠지요. 하지만 유저들이 그 떡밥을 제대로 알아보기 어렵다면 차후의 서사에 대한 매력이 없는 상태가 이어지게 됩니다. 아무리 대단한 빌런들이 등장한다 한들, 유저들의 입장에서는 \"그래서 걔가 누군데? 뭐, 일단 잡으면 되는거지?\" 수준의 듣보잡 빌런이 되는거죠.\n" +
//            "\n" +
//            "지금의 스마게는 서사의 진행에 있어서 너무 \"서프라이즈\" 식 스토리 텔링에 집착한다는 느낌이 있습니다. \"쨘, 사실은 이랬어요.\"라는 식의 이야기 진행은 유저들에게 한 순간 \"우와\"하는 반응을 끌어낼 수도 있고, 잔잔한 이야기 진행에 큰 반향을 불러일으킬 수 있다는 장점이 있지만, 반대로 유저들이 수동적으로 이야기에 끌려가는 현상을 만들어냅니다. 유저는 몰랐던 것을 우연히 마주하게 되는 상황이 반복되고, 주어지는 이야기를 그저 겪을 수밖에 없기 때문입니다. 지금 아만의 스토리가 그렇지요. 유저들은 계속해서 아만의 숨겨진 의중과 행위의 근거를 이해하지 못하고, 뒤를 졸졸 따라다니면서 아만이 움직이는 대로 벌어지는 상황을 수동적으로 \"당하게\" 되며, 이제는 희미해져가는 아만에 대한 매력을 충분히 되살리지 못하고 또다시 안개 속으로 보내버렸습니다. \n" +
//            "이런 스토리 전개는 스토리의 흐름 속도를 조절하고 서프라이즈를 준비하는 데는 도움이 될 지언정, 그 스토리를 겪는 유저들에게 매력보다는 불쾌감을 선사하게 됩니다. 흔히 이런 스토리 진행 착오는 스토리를 진행하는 작가가 본인만 알고 있는 정보와 독자들이 지닌 정보의 격차를 제대로 이해하지 못할 경우에 발생합니다. 작가 본인이 지닌 정보를 기준으로 해당 캐릭터에 대한 큰 애정을 당연시 하다보니, 정보가 부족한 독자들이 해당 캐릭터에 대해 작가 만큼 애정을 지니지 못하게 된다는 걸 이해하지 못해서 발생하는 감정의 갭인 것이죠. 제 생각에 지금 스마게 스토리 팀이 아만에 대해 가지는 감정과 플레체까지 진행한 유저들이 아만에 대해 가지는 감정에는 큰 격차가 있다고 봅니다. 그리고 이 격차는 단순히 훗날 서프라이즈로 사실이 공개되는 것 만으로는 결코 해소되지 않을 것입니다.\n"
//    );
//
//    for(int i = 2; i <= 1010; i++) {
//        news.setNewsCode("NEW_" + i);
//        news.setNewsTitle("뉴스제목입니다." + i);
//        news.setNewsView(i+"");
//        news.setNewsDate((int)(Math.random()*50+2000) + "-" +
//                String.format("%02d",(int)(Math.random()*12) + 1) + "-" +
//                String.format("%02d",(int)(Math.random()*30) + 1));
//        news.setNewsContent(newsContent + i);
//        news.setGameName("GAM_" + i);
//        news.setMemberName("MEM_1");
//
//        System.out.println(news);
//        try {
//            newsInfoService.registNewsInfo(news);
//        } catch (Exception e) {
//            System.out.println("error!! (i = " + i + ")");
//            break;
//        }
//    }
//    return "redirect:/";
//}



}
