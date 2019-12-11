package com.baizhi.czm.service;

import com.baizhi.czm.dao.BannerDao;
import com.baizhi.czm.entity.Banner;
import com.baizhi.czm.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BannerServiceImp implements BannerService {
    //注入
    @Resource
    private BannerDao bannerDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //1.总条数  records
        Integer records = bannerDao.totalcount();
        map.put("records",records);
        //2.总页数  total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total",total);
        //3.当前页  page
        map.put("page",page);
        //4.数据   rows
        List<Banner> banners = bannerDao.selectAll(page,rows);
        map.put("rows",banners);

        return map;
    }

    //添加
    @Override
    public String add(Banner banner) {
        String uuid= UUIDUtil.getUUID();
        banner.setId(uuid);
        banner.setState("1");
        banner.setUpload_time(new Date());

        System.out.println(banner);
        bannerDao.add(banner);
        return uuid;
    }

    //修改
    @Override
    public void updat(Banner banner) {
        bannerDao.updateByID(banner);
    }

    //删除
    @Override
    public void delet(String id) {
        bannerDao.deletByID(id);
    }
}
