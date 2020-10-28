package com.xo.web.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @Author： XO
 * @Description：
 * @Date： 2020/4/26 20:56
 */

@RestController
@RequestMapping("/user")
@Api(tags = "测试")
public class CourseController {



/*    @PostMapping("/addCourse")
    @ApiOperation("用户")
    @CannotRepetitionRequest(timeout = 1)
    public CommonResult<Integer> add(@RequestBody AuthUser authUser) {

        authUserMapper.insertSelective(authUser);

        return CommonResult.requestError("成功插入",0);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    public CommonResult<AuthUser> getList() {
        return CommonResult.success("列表",
                authUserMapper.selectByPrimaryKey(1));
    }*/


}
