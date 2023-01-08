package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Model.DailyViewCount;
import com.example.demo.mapper.DailyViewCountMapper;
import com.example.demo.service.DailyViewCountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2023-01-06
 */
@Service
public class DailyViewCountServiceImpl extends ServiceImpl<DailyViewCountMapper, DailyViewCount> implements DailyViewCountService {

}
