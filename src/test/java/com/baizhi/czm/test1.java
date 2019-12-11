package com.baizhi.czm;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.czm.dao.UserDao;
import com.baizhi.czm.entity.User;
import com.baizhi.czm.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= CmfzCzmApplication.class)
public class test1 {

    @Resource
    private UserService userService;
    @Resource
    private UserDao userDao;

    //导出
    @Test
    public void test1(){
        List<User> users = userDao.queryAlls();


        /*title:标题名,工作簿名    */
        ExportParams exportParams = new ExportParams("持明法洲", "信息表1");
                                                                        /*需要导出的实体类,导出的数据*/
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D://user.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
