package com.api.versioning.controller;

import com.api.versioning.Version;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user controller.
 *
 * @author : Manos Papantonakos on 19/8/2016.
 */
@RestController("Analysis")
@RequestMapping("analysis")
@Api(value = "user", description = "Operations about users")
public class UserController {

    /**
     * Return the user.
     *
     * @return the user
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "user")
    @Version({1})
    @ApiOperation(value = "Returns user", notes = "Returns the user", tags = { "User"})
    public String getUserV1() {
        return "User V1";
    }

    /**
     * Return the user.
     *
     * @return the user
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "user")
    @Version({2})
    @ApiOperation(value = "Returns user", notes = "Returns the user", tags = {"User"})
    public String getUserV2() {
        return "User V2";
    }

    /**
     * Return the user.
     *
     * @return the user
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "user")
    @Version({3,4})
    @ApiOperation(value = "Returns user", notes = "Returns the user", tags = {"User"})
    public String getUserV12() {
        return "User V3";
    }
}
