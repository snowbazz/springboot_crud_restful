# springboot_crud_restful

### Description

一个 SpringBoot 入门小 Demo，只有简单的 CRUD 和`ExceptionHandler`异常处理的使用，符合 RESTful 规范。

关于RESTful我做了个总结，可以结合我的一篇文章：[RESTful API](<https://orrrz.github.io/post/restful-api/>)。

### Note

- SpringBoot 启动三部曲，导入依赖，编写yml文件，编写引导类。因为仅懂一点皮毛，许多细节我没掌握，所以这里的过程我就不赘述。
- 说一下我刚学习遇到的一个小坑，SpringBootApplication引导类的位置要在Controller包的同级，也就是Controller类的上一级，否则页面报404错误。我通俗的理解为，引导类需要管理controller，所以目录位置应当高于controller类。底层原因我目前没了解，后续会深入学习。

- 下面是LabelController的代码，可以看到RESTful风格的体现

```java
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

    // 修改标签
    // 即使传入的参数Label已经包含了id，id也不省去，RESTful风格的一种体现
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


```



几个常用的注解记录

```java

@PathVariable：自动将rest请求匹配到同名参数上，用在路径传参

@RequestBody：自动将请求的json参数 组装成对象

@ResponseBody：将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML

@ModelAttribute：自动将请求的form表单参数 组装成对象（本文无关）
```





### 异常处理

值得一提的是，在这期间我学习了如何使用`@ControllerAdvice` + `@ExceptionHandler` 实现全局的异常处理。

下面是我自己处理全局异常以及自定义异常的代码

```java
package io.github.orrrz.exception;

import io.github.orrrz.entity.Result;
import io.github.orrrz.entity.StatusCode;
import org.omg.CORBA.SystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by icejam.
 * 全局异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    // 自定义异常
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result customHandler(SystemException e) {
        return new Result(false, StatusCode.ERROR, "系统异常");
    }

    // 其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
```

- 我的代码中使用增强Controller做全局异常处理，即`@ControllerAdivice`注解，有这个注解的类中的方法的某些注解会应用到所有的Controller里，比如`@ExceptionHandler`注解。

- 由于我的Controller的CRUD方法没有捕获异常，所以会将异常抛给`BaseExceptionHandler`处理（我上面定义的全局异常处理类，这个类中只处理了两个异常，如果还需要特殊处理的异常，只需要加上处理的方法即可），直接返回异常信息给前端。

- tips: `@ControllerAdvice` 注解标注当前bean是对 Controller 进行增强，具体增强什么，需要配合`@ExceptionHandler` 实现对异常的精确处理。

补充：对于SpringMVC异常处理的方式详见我的笔记：[SpringMVC异常处理](<https://orrrz.github.io/post/springmvc%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86/>)

