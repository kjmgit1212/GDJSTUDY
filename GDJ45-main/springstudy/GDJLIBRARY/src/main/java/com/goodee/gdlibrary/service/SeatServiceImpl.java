package com.goodee.gdlibrary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.domain.SeatDTO;
import com.goodee.gdlibrary.mapper.SeatMapper;

@Service
public class SeatServiceImpl implements SeatService {
   
   @Autowired
   private SeatMapper seatMapper;
   
   
   @Override
   public List<SeatDTO> findSeatList() {
      return seatMapper.selectSeatList();
   }
   
   
   @Override
   public SeatDTO findSeat(Long seatNo) {
      return seatMapper.selectSeatByNo(seatNo);
   }
   
   
   @Override
   public void upSeatStatus(Long seatNo, HttpServletRequest request) {
        MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
         Long memberNo = loginMember.getMemberNo();
         String memberId = loginMember.getMemberId();
         SeatDTO seat = SeatDTO.builder()
               .memberNo(memberNo)
               .seatNo(seatNo)
               .memberId(memberId)
               .build();
         
      seatMapper.updateUpSeatStatus(seat);
   }
   
   
   @Override
   public Map<String, Object> seatCheckOut(Long seatCode) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("res", seatMapper.updateDownSeatStatus(seatCode));
      return map;
   }
   
   
   @Override
   public Map<String, Object> seatConfirm(String memberId) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("res", seatMapper.selectSeatById(memberId));
      return map;
   }
   
   
   @Override
   public void downSeatStatusByScheduled() {
      seatMapper.updateDownSeatStatusByScheduled();
   }
   
   
   @Override
   public Map<String, Object> seatRenew() {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("seats", seatMapper.selectSeatList());
      return map;
   }
   
   @Override
   public Long randomSeatCode(Long seatNo) {
      String str = "";
      for(int i = 0; i <6; i++) {
         str += ((int)(Math.random() * 9) + 1);
      }
      Long seatCode = Long.parseLong(str);
      SeatDTO seat = new SeatDTO();   // seatDTO 만들어서 값 세팅하고 매퍼로 보냄.
      seat.setSeatNo(seatNo);
      seat.setSeatCode(seatCode);
      
      seatMapper.updateRandomCode(seat);
      seatMapper.updateSeatDate(seat);
      
      return seatCode;
   }

   
   @Override
   public void addSeatInfo(Long seatNo, HttpServletRequest request) {
      MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
         Long memberNo = loginMember.getMemberNo();
         SeatDTO seat = SeatDTO.builder()
               .memberNo(memberNo)
               .seatNo(seatNo)
               .build();
      seatMapper.insertSeatInfo(seat);
   }
   
   
   
   // 추가한 부분
   @Override
   public int findSeatByMemberNo(Long memberNo) {
      return seatMapper.selectSeatByMemberNo(memberNo);
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}