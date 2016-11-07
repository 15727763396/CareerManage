package com.service;

import com.pojo.CmJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
@Service("jobService")
public class JobService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    //张小丽：查询所有的岗位
    public List<CmJob> findAllJob(){
       String hsql="select new com.pojo.CmJob(j.jid,j.jname) from CmJob j";
        List<CmJob>data=(List<CmJob>) hibernateTemplate.find(hsql);
        return  data;
    }
    //张小丽：根据学生id查询其就业岗位
    public  CmJob findBySid(int sid){
        String hsql="select  new com.pojo.CmJob(j.jid,j.jname) from CmInter i" +
                "  inner join i.cmRecruitByRid r " +
                "  inner  join r.cmJobByJid j " +
                " where  i.cmStudentBySid.sid=? and i.isuccess=1" +
                "  ORDER BY i.itime desc";
        List<CmJob>data=(List<CmJob>) hibernateTemplate.find(hsql,sid);
        return  data.get(0);
    }
}
