package com.xq.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Collect;
import com.xq.tmall.entity.Product;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台天猫-收藏页
 */
@Controller
public class ForeCollectController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private CollectService collectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */


    @GetMapping("selectOne")
    public Collect selectOne(Integer id) {
        return this.collectService.queryById(id);
    }
    //获取收藏列表
    @GetMapping("/collectList")
    public String collectList(HttpSession session, Map<String, Object> map) {

        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        Collect collect = new Collect();
        collect.setUserId((Integer) userId);
        List<Collect> collectList = collectService.queryAll(collect);
        Product product = null;
        List<Product> productList = new ArrayList<>();
        for (Collect item: collectList) {
            product = productService.get(item.getProductId());
            productList.add(product);
        }
        logger.info("获取商品列表的对应信息");
        for (Product p : productList) {
            p.setSingleProductImageList(productImageService.getList(p.getProduct_id(), (byte) 0, null));
//            p.setProduct_sale_count(productOrderItemService.getSaleCountByProductId(p.getProduct_id()));
//            p.setProduct_review_count(reviewService.getTotalByProductId(p.getProduct_id()));
            p.setProduct_category(categoryService.get(p.getProduct_category().getCategory_id()));
        }

        map.put("productList",productList);
        return "fore/productCollectListPage";
    }

    //添加收藏和取消收藏
    @ResponseBody
    @RequestMapping(value = "/addHeart/{product_id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String addHeart(@PathVariable("product_id") Integer product_id, HttpSession session) {
        JSONObject object = new JSONObject();
        Collect collect = new Collect();
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId == null) {
            object.put("url", "/login");
            object.put("success", false);
            return object.toJSONString();
        }
        collect.setUserId((Integer) userId);

        logger.info("通过产品ID获取产品信息：{}", product_id);
        Product product = productService.get(product_id);
        if (product == null) {
            object.put("url", "/login");
            object.put("success", false);
            return object.toJSONString();
        }
        List<Collect> collectList = new ArrayList<>();
        Boolean flag = true;
        logger.info("获取收藏列表判断是否收藏");
        collectList =  collectService.queryAll(collect);
        int yn = 0;
        if (collectList!=null){
            for (Collect col :collectList) {
                if (col.getProductId()==product_id){
                    flag = false;
                    collectService.deleteById(product_id);
                    yn = -1;
                    break;
                }
            }
        }
        if (flag){
            logger.info("添加收藏到数据库");
            collect.setProductId(product_id);
            collectService.insert(collect);
            yn = 1;
        }
        if (yn==1){
            object.put("success",true);
        }
        if (yn==-1){
            object.put("success",false);
        }
        return object.toJSONString();
    }

    //取消收藏
    @RequestMapping(value = "/deleteHeart/{product_id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String deleteHeart(@PathVariable("product_id") Integer product_id, HttpSession session,Map<String, Object> map){
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        collectService.deleteById(product_id);
        Collect collect = new Collect();
        collect.setUserId((Integer) userId);
        List<Collect> collectList = collectService.queryAll(collect);
        Product product = null;
        List<Product> productList = new ArrayList<>();
        for (Collect item: collectList) {
            product = productService.get(item.getProductId());
            productList.add(product);
        }
        logger.info("获取商品列表的对应信息");
        for (Product p : productList) {
            p.setSingleProductImageList(productImageService.getList(p.getProduct_id(), (byte) 0, null));
            p.setProduct_category(categoryService.get(p.getProduct_category().getCategory_id()));
        }
        map.put("productList",productList);
        return "fore/productCollectListPage";
    }
}
