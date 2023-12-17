package cn.jiyun.controller;

import cn.jiyun.config.RabbitQueue;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
