package cn.jiyun.service;

import cn.jiyun.result.PageResult;
import cn.jiyun.result.Result;

public interface BorrowService {
    /**
     * 获取Table表
     * @param page
     * @param size
     * @param uid
     * @return
     */
    PageResult getTableBorrow(Integer page, Integer size, Integer uid);

    /**
     * 还书
     * @param id
     * @param bookId
     * @return
     */
    Result returnBook(Integer id, Integer bookId);

    Result returnBookInfo(Integer id);
}
