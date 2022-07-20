package com.goodee.gdlibrary.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goodee.gdlibrary.domain.SeatDTO;

public interface SeatService {
   public List<SeatDTO> findSeatList();
   
   public SeatDTO findSeat(Long seatNo);
   
   public void upSeatStatus(Long seatNo, HttpServletRequest request);
   public Map<String, Object> seatCheckOut(Long seatCode);
   public Map<String, Object> seatConfirm(String memberId);
   public void downSeatStatusByScheduled();
   public Map<String, Object> seatRenew();
   public Long randomSeatCode(Long seatNo);
   public void addSeatInfo(Long seatNo, HttpServletRequest request);
   
   // 추가한 부분
   public int findSeatByMemberNo(Long memberNo);
}