package io.github.orrrz.service;

import io.github.orrrz.dao.LabelDao;
import io.github.orrrz.entity.Label;
import io.github.orrrz.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by icejam.
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    // 查询全部
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    // 根据id查询
    public Object findById(String id) {
        return labelDao.findById(id);
    }

    // 添加标签
    public Label add(Label label) {
        label.setId(String.valueOf(idWorker.nextId()));
        return labelDao.save(label);
    }

    // 修改标签
    public Label update(Label label) {
        return labelDao.save(label);
    }

    // 删除标签
    public void delete(String id) {
        labelDao.deleteById(id);
    }
}
