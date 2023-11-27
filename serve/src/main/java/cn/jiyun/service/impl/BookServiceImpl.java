package cn.jiyun.service.impl;

import cn.jiyun.mapper.BookMapper;
import cn.jiyun.mapper.BorrowMapper;
import cn.jiyun.pojo.Book;
import cn.jiyun.pojo.Borrow;
import cn.jiyun.pojo.User;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BookService;
import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public PageResult getTableDatepa(Integer page, Integer size, Book book) {
        PageHelper.startPage(page, size);
        List<Book> list = bookMapper.getTableDatepa(book);
        PageInfo info = new PageInfo(list);
        return new PageResult(info.getTotal(), info.getList());
    }

    @Override
    public Result del(Integer id) {
        try {
            bookMapper.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("******");
        }
    }

    @Override
    public Result borrow(Integer id) {
        try {
            Book book = bookMapper.selectById(id);
            book.setStatus(0);
            book.setNum(book.getNum() + 1);
            bookMapper.updateById(book);

            Borrow borrow = new Borrow();
            borrow.setCreateTime(new Date());
            borrow.setBid((long) id);

            String login = (String) redisTemplate.boundValueOps("LOGIN").get();
            User user = JSONObject.parseObject(login, User.class);
            borrow.setUid((long) user.getId());

            borrowMapper.insert(borrow);
            return Result.success();
        } catch (Exception e) {
            return Result.error("借阅失败");
        }
    }

    @Override
    public Result updateById(Book book) {
        try {
            bookMapper.updateById(book);
            return Result.success();
        } catch (Exception e) {
            return Result.error("修改失败");
        }
    }
}
