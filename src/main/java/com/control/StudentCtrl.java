package com.control;

import com.pojo.CmStudent;
import com.pojo.CmUnemp;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/10/31.
 */
@Controller
@RequestMapping("/student")
public class StudentCtrl {
    @Autowired
    private StudentService studentService;
}
