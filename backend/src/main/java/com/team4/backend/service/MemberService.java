package com.team4.backend.service;

import com.team4.backend.mapper.FriendMapper;
import com.team4.backend.mapper.MemberMapper;
import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.Member;
import com.team4.backend.model.dto.MemberDTO;
import com.team4.backend.model.dto.ResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final FriendMapper friendMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public ResultDTO join(MemberDTO memberDTO) {

        if (isMemberExist(memberDTO.getEmail())){
            return ResultDTO.builder()
                    .status(false)
                    .message("이미 존재하는 유저입니다.")
                    .build();
        }

        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        memberMapper.save(memberDTO);

        return ResultDTO.builder()
                .status(true)
                .message("회원가입 되었습니다.")
                .build();
    }

    public boolean isMemberExist(String isEmail) {
        return memberMapper.findMemberByEmail(isEmail).isPresent();
    }

    public Member getLobbyInfoByMemberUID(int memberUID) {
        return memberMapper.findMemberByUID(memberUID);
    }

    public List<FriendDTO> myFriendList(int memberUID) {
        return friendMapper.findMyFriendByMemberID(memberUID);
    }
}
