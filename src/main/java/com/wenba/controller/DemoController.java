package com.wenba.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenba.common.http.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping("/getRuleItem")
    @ApiOperation(value = "getRuleItem",notes = "getRuleItem接口")
    public ApiResponse getRuleItem(@RequestBody RuleItemParam param){
        List<ClueRuleItem> list = param.getList();
        Map<Integer, List<ClueRuleItem>> collect = list.stream().collect(Collectors.groupingBy(ClueRuleItem::getFlag));
        Set<Map.Entry<Integer, List<ClueRuleItem>>> entries = collect.entrySet();
        List<String> expressionList = new ArrayList<>();
        for(Map.Entry<Integer, List<ClueRuleItem>> entry : entries){
            Integer key = entry.getKey();
            List<ClueRuleItem> value = entry.getValue();
            wrapExpression(value,expressionList);
        }
        String expression = "(";
        if(CollectionUtils.size(expressionList) > 1){
            for(String str : expressionList){
                expression = expression + str + ") || (";
            }
            log.info("before expression--->{}" + expression);
            expression = expression.substring(0,expression.lastIndexOf(")")+1).trim();
        }
        log.info("after expression--->{}" + expression);
        return ApiResponse.ok(expression);
    }

    private void wrapExpression(List<ClueRuleItem> itemList,List<String> expressionList) {
        List<String> expList = new ArrayList<>();
        // expression = "string.contains(first_channel,param) && !string.contains(second_channel,param)";
            for(ClueRuleItem ruleItem : itemList){
                String clueDimension = ruleItem.getClueDimension();  // 规则维度
                String ruleContent = ruleItem.getRuleContent();  // 规则内容
                if(!clueDimension.equals(RuleDimension.valueOf(clueDimension).toString())){
                    throw new RuntimeException("规则维度存在问题...");
                }
                String logicType = ruleItem.getLogicType();  // 规则逻辑运算符
                if(!logicType.equals(LogicTypeEnum.valueOf(logicType).toString())){
                    throw new RuntimeException("规则运算符存在问题...");
                }
                if(StringUtils.isEmpty(ruleContent)){
                    throw new RuntimeException("规则内容存在问题...");
                }
                String typeContains = "";
                if(logicType.equals(LogicTypeEnum.CONTAINS.toString())){
                    typeContains = LogicTypeEnum.CONTAINS.getLogicType();
                }
                if(logicType.equals(LogicTypeEnum.NOT_CONTAINS.toString())){
                    typeContains = LogicTypeEnum.NOT_CONTAINS.getLogicType();
                }
                if(logicType.equals(LogicTypeEnum.EQUAL.toString())){
                    typeContains = LogicTypeEnum.EQUAL.getLogicType();
                }
                typeContains = typeContains.replace("p1",clueDimension.toLowerCase()).replace("p2","\""+ruleContent + "\"").trim();
                expList.add(typeContains);
            }

            String expressionString = "";
            if(expList.size() == 1){
                expressionList.add(expList.get(0));
                return;
            }else {
                for(String exp : expList){
                    expressionString = expressionString + exp + " && ";
                }
            }
            expressionList.add(expressionString.substring(0, expressionString.lastIndexOf("&&")).trim());
    }



    public static void main(String[] args) {
        System.out.println(RuleDimension.valueOf("FIRST_CHANNEL").toString().toLowerCase());
        //System.out.println(LogicTypeEnum.CONTAINS.toString().toLowerCase());
        String mes = "string.contains(A,B)";
        String p = "eee";
        p = "\"" + p + "\"";
        mes = mes.replace("A","ccc").replace("B",p);
        System.out.println(mes);

        String expressionString = "(";
        List<String> list = Arrays.asList("string.contains(first_channel,param) && !string.contains(second_channel,param)","string.contains(city,param)");
        for(String s : list){
            expressionString = expressionString + s + ") || (";
        }
        System.out.println(expressionString.substring(0,expressionString.lastIndexOf(")")+1).trim());
    }

}

enum LogicTypeEnum{
    CONTAINS{
        @Override
        public String getLogicType() {
            return RuleDimensionContains.CONTAINS;
        }
    },
    NOT_CONTAINS{
        @Override
        public String getLogicType() {
            return RuleDimensionContains.NOT_CONTAINS;
        }
    },
    EQUAL{
        @Override
        public String getLogicType() {
            return RuleDimensionContains.EQUAL;
        }
    };
    public abstract String getLogicType();
}

enum RuleDimension{
    FIRST_CHANNEL{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_FIRST_CHANNEL;
        }
    },
    SECOND_CHANNEL{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_SECOND_CHANNEL;
        }
    },
    CHANNEL_NAME{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_CHANNEL_NAME;
        }
    },
    CITY{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.RULE_AREA;
        }
    },
    GRADE{
        @Override
        public String getRuleDimensionName() {
            return RuleDimensionContains.GRADE;
        }
    }
    ;
    public abstract String getRuleDimensionName();
}
interface RuleDimensionContains{
    String RULE_FIRST_CHANNEL = "first_channel";
    String RULE_SECOND_CHANNEL = "second_channel";
    String RULE_CHANNEL_NAME = "channel_name";
    String RULE_AREA = "city";
    String GRADE = "grade";
    String CONTAINS = "string.contains(p1,p2)";
    String NOT_CONTAINS ="!string.contains(p1,p2)";
    String EQUAL = "p1 == p2";
}

@Data
class RuleItemParam{
    private String ruleLevel;
    private List<ClueRuleItem> list;

}

@Data
class ClueRuleItem{
    private Integer id;
    private String clueDimension; //线索维度
    private String logicType; // 逻辑类型
    private String ruleContent;
    private Integer flag;
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