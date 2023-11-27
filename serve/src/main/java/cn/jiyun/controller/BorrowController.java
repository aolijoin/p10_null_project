package cn.jiyun.controller;

import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;
import cn.jiyun.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Resource
    private BorrowService borrowService;

    /**
     * 获取Table表
     * @param page
     * @param size
     * @param uid
     * @return
     */
    @RequestMapping("/getTableBorrow/{page}/{size}/{uid}")
    public PageResult getTableBorrow(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @PathVariable("uid") Integer uid
    ) {
        return borrowService.getTableBorrow(page, size, uid);
    }

    /**
     * 还书
     * @param id
     * @param bookId
     * @return
     */
    @RequestMapping("/returnBook/{id}/{bookId}")
    public Result returnBook(@PathVariable("id") Integer id
            , @PathVariable("bookId") Integer bookId) {
        return borrowService.returnBook(id, bookId);
    }

    @RequestMapping("/returnBookInfo/{id}")
    public Result returnBookInfo(@PathVariable("id") Integer id) {
        return borrowService.returnBookInfo(id);
    }
}
