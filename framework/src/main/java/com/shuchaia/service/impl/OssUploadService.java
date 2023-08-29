package com.shuchaia.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.exception.SystemException;
import com.shuchaia.service.UploadService;
import com.shuchaia.utils.PathUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @ClassName OssUploadService
 * @Description 将图片上传到七牛云oss
 * @Author shuchaia
 * @Date 2023/7/6 16:48
 * @Version 1.0
 */
@Service
@Data
// 从配置文件中取值
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;


    /**
     * @param img 上传的图片
     * @return
     */
    @Override
    public ResponseResult uploadImg(MultipartFile img) throws IOException {
        // 判断文件类型 & 文件大小
        // 判断文件类型：获取原始文件名，对原始文件名进行判断
        String originalFilename = img.getOriginalFilename();

        // todo: 自定义验证文件类型的注释
        if (!"image".equals(Objects.requireNonNull(img.getContentType()).split("/")[0])) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        // 判断通过，上传到oss
        String url = uploadOss(img);
        return ResponseResult.okResult(url);
    }

    private String uploadOss(MultipartFile img) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);

        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        // 存放路径约定：年/月/日/UUID.png
        String key = PathUtils.generateFilePath(Objects.requireNonNull(img.getOriginalFilename()));

        try {
            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rxbqokcg3.hn-bkt.clouddn.com/"+key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }
}
