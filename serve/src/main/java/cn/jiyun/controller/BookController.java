package cn.jiyun.controller;

import cn.jiyun.pojo.Book;
import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/book")
@RestController
public class BookController {
    @Resource
    private BookService bookService;

    @RequestMapping("/getTableDate/{page}/{size}")
    public PageResult getTableDate(
            @PathVariable("page") Integer page
            , @PathVariable("size") Integer size
            , @RequestBody Book book) {
        return bookService.getTableDatepa(page, size, book);
    }

    @RequestMapping("/del/{id}")
    public Result del(@PathVariable("id") Integer id) {
        return bookService.del(id);
    }

    @RequestMapping("/borrow/{id}")
    public Result borrow(@PathVariable("id") Integer id) {
        return bookService.borrow(id);
    }

    @RequestMapping("updateBook")
    public Result updateBook(@RequestBody Book book) {
        return bookService.updateById(book);
    }
}
