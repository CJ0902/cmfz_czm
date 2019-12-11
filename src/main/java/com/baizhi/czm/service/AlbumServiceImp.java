package com.baizhi.czm.service;

import com.baizhi.czm.dao.AlbumDao;
import com.baizhi.czm.entity.Album;
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
public class AlbumServiceImp implements AlbumService {
    //注入
    @Resource
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //1.总条数    records
        Integer records = albumDao.totalcount();
        map.put("records",records);
        //2.总页数   total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total",total);
        //3.当前页   page
        map.put("page",page);
        //4.数据    rows
        List<Album> albums =albumDao.selectAll(page,rows);
        map.put("rows",albums);
        return map;
    }

    //添加
    @Override
    public String add(Album album) {
        String uuid = UUIDUtil.getUUID();
        album.setId(uuid);
        album.setPub_date(new Date());

        albumDao.add(album);
        return uuid;
    }

    //修改
    @Override
    public void updat(Album album) {
        albumDao.updat(album);
    }

    //删除
    @Override
    public void delet(String id) {
        albumDao.delet(id);
    }
}
