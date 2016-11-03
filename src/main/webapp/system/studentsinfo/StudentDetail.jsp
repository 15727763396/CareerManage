<%@ page import="com.pojo.CmStudent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2016/10/26
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="../../js/showele.js" ></script>
    <link rel="stylesheet" type="text/css" href="../../css/default.css"/>
    <link rel="stylesheet" href="../../css/icon.css" />


</head>
<body onload="Activeli()">
<div class="table-box">
    <div class="table-content">
        <div class="table-head">
            <div class="table-address">
                <div style="float: left;">
                    <span>信息管理</span>
                    <div class="left-arrow"></div>
                    <span><a href="selecteAllC.html">学生信息</a></span>
                    <div class="left-arrow"></div>
                    <span>详细信息</span>
                </div><br />
                <div class="Big-title">
                    <div class="littil-title">
                        学生详细信息
                    </div>
                    <div class="search-box">
                        <button class="mybutton" type="button" onclick="JavaScript :history.back(-1)">
                            返回上一页
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-bar">
            <ul>
                <li class="active-li" onclick="ShowMarkText()">成绩信息 </li>
                <li onclick="ShowQiwangText()">就业期望</li>
                <li onclick="ShowMarkCanvars()">能力认定</li>
            </ul>
        </div>
        <div id="mark-text">
            <!--这是一条记录开始-->
            <table  class="pure-table pure-table-bordered">
                <tr>
                    <td colspan="8" style="text-align: center;height: 30px;">成绩单</td>
                </tr>
                <tr>
                    <td width="60px">姓名：</td>
                    <td>${student.sname}</td>
                    <td>性别：</td>
                    <td>
                        <c:if test="${!(student.ssex)}">
                            男
                        </c:if>
                        <c:if test="${student.ssex}">
                            女
                        </c:if>
                    </td>
                    <td>学号：</td>
                    <td>${student.sno}</td>
                    <td>专业：</td>
                    <td>${student.spro}</td>
                </tr>

                <tr>
                    <td >年级：</td>
                    <td>${student.sgrade}</td>
                    <td>班级：</td>
                    <td>${student.sclass}</td>
                    <td>入学日期</td>
                    <td>${student.sgrade}/09</td>
                    <td>预期毕业日期</td>
                    <td>${student.sgrade+4}/07</td>
                </tr>
                <tr>
                    <td width="130px">已修必修学分：</td>
                    <td colspan="3">${comcredit}</td>
                    <td>已修选修学分：</td>
                    <td colspan="3">${opcredit}</td>
                </tr>
                <tr>
                    <td >总科目数:</td>
                    <td colspan="3">${allsubjects}</td>
                    <td>毕业清考数:</td>
                    <td colspan="3">${clearsubjects}</td>
                </tr>
                <tr>
                    <td >中兴课程总科目数:</td>
                    <td colspan="3">${zxsubjects}</td>
                    <%--<td>毕业清考数:</td>
                    <td colspan="3">${clearsubjects}</td>--%>
                </tr>
                <tr>
                    <td colspan="8" height="30px"></td>
                </tr>
            </table>
            <table class="pure-table pure-table-bordered">
                <tr>
                    <td colspan="2">中兴课程名称</td>
                    <td>课程类型</td>
                    <td>成绩</td>
                    <td>补考</td>
                    <td>学分</td>
                    <td>学年</td>
                    <td>学期</td>
                </tr>
            <c:if test="${zxreport!=null}">
                <c:forEach var="sub" items="${zxreport}">
                    <tr>
                        <td colspan="2">${sub.gkcm} </td>
                        <td>
                            <c:if test="${sub.glx==0}">
                                必修
                            </c:if>
                            <c:if test="${sub.glx==1}">
                                公选
                            </c:if>
                            <c:if test="${sub.glx==2}">
                                系选
                            </c:if>
                        </td>
                        <td>${sub.gcj} </td>
                        <td>${sub.gbkcj}</td>
                        <td>${sub.gxf}</td>
                        <td>${sub.gxn}</td>
                        <td>${sub.gxq}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${zxreport==null}">
                该学生没有中兴课程成绩
            </c:if>
            </table>
            <div class="table-slipline"></div>
            <!--这是一条记录结束-->

            <div class="button-footer">
                <div class="right-button-footer">
                    <div id="Page">
                        <a href="#">«</a>
                        <span>1</span>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#">6</a>
                        <a href="#">»</a>
                    </div>
                </div>
                <div class="left-button-footer">
                    <button class="mybutton" type="button" onclick="ShowDetailTip()"> <span>导出数据</span></button>
                    <!--<button class="icon-filein" type="button" onclick="ShowDetailTip()"> <span>导入数据</span></button>
                    <button class="icon-down" type="button" onclick="ShowDetailTip()"> <span>下载Excel模板</span></button>-->
                </div>
            </div>

        </div>
        <div id="qiwang-text">
            <form  id="form1"  >
                <table  class="pure-table pure-table-bordered left" id="table-qiwang">
                    <tr>
                        <td width="150px">姓名：</td>
                        <td> <input type="text" id="qw-stuname" disabled="disabled" value="王新"/></td>
                    </tr>
                    <tr>
                        <td>期望就业单位：</td>
                        <td><input type="text" id="qw-comp" disabled="disabled" value="暂无"/></td>
                    </tr>
                    <tr>
                        <td>期望就业岗位：</td>
                        <td><input type="text" id="qw-gw" disabled="disabled" value="web前端" list="qw-gwlist" placeholder="双击修改.."/>
                            <datalist id="qw-gwlist">
                                <option>web前端</option>
                                <option>java开发</option>
                                <option>Android开发</option>
                                <option>软件测试</option>
                                <option>软件工程师</option>
                            </datalist>
                        </td>
                    </tr>
                    <tr>
                        <td>期望薪资：</td>
                        <td><input type="text" id="qw-gz" disabled="disabled" value="2000元/月"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="mybutton" type="button" onclick="beginEdit()">编辑</button>

                            <button class="mybutton" type="button" style="margin-left: 80px;" >保存</button>
                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <div id="mark-canvars">
            <!--这是个人能力评价页面：包括星级评定，以及教师备注。后期加管理员，该星级评定的数据不来自数据库，来自授课教师的反馈。-->
            <div class="starability-container">
                <h3>能力评分：</h3>
                <form>
                    <fieldset class="starability-checkmark">

                        <input type="radio" id="rate5-6" name="rating" value="5" checked="checked"/>
                        <label for="rate5-6" title="非常好">5 stars</label>

                        <input type="radio" id="rate4-6" name="rating" value="4" />
                        <label for="rate4-6" title="好">4 stars</label>

                        <input type="radio" id="rate3-6" name="rating" value="3" />
                        <label for="rate3-6" title="一般">3 stars</label>

                        <input type="radio" id="rate2-6" name="rating" value="2" />
                        <label for="rate2-6" title="不好">2 stars</label>

                        <input type="radio" id="rate1-6" name="rating" value="1" />
                        <label for="rate1-6" title="特别差">1 star</label>
                    </fieldset>
                </form>
            </div>
            <div class="starability-container">
                <h3>教师评价：</h3>
                <form>
                    <textarea disabled="disabled" id="pingjia">${student.sassess}</textarea><br />
                    <div class="buttonbox">
                        <input type="button" value="修改" class="mybutton" onclick="beginBianji()"/>
                        <input type="button" value="保存" class="mybutton" />
                    </div>
                </form>

            </div>

        </div>

    </div>

</div>
</body>
</html>
