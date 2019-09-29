package com.amvpe.cloud.controller;


import com.amvpe.cloud.config.UploadImgConfig;
import com.amvpe.cloud.entity.Counter;
import com.amvpe.cloud.service.StorageImageService;
import com.amvpe.cloud.tools.Init;
import com.amvpe.cloud.tools.UploadTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 控制器类
 * @author lidonghai
 */
@Controller
public class UploadImgController {
    private final Logger uploadImgControllerLogger = LoggerFactory.getLogger(UploadImgController.class);

    @Autowired
    StorageImageService storageImageService;
    @Autowired
    Init init;

    /**
     * 存储文件的路径，在application.properties设置
     */
    @Value("${customize.storagePath}")
    private String filePath;

    @RequestMapping({"/","/success"})
    public String uploadIndex(){
        return "upload";
    }

    /**
     * 上传文件处理类
     * @param img 传入img
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public String uploadImg(@RequestPart("uploadImg") MultipartFile img, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

        boolean verifyImage = UploadTools.verifyImage(img);

        Map<String,String> responseMap = new HashMap<>(4);

        //图片校验不成功
        if (!verifyImage) {
            responseMap.put("message","支持的图片格式jpeg/png/gif");
            responseMap.put("code","-500");
            responseMap.put("URL","您上传的文件格式有误,请上传jpeg/png/gif格式图片.");
            redirectAttributes.addFlashAttribute("responseMap", responseMap);

            uploadImgControllerLogger.error("图片校验失败");

            return "redirect:/success";
        }

        //图片校验成功
        String imgName = storageImageService.storageImage(img, filePath);
        responseMap.put("message","上传成功");
        responseMap.put("code","200");

        //生成图片访问路径
        String imgURL = init.getHost() + UploadImgConfig.resourcesPattern + imgName;
        responseMap.put("URL",imgURL);

        redirectAttributes.addFlashAttribute("responseMap", responseMap);

        uploadImgControllerLogger.info("成功完成一次存储");

        //图片计数器加一
        Counter.getInstance().setCount(Counter.getInstance().getCount() + 1);

        return "redirect:/success";
    }

    /**
     * 返回图片存储数量
     * @return
     */
    @RequestMapping("/imageCount")
    @ResponseBody
    public String imageCount() {
        return String.valueOf(Counter.getInstance().getCount());
    }

    /**
     * 获取当前服务器时间
     * @return
     */
    @RequestMapping("/nowTime")
    @ResponseBody
    public String nowTime () {
        String nowTime = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        return nowTime;
    }

    /**
     * 捐赠列表
     * @return
     */
    @RequestMapping("/donateList")
    public String donateList() {
        return "donateList";
    }

}
