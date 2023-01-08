package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Model.Friends;
import com.example.demo.mapper.FriendsMapper;
import com.example.demo.service.FriendsService;
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
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements FriendsService {

}
