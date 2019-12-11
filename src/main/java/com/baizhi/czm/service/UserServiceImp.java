package com.baizhi.czm.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.czm.dao.AlbumDao;
import com.baizhi.czm.dao.UserDao;
import com.baizhi.czm.entity.Album;
import com.baizhi.czm.entity.User;
import com.baizhi.czm.util.UUIDUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceImp implements UserService {
    //注入
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //1.总条数    records
        Integer records = userDao.totalcount();
        map.put("records",records);
        //2.总页数   total
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total",total);
        //3.当前页   page
        map.put("page",page);
        //4.数据    rows
        List<User> users = userDao.queryAll(page,rows);
        map.put("rows",users);
        return map;
    }

    //修改状态
    @Override
    public void updat(User user) {
        System.out.println(user);
        userDao.updat(user);
    }

    @Override
    public List<User> quers() {
        List<User> users = userDao.queryAlls();

        /*title:标题名,工作簿名    */
        ExportParams exportParams = new ExportParams("持明法洲", "用户信息表");
        /*需要导出的实体类,导出的数据*/
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D://user.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(users);
        return users;
    }

    //用户统计
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> stat() {
        List<User> usersing = userDao.queryAlls();
        return usersing;

    }

}
