package com.team4.backend.service;

import com.team4.backend.mapper.ChannelMapper;
import com.team4.backend.model.ChannelMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelMapper channelMapper;

    public List<ChannelMember> getMyChannels(int memberUID) {
        List<ChannelMember> list = new ArrayList<>();
        list.add(getChannelType("lobby",memberUID));
        list.addAll(channelMapper.findChannelsByEmail(memberUID));
        list.add(getChannelType("addServer",memberUID));
        list.add(getChannelType("public",memberUID));
        return list;
    }

    public ChannelMember getChannelType(String index, int memberUID) {
        return new ChannelMember(0, memberUID, null, null, index);
    }
}
