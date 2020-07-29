package com.wenba.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenba.common.http.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@Api(tags = "demo控制层")
@Slf4j
public class DemoController {

    /**
     * @author: tongrongbing
     * @description:  测试多参数和LocalDateTime序列化（字段上需要加上@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")）
     *                  或者自定义json序列化器
     * @time: 2020/7/14 3:03 下午
     * @param paramDto
     * @return com.wenba.common.http.ApiResponse
     */
    @PostMapping
    @ApiOperation(value = "demoTest",notes = "demoTest接口")
    public ApiResponse add(@RequestBody @Valid ParamDto paramDto){
        paramDto.setEndTime(LocalDateTime.now().plusDays(2));
         return ApiResponse.ok(paramDto);
    }

    /**
     * @author: tongrongbing
     * @description: 单个文件上传
     * @time: 2020/7/15 9:17 上午
     * @param multipartFile
     * @return com.wenba.common.http.ApiResponse
     */
    @PostMapping("/file")
    public ApiResponse uploadFile(@RequestParam(name = "file")MultipartFile multipartFile) throws Exception{
        log.info("name->{},originName->{},size->{}",multipartFile.getName(), multipartFile.getOriginalFilename(), multipartFile.getSize());
        String folder = "/Users/wenba/Desktop/study";
        File file = new File(folder,new Date().getTime()+multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return ApiResponse.ok();
    }

    /**
     * @author: tongrongbing
     * @description: 多个文件上传
     * @time: 2020/7/15 9:18 上午
     * @param fileList
     * @return com.wenba.common.http.ApiResponse
     */
    @PostMapping("/files")
    public ApiResponse uploadFiles(@RequestParam(name = "file") List<MultipartFile> fileList) throws Exception{
        for(MultipartFile file : fileList){
            log.info("name->{},originName->{},size->{}",file.getName(), file.getOriginalFilename(), file.getSize());
            String fileName = file.getOriginalFilename();
            //File dest = new File(" src/main/resources/static/" + fileName);
            File dest = new File("/Library/tong_program/trb/src/main/java/com/wenba/controller",fileName);
            file.transferTo(dest);
        }
        return ApiResponse.ok();
    }

}
@Getter
@Setter
class ParamDto {

    @NotNull(message = "必填")
    private Integer paramId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime endTime;

    private List<Contact> contactList;

    private Map<String,Person> map;

    private Person person;
}
@Getter
@Setter
class Contact{
    private String name;
    private String value;
    private List<String> stringList;
}
@Getter
@Setter
class Person{
    private String name;
    private int age;
}