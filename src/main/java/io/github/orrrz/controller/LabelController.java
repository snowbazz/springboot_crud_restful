package io.github.orrrz.controller;

import io.github.orrrz.entity.Label;
import io.github.orrrz.entity.Result;
import io.github.orrrz.entity.StatusCode;
import io.github.orrrz.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by icejam.
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    // 查询全部
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    // 根据主键id查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    // 添加标签
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    // 修改标签，即使传入的参数Label已经包含了id，id也不省去，RESTful风格的一种体现
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody Label label) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    // 删除标签
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "id") String id) {
        labelService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
