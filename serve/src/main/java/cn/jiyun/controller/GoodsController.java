package cn.jiyun.controller;

import cn.jiyun.config.PageResult;
import cn.jiyun.config.Result;
import cn.jiyun.mapper.FilePOJOMapper;
import cn.jiyun.pojo.FilePOJO;
import cn.jiyun.pojo.Goods;
import cn.jiyun.pojo.GoodsAddDTO;
import cn.jiyun.pojo.GoodsDTO;
import cn.jiyun.service.GoodsService;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static final String KEY_QUEUE = "QUEUE";
    @Resource
    private GoodsService goodsService;
    @Resource
    private FilePOJOMapper filePOJOMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/getAll")
    public PageResult hello(Integer page, Integer size, @RequestBody GoodsDTO goodsDTO) {
        log.info("参数page:{};size:{};GoodsDTO:{}", page, size, goodsDTO);
        return goodsService.queryDTO(page, size, goodsDTO);
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file) {
        log.info("上传的文件:{}", file);
        String name = file.getOriginalFilename();

        int i = name.lastIndexOf(".");
        System.out.println(i);
        String substring = name.substring(i);
        System.out.println("i:" + 1);
        System.out.println("substring:" + substring);
        long size = file.getSize();
        FilePOJO filePOJO = new FilePOJO();
        filePOJO.setSize(size + "");
        filePOJO.setType(substring);
        filePOJO.setName(name.substring(0, i));

        System.out.println(filePOJO);

        filePOJOMapper.insert(filePOJO);

        return "";
    }

    @RequestMapping("/addGoods")
    public Result addGoods(@RequestBody GoodsAddDTO goodsAddDTO) {
        log.info("添加参数:{}", goodsAddDTO);
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsAddDTO, goods, Goods.class);
        System.out.println("Bean拷贝:{}" + goods);

        goods.setCity(goodsAddDTO.getName());
        goods.setName("3");
        log.info("执行添加SQL:{}", goodsService.save(goods));
//MQ同步数据
        rabbitTemplate.send(KEY_QUEUE, new Message(JSONObject.toJSONString(goods).getBytes()));

        return Result.success();
    }
}
