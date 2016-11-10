package com.service;

import com.ResObj.InterResObj;
import com.pojo.CmArea;
import com.pojo.CmInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by w on 2016/10/26.
 */
@Service("interService")
public class InterService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private AreaService areaService;
    @Autowired
    private RecruitService recruitService;
    @Autowired
    private StudentService studentService;

    //增加面试学生——ly
    public boolean addInter(int sid,int rid, int aid, String iaddress, String itype, String itime) throws ParseException {
        CmInter inter = new CmInter();
        inter.setCmStudentBySid(studentService.findBySid(sid));
        inter.setCmRecruitByRid(recruitService.findByRid(rid));
        inter.setCmAreaByAid(areaService.findByAid(aid));
        inter.setIaddress(iaddress);
        inter.setItype(itype);
        DateFormat df = DateFormat.getDateInstance();
        Date d = df.parse(itime);
        long da = d.getTime();
        Timestamp ts = new Timestamp(da);
        System.out.println("ts--------"+ts);
        inter.setItime(ts);
        inter.setIsuccess(0);
        inter.setIstate(0);
        try {
            hibernateTemplate.save(inter);
        }catch (Exception e){
            System.out.println("添加面试学生出错");
        }
        return false;
    }

    //删除面试学生——ly
    public boolean delInter(int iid){
        CmInter inter = this.findByIid(iid);
        if(inter!=null){
            inter.setIstate(1);
            hibernateTemplate.saveOrUpdate(inter);
            return true;
        }
        return false;
    }

    //编辑面试学生——ly
    public boolean updateInter(int iid,int isuccess){
        CmInter inter = this.findByIid(iid);
        if(inter!=null){
            inter.setIsuccess(isuccess);
            hibernateTemplate.saveOrUpdate(inter);
            return true;
        }
        return false;
    }

    //按不同条件查询面试学生——ly
    public List<InterResObj> query(int rid,int type,String searchtext){
        String hsql = "select new com.ResObj.InterResObj(i.iid,i.iaddress,i.itype,i.itime,i.isuccess,r.rid,a.aid,a.aprovince,a.acity,s.sid,s.sname,s.ssex,s.sbirth,s.spro,s.sgrade,s.sclass,s.sphone) " +
                "from CmInter i " +
                "inner join i.cmRecruitByRid r " +
                "inner join i.cmAreaByAid a " +
                "inner join i.cmStudentBySid s "  +
                "where i.istate = 0 and i.cmRecruitByRid.rid = ? ";
        if(type==0){
            hsql = hsql + "and s.sname like ?";
        }else if(type==1){
            hsql = hsql + "and s.sgrade like ?";
        }else{
            hsql = hsql + "and s.spro like ?";
        }
        Object[] value = {rid,'%'+searchtext+'%'};
        List<InterResObj> data = (List<InterResObj>)hibernateTemplate.find(hsql,value);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按iid查询面试学生——ly
    public CmInter findByIid(int iid){
        String hsql = "from CmInter i where i.iid = ?";
        List<CmInter> data = (List<CmInter>)hibernateTemplate.find(hsql,iid);
        if(data.size()>0){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //按iid查询面试学生——ly
    public InterResObj findResObjByIid(int iid){
        String hsql = "select new com.ResObj.InterResObj(i.iid,i.iaddress,i.itype,i.itime,i.isuccess,r.rid,a.aid,a.aprovince,a.acity,s.sid,s.sname,s.ssex,s.sbirth,s.spro,s.sgrade,s.sclass,s.sphone) " +
                "from CmInter i " +
                "inner join i.cmRecruitByRid r " +
                "inner join i.cmAreaByAid a " +
                "inner join i.cmStudentBySid s "  +
                "where i.istate = 0 and i.iid = ?";
        List<InterResObj> data = (List<InterResObj>)hibernateTemplate.find(hsql,iid);
        if(data.size()>0){
            return data.get(0);
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //查询学生的面试记录——ly
    public List<InterResObj> findInterBySid(int sid){
        String hsql = "select new com.ResObj.InterResObj(i.iid,i.iaddress,i.itype,i.itime,i.isuccess,r.rid,a.aid,a.aprovince,a.acity,s.sid,s.sname,s.ssex,s.sbirth,s.spro,s.sgrade,s.sclass,s.sphone) " +
                "from CmInter i " +
                "inner join i.cmRecruitByRid r " +
                "inner join i.cmAreaByAid a " +
                "inner join i.cmStudentBySid s "  +
                "where i.istate = 0 and s.sid = ?";
        List<InterResObj> data = (List<InterResObj>)hibernateTemplate.find(hsql,sid);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }

    //查询面试人数——ly
    public int findByRidCount(int rid){
        String hsql = "select count(*) from CmInter i where i.istate = 0 and i.cmRecruitByRid.rid = ?";
        List<?> data = hibernateTemplate.find(hsql,rid);
        System.out.println("面试报名人数："+Integer.parseInt(data.get(0).toString()));
        return Integer.parseInt(data.get(0).toString());
    }

    //查询面试人员信息——ly
    public List<InterResObj> findByRid(int rid){
        String hsql = "select new com.ResObj.InterResObj(i.iid,i.iaddress,i.itype,i.itime,i.isuccess,r.rid,a.aid,a.aprovince,a.acity,s.sid,s.sname,s.ssex,s.sbirth,s.spro,s.sgrade,s.sclass,s.sphone) " +
                "from CmInter i " +
                "inner join i.cmRecruitByRid r " +
                "inner join i.cmAreaByAid a " +
                "inner join i.cmStudentBySid s "  +
                "where i.istate = 0 and r.rid = ?";
        List<InterResObj> data = (List<InterResObj>)hibernateTemplate.find(hsql,rid);
        if(data.size()>0){
            return data;
        }
        System.out.println("未查到相关数据！");
        return null;
    }
}
