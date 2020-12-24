package com.example.demo.service;

import com.example.demo.common.ResultCode;
import com.example.demo.common.ResultGenerator;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.model.SysUser;
import com.example.demo.util.IpUtil;
import com.example.demo.vo.ResultVO;
import com.example.demo.vo.SysUserVo;
import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 业务层，实现业务
 * */

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

//    public ResultVO checkLogin(String no, String password, HttpServletRequest request) {
//
//        /**
//         * 数据查询接口，获取的数据存放到Users实体中，进行持久化
//         * */
//        SysUser user = sysUserMapper.selectByNo(no);
//
//        /**
//         * 统一数据返回，由于前后端开发模式需进行数据交互，后端可以返回固定格式的数据给前端，
//         * 以便于开发，这是一种简单的实现方式。进一步，我们可以将这一结果参数封装成一个实体类，
//         * 来减少代码冗余。类似下面 'user.setNo(no)' 的操作。
//         * */
////      Map<String, Object> result = new HashMap<>();
//        ResultVO result = new ResultVO();
//
//        //获取当前登录用户IP地址
//        String ip = IpUtil.getIpAddr(request);
//
//        /**
//         * 以下为业务逻辑代码
//         * */
//        if (user == null) {
////          result.put("code", 0);
////          result.put("message", "此工号不存在");
//            result.setCode(0);
//            result.setMessage("此工号不存在");
//        } else {
//            if (!user.getPassword().equals(password)) {
////              result.put("code", 0);
////              result.put("message", "密码不正确");
//                result.setCode(0);
//                result.setMessage("密码不正确");
//            } else {
////              result.put("code", 1);
////              result.put("message", "登录成功");
//                result.setCode(1);
//                result.setMessage("登录成功");
//                user.setLastLoginTime(new Date());
//                user.setLastLoginIp(ip);
//                int insert = sysUserMapper.updateByPrimaryKeySelective(user);
//            }
//        }
//        return result;
//    }

    public ResultGenerator checkLoginByShiro(String no, String password, HttpServletRequest request) {
        /**
         * 使用shiro编写登录认证
         */
//        ResultVO resultVO = new ResultVO();
        SysUser user = sysUserMapper.selectByNo(no);
        //获取当前登录用户IP地址
        String ip = IpUtil.getIpAddr(request);
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(no, password);
        //3.执行登录方法
        try {
            subject.login(token);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(ip);
            int insert = sysUserMapper.updateByPrimaryKeySelective(user);
            if (insert == 0) {
//                resultVO.setCode(0);
//                resultVO.setMessage("网络出错，请联系管理员");
                return ResultGenerator.createByError(ResultCode.INTERNET_ERROR.getCode(), ResultCode.INTERNET_ERROR.getDesc());
            } else {
//                resultVO.setCode(1);
//                resultVO.setMessage("登录成功");
                return ResultGenerator.createBySuccess();
            }
        } catch (Exception e) {
//            resultVO.setCode(0);
//            resultVO.setMessage(e.getMessage());
            return ResultGenerator.createByError(ResultCode.ERROR.getCode(), e.getMessage());
        }
//        try {
//            subject.login(token);
//            resultVO.setCode(1);
//            resultVO.setMessage("登录成功");
//        } catch (UnknownAccountException e) {
//            resultVO.setCode(0);
//            resultVO.setMessage("此工号不存在");
//        } catch (IncorrectCredentialsException e) {
//            resultVO.setCode(0);
//            resultVO.setMessage("密码不正确");
//        }
//        return resultVO;
    }

    public ResultVO userRegister(String no, String password) {
        /**
         * 实例化Users对象，并将要写入数据库的数据存放到user中
         * */
        SysUser user = new SysUser();
        user.setNo(no);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());

        /**
         * 数据库操作接口，这里的返回值为Integer类型，如果数据操作成功，则会有数据返回，否则无数据返回
         * */
        Integer register = sysUserMapper.insertSelective(user);
//      Map<String, Object> result = new HashMap<>();
        ResultVO result = new ResultVO();
        if (register != 1) {
//          result.put("code", 0);
//          result.put("message", "注册失败，请重试！");
            result.setCode(0);
            result.setMessage("注册失败，请重试");
        } else {
//          result.put("code", "1");
//          result.put("message", "注册成功，请登录！");
            result.setCode(1);
            result.setMessage("注册成功");
        }
        return result;
    }

    public ResultVO userUpdate(SysUser user) {
        Integer update = sysUserMapper.updateByPrimaryKey(user);
        ResultVO resultVO = new ResultVO();
        if (update == 0) {
            resultVO.setCode(0);
            resultVO.setMessage("更新出错，请重试");
        } else {
            resultVO.setCode(1);
            resultVO.setMessage("更新成功");
        }
        return resultVO;
    }

    public List<SysUserVo> userList(SysUserVo sysUser) {
        return sysUserMapper.selectAll(sysUser);
    }

    public ResultGenerator userAdd(SysUser sysUser) {
        SysUser current = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysUser.setPassword("123456");
        sysUser.setStatus(1);
        sysUser.setDelFlag(0);
        sysUser.setCreator(current.getId());
        sysUser.setCreateTime(new Date());
        Integer add = sysUserMapper.insertSelective(sysUser);
        if (add != 0) {
            return ResultGenerator.createBySuccess();
        }
        return ResultGenerator.createByError(ResultCode.ERROR.getCode(), "添加失败！");
    }

    public ResultGenerator userDel(String[] no) {
        Integer del = sysUserMapper.delectByNo(no);
        if (del != 0) {
            return ResultGenerator.createBySuccess();
        }
        return ResultGenerator.createByError(ResultCode.ERROR.getCode(), "删除失败！");
    }

}
