package com.semi.gamespace.common.handler;

import com.semi.gamespace.common.model.dto.ImageDTO;
import com.semi.gamespace.config.ImageConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageHandler {
    public List<ImageDTO> imageParsing(ImageConfiguration config, List<MultipartFile> imageList, List<String> randomFileName) {
        List<ImageDTO> result = new ArrayList<>();

        //이미지가 null일 때 빈 ArrayList<ImageDTO> 반환
        if(imageList.size() == 0) return result;

        //절대경로 조회
        String rootLocation = System.getProperty("user.dir");
        //파일 경로
        String originalPath = rootLocation + config.getImagePath();
        //썸네일 경로
        String thumbnailPath = rootLocation + config.getThumbnailPath();

        File directoryOrigin = new File(originalPath);
        File directoryThumbnail = new File(thumbnailPath);

        //파일 경로가 없는 경우 디렉토리 생성
        if(!directoryOrigin.exists()) {
            directoryOrigin.mkdirs();   //상위 디렉토리까지 전부 생성
        }
        if(!directoryThumbnail.exists()) {
            directoryThumbnail.mkdirs();
        }

        for(int i = 0; i < imageList.size(); i++) {
            MultipartFile file = imageList.get(0);
            String FileName = randomFileName.get(0);
            ImageDTO resultImage = new ImageDTO();
            //attachCode - sequence
            //참조 코드 - config에 존재
            //원본 이름
            String originFileName = file.getName();
            //저장 이름
            //저장 경로 - originalPath
            //파일 타입 - config에 존재
            //썸네일 경로 - thumbnailPath
            //첨부 여부 - 'Y'

            resultImage.setRefCode(config.getRefCode());
            resultImage.setOriginName(originFileName);
            resultImage.setSaveName(FileName);
            resultImage.setSavePath(config.getImagePath());
            resultImage.setFileType(config.getFileType());
            resultImage.setThumbnailPath(thumbnailPath);
            result.add(resultImage);
        }
        System.out.println("이미지 등록 건수 : " + result.size() + " / " + imageList.size());

        return result;
    }

    public ImageDTO imageParsing(ImageConfiguration config, MultipartFile image, List<String> randomFilename) {
        if(image == null) return null;
        List<MultipartFile> singleImage = new ArrayList<>();
        singleImage.add(image);
        return imageParsing(config, singleImage, randomFilename).get(0);
    }

    public int saveFile(ImageConfiguration config, List<MultipartFile> fileList, List<String> randomFileName) {
        int result = 0;

        //절대경로 조회
        String rootLocation = System.getProperty("user.dir");
        //파일 경로
        String originalPath = rootLocation + config.getImagePath();
        File directory = new File(originalPath);
        for(int i = 0; i < fileList.size(); i++) {
            MultipartFile file = fileList.get(0);
            String fileName = randomFileName.get(0);
            try {
                System.out.println("파일 업로드를 시도합니다 : " + directory + "\\" + fileName);
                File destination = new File(directory + "\\" + fileName);
                file.transferTo(destination);
                result++;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("파일 업로드 중 에러 발생 - " + i);
                File target = new File(directory + fileName);
                target.delete();
            }
        }
        return result;
    }

    public int saveFile(ImageConfiguration config, MultipartFile file, List<String> randomFileName) {
        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file);
        return saveFile(config, fileList, randomFileName);
    }
}
