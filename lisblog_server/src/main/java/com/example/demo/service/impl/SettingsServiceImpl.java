package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Model.Settings;
import com.example.demo.mapper.SettingsMapper;
import com.example.demo.service.SettingsService;
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
public class SettingsServiceImpl extends ServiceImpl<SettingsMapper, Settings> implements SettingsService {

}
