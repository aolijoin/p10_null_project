package cn.jiyun.service.impl;

import cn.jiyun.mapper.CartMapper;
import cn.jiyun.mapper.GoodsMapper;
import cn.jiyun.pojo.Cart;
import cn.jiyun.pojo.Goods;
import cn.jiyun.result.Result;
import cn.jiyun.service.CartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 获取购物车Table表格
     *
     * @return
     */
    @Override
    @Transactional
    public Result<HashMap<String, Object>> getTableData() {
        HashMap<String, Object> map = new HashMap<>();
        List<Cart> tableData = cartMapper.getTableData();
        map.put("rows", tableData);
        Map<String, Object> numPrice = cartMapper.getNumAndPrice();
        map.put("numPrice", numPrice);
        return Result.success(map);
    }

    /**
     * 购物车移除商品
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Result handleClick(Integer id) {
        try {
            Cart cart = cartMapper.selectById(id);
            Goods goods = goodsMapper.selectById(cart.getGid());
            goods.setNum(cart.getNum() + cart.getNum());
            goodsMapper.updateById(goods);
            cartMapper.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("移除失败");
        }
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param num
     * @return
     */
    @Override
    @Transactional
    public Result changeNum(Long id, Integer num) {
        Cart cart = cartMapper.selectById(id);
        Goods goods = goodsMapper.selectById(cart.getGid());
        //修改数量不能超过 剩余库存+购物车库存的和
        if (goods.getNum() + cart.getNum() < num) {
            return Result.error("选购数量不可以超过库存数量。剩余库存:" + goods.getNum());
        } else {
            int i = 0;
            i = cart.getNum() - num;
            cart.setNum(num);
            goods.setNum(goods.getNum() + i);
            cartMapper.updateById(cart);
            goodsMapper.updateById(goods);
            return Result.success();
        }

    }

    /**
     * 新增商品到购物车
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Result addCar(Integer id) {
        try {
            LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Cart::getGid, id);
            Cart cart = cartMapper.selectOne(wrapper);
            if (cart != null) {
                return Result.error("购物车中已存在该商品");
            }


//            Cart build = Cart.builder().gid((long) id).num(1).build();
            Cart build = new Cart();
            build.setGid((long) id);
            build.setNum(1);
            cartMapper.insert(build);
            Goods goods = goodsMapper.selectById(id);
            goods.setNum(goods.getNum() - 1);
            goodsMapper.updateById(goods);
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加失败！");
        }
    }
}
