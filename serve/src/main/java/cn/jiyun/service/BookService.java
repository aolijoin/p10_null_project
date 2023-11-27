package cn.jiyun.service;

import cn.jiyun.pojo.Book;
import cn.jiyun.pojo.User;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;

public interface BookService {
    PageResult getTableDatepa(Integer page, Integer size, Book book);

    Result del(Integer id);

    Result borrow(Integer id);

    Result updateById(Book book);
}
