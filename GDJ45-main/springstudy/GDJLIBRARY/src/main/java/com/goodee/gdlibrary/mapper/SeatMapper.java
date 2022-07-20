package com.goodee.gdlibrary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.SeatDTO;

@Mapper
public interface SeatMapper {
   
   public List<SeatDTO> selectSeatList();
   
   public SeatDTO selectSeatByNo(Long seatNo);

   
   public int updateUpSeatStatus(SeatDTO seat);
   public int updateDownSeatStatus(Long seatCode);
   public SeatDTO selectSeatById(String memberId);
   public int updateDownSeatStatusByScheduled();
   public void updateRandomCode(SeatDTO seat);
   public void updateSeatDate(SeatDTO seat);
   public void insertSeatInfo(SeatDTO seat);
   
   // 추가한 부분
   public int selectSeatByMemberNo(Long memberNo);
}