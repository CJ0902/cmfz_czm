package com.baizhi.czm.service;

import com.baizhi.czm.dao.GuruDao;
import com.baizhi.czm.dao.UserDao;
import com.baizhi.czm.entity.Guru;
import com.baizhi.czm.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class GuruServiceImp implements GuruService {
    //注入
    @Resource
    private GuruDao guruDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //1.总条数    records
        Integer records = guruDao.totalcount();
        map.put("records",records);
        //2.总页数   total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total",total);
        //3.当前页   page
        map.put("page",page);
        //4.数据    rows
        List<Guru> gurus = guruDao.queryAll(page,rows);
        map.put("rows",gurus);
        return map;
    }

    //根据id查
    @Override
    public Guru queryId(String id) {
        Guru guru = guruDao.queryId(id);
        return guru;
    }
}
