package cn.jiyun.mapper;

import cn.jiyun.pojo.Book;
import cn.jiyun.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    List<Book> getTableDatepa(Book book);
}
