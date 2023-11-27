package cn.jiyun.service.impl;

import cn.jiyun.mapper.BookMapper;
import cn.jiyun.mapper.BorrowMapper;
import cn.jiyun.pojo.Book;
import cn.jiyun.pojo.Borrow;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BorrowService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    private static final String KEY_CURRENT_USER = "CURRENT_USER";

    /**
     * 获取Table表
     *
     * @param page
     * @param size
     * @param uid
     * @return
     */
    @Override
    public PageResult getTableBorrow(Integer page, Integer size, Integer uid) {
        PageHelper.startPage(page, size);
        List<Borrow> borrows = borrowMapper.getTableInfo(uid);
        PageInfo info = new PageInfo(borrows);
        return new PageResult(info.getTotal(), info.getList());
    }

    /**
     * 还书
     *
     * @param id
     * @param bookId
     * @return
     */
    @Override
    public Result returnBook(Integer id, Integer bookId) {
        try {
            Book book = bookMapper.selectById(bookId);
            book.setStatus(1);
            bookMapper.updateById(book);

            LambdaQueryWrapper<Borrow> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Borrow::getId, id);
            Borrow borrow = borrowMapper.selectOne(wrapper);
            borrow.setEndTime(new Date());
            borrowMapper.updateById(borrow);

            redisTemplate.boundValueOps(KEY_CURRENT_USER).set(null);
            return Result.success();
        } catch (Exception e) {
            return Result.error("还书失败");
        }
    }


    @Override
    public Result returnBookInfo(Integer id) {
        try {
            Borrow borrow = borrowMapper.selectById(id);
//        rabbitTemplate.send("CURRENT_USER", new Message(JSONObject.toJSONString(borrow).getBytes()));
//            HashMap<String, Object> map = new HashMap<>();
            redisTemplate.boundValueOps(KEY_CURRENT_USER).set(JSONObject.toJSONString(borrow));

            return Result.success("正在催还!!! ");
        } catch (Exception e) {
            return Result.error("催还失败");
        }


    }


}
