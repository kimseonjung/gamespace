package com.semi.gamespace.game.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.gamespace.game.model.dto.*;
import com.semi.gamespace.game.model.service.GameInfoService;
import com.semi.gamespace.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(value = {"/game","/"})
public class GameInfoController {

    private final GameInfoService gameInfoService;

    private  final MessageSource messageSource;

    @Autowired
    public GameInfoController(GameInfoService gameInfoService, MessageSource messageSource) {
        this.gameInfoService = gameInfoService;
        this.messageSource = messageSource;
    }



    @GetMapping("game")
    public ModelAndView selectGameMainList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){



        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();

        List<CategoryDTO> categoryList = gameInfoService.selectAllCategory();
        List<TagDTO> tagList = gameInfoService.selectAllTag();
        List<DevicesDTO> devicesList = gameInfoService.selectAllDevices();

        gameInfoList.stream().forEach(game ->System.out.println("game =" + game));
        categoryList.stream().forEach(category ->System.out.println("category = " + category));
        tagList.stream().forEach(tag -> System.out.println("tag = " + tag));
        devicesList.stream().forEach(devices ->System.out.println("devices = " + devices));



        mv.addObject("gameInfoList", gameInfoList);
        mv.addObject("categoryList", categoryList);
        mv.addObject("tagList", tagList);
        mv.addObject("devicesList", devicesList);
        mv.setViewName("game/gameMain");

        return mv;

    }

    @GetMapping("/gameInfoInsert")
    public ModelAndView selectCategoryTagList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){


        List<CategoryDTO> categoryList = gameInfoService.selectAllCategory();
        List<TagDTO> tagList = gameInfoService.selectAllTag();


        categoryList.stream().forEach(category ->System.out.println("category = " + category));
        tagList.stream().forEach(tag -> System.out.println("tag = " + tag));



        mv.addObject("categoryList", categoryList);
        mv.addObject("tagList", tagList);
        mv.setViewName("game/gameInfoInsert");

        return mv;

    }


//    @GetMapping("selectCategoryOne")
//    public ModelAndView selectCategoryOne(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
//
//        String cate_no = request.getParameter("cate_no");
//
//        List<GameInfoDTO> selectCategoryOne = gameInfoService.selectCategoryOne(cate_no);
//        mv.addObject("cate_no", cate_no);
//        mv.addObject("selectCategoryOne", selectCategoryOne);
//
//        return mv;
//    }

    @PostMapping(value ={ "game"}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String selectGameMainJsonList(Model model, CategoryDTO categoryDTO, TagDTO tagDTO,
                                         @RequestParam(value = "categoryCode[]")List<String> categoryCode, @RequestParam(value = "tagCode[]")List<String> tagCode) throws Exception{

        categoryCode.remove(0);
        tagCode.remove(0);
        System.out.println(categoryDTO);
        System.out.println(tagDTO);

        Map<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("categoryCode", categoryCode.isEmpty() ? null : categoryCode);
        dataMap.put("tagCode", tagCode.isEmpty() ? null : tagCode);


        List<CategoryDTO> categoryTagList = gameInfoService.selectCheckCategoryTag(dataMap);


        System.out.println(categoryCode);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("categoryTagList", categoryTagList);

        String jsonString = gson.toJson(map);

        System.out.println(jsonString);

        //mv.addObject("categoryList", gson.toJson(categoryList));
        //mv.setViewName("jsonView");

        return jsonString;
    }







    @GetMapping("/gameInfoDetail")
    public ModelAndView selectGameInfoDetail (ModelAndView mv, String gameCode){

        GameInfoDTO gameInfoDetail = gameInfoService.selectGameDetail(gameCode);

        System.out.println("detail =" + gameInfoDetail);

        MinimumSystemDTO minimumSystem = gameInfoService.selectDetailMinimumSystem(gameCode);
        RecommendedSystemDTO recommendedSystem = gameInfoService.selectDetailRecommendedSystem(gameCode);
        System.out.println("mini =" + minimumSystem);
        System.out.println("rec =" + recommendedSystem);

        mv.addObject("minimumSystem", minimumSystem);
        mv.addObject("recommendedSystem", recommendedSystem);
        mv.addObject("gameInfoDetail", gameInfoDetail);
        mv.setViewName("game/gameInfoDetail");

        return mv;

    }


//    @GetMapping("/gameInfoUpdate")
//    public ModelAndView updateGameInfoDetailForm(ModelAndView mv, HttpServletRequest request){
//
//        String gameCode = request.getParameter("gameCode");
//        GameInfoDTO gameInfoDetail = gameInfoService.selectGameDetail(gameCode);
//        mv.addObject("gameInfoDetail", gameInfoDetail);
//        mv.addObject("gameInfoUpdate", gameInfoService.getGameCode(gameCode));
//        mv.setViewName("game/gameInfoDetail");
//
//        return mv;
//    }

//    @PostMapping("/gameInfoUpdate")
//    public ModelAndView updateGameInfoDetail(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr, Locale locale) throws Exception {
//
//        GameInfoDTO gameInfoDetail = gameInfoService.selectGameDetail(request.getParameter("gameCode"));
//        gameInfoDetail.setCategoryCode(request.getParameter("categoryCode"));
//        gameInfoDetail.setTagCode(request.getParameter("tagCode"));
//        gameInfoDetail.setDevicesCode(request.getParameter("devicesCode"));
//        gameInfoDetail.setGameName(request.getParameter("gameName"));
//        gameInfoDetail.setRatingCode(request.getParameter("ratingCode"));
//        gameInfoDetail.setLaunchDate(request.getParameter("launchDate"));
//        gameInfoDetail.setPrice(request.getParameter("price"));
//        gameInfoDetail.setDeveloper(request.getParameter("developer"));
//        gameInfoDetail.setPlatformCode(request.getParameter("platformCode"));
//        gameInfoDetail.setDistributorCode(request.getParameter("distributorCode"));
//        gameInfoDetail.setLanguageCode(request.getParameter("languageCode"));
//        gameInfoDetail.setGameIntro(request.getParameter("gameIntro"));
//        gameInfoDetail.setGameStatus(request.getParameter("gameStatus"));
//
//        gameInfoService.updateGameInfo(gameInfoDetail);
//        gameInfoService.updateMinimumSystem(newMinimumSystem);
//        gameInfoService.updateRecommendedSystem(newRecommendedSystem);
//        gameInfoService.updateSpecification(newSpecification);
//
//        mv.setViewName("redirect:/game/game");
//        rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateGameInfo", null, locale));
//
//
//        return mv;
//    }


//
//    @GetMapping("gameInfoInsert")
//    public void registPage(){}




    @PostMapping("gameInfoInsert")
    public ModelAndView registGameInfo(ModelAndView mv, GameInfoDTO newGameInfo, SpecificationDTO newSpecification, MinimumSystemDTO newMinimumSystem, RecommendedSystemDTO newRecommendedSystem , RedirectAttributes rttr, Locale locale) throws Exception{
        gameInfoService.registGameInfo(newGameInfo);
        gameInfoService.registMinimumSystem(newMinimumSystem);
        gameInfoService.registRecommendedSystem(newRecommendedSystem);
        gameInfoService.registSpecification(newSpecification);

        mv.setViewName("redirect:/game/game");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registGameInfo", null, locale));


        return mv;
    }

//    @GetMapping("/debug/insertGameInfo")
//    public String debugInsertGameInfo(Model model, Principal principal) {
//        GameInfoDTO gameInfo = new GameInfoDTO();
//        SpecificationDTO specification = new SpecificationDTO();
//        MinimumSystemDTO minimumSystem = new MinimumSystemDTO();
//        RecommendedSystemDTO recommendedSystem = new RecommendedSystemDTO();
//
//        for(int i = 2; i <= 1010; i++) {
//            gameInfo.setGameName("게임명" + i);
//            gameInfo.setDevicesCode("DIV_" + (int)(Math.random()*3+1)); //ㅎㅎ
//
//            gameInfo.setCategoryCode("CAT_" + (int)(Math.random()*10+1)); // ㅎㅎ
//            gameInfo.setTagCode("TAG_"+ (int)(Math.random()*10+1)); //ㅎㅎ
//            gameInfo.setLaunchDate("2023/01/20");
//            gameInfo.setDeveloper("developer"+ i);
//            gameInfo.setDistributorCode("DIS_" + i);
//            gameInfo.setRatingCode("RAT_" + (int)(Math.random()*4+1)); //gg
//            gameInfo.setPrice(String.valueOf((int)(Math.random()*50000+10000)) + "원");
//            gameInfo.setPlatformCode("PLA_" + (int)(Math.random()*5+1)); //hh
//            gameInfo.setLanguageCode("LAN_" + (int)(Math.random()*3+1)); //gg
//
//            gameInfo.setGameIntro("게임소개입니다" + i);
//            gameInfo.setGameStatus("Y");
//
//
////            minimumSystem.setMinOperatingSystem("최소더미운영체제" + i); //100개까지만
////            minimumSystem.setMinProcessor("최소더미프로세서" + i);  //100개까지만
////            minimumSystem.setMinMemory("최소더미메모리" + i);  //100개까지만
////            minimumSystem.setMinStorageSpace("최소더미저장공간" + i);  //100개까지만
////            minimumSystem.setMinGraphic("최소더미그래픽" + i);  //100개까지만
////
////
////            recommendedSystem.setRecOperatingSystem("권장더미운영체제" + i);  //100개까지만
////            recommendedSystem.setRecProcessor("권장더미프로세서" + i);  //100개까지만
////            recommendedSystem.setRecMemory("권장더미메모리" + i);  //100개까지만
////            recommendedSystem.setRecStorageSpace("권장더미저장공간" + i);  //100개까지만
////            recommendedSystem.setRecGraphic("권장더미그래픽" + i);  //100개까지만
//
//
//            System.out.println(gameInfo);
////            System.out.println(specification);
////            System.out.println(minimumSystem);
////            System.out.println(recommendedSystem);
//            try {
//                gameInfoService.registDebugGameInfo(gameInfo);
////                gameInfoService.registDebugSpecification(specification);
////                gameInfoService.registDebugMinimumSystem(minimumSystem);
////                gameInfoService.registDebugRecommendedSystem(recommendedSystem);
//
//
//
//            } catch (Exception e) {
//                System.out.println("error!! (i = " + i + ")");
//                break;
//            }
//        }
//        return "redirect:/";
//    }


}
