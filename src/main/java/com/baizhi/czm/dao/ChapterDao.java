package com.baizhi.czm.dao;

import com.baizhi.czm.entity.Banner;
import com.baizhi.czm.entity.Chapter;

import java.util.List;

public interface ChapterDao {
    //展示所有
    public List<Banner> selectAll(String album_id);
    //求总条数
    public  Integer  totalcount();

    //添加
    public void add(Chapter chapter);
    //修改
    public  void  update(Chapter chapter);
    //根据id删除
    public void delet(String id);
}
