package com.goodee.ex16.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex16.domain.FileAttachDTO;
import com.goodee.ex16.domain.GalleryDTO;

@Mapper
public interface GalleryMapper {
	public int insertGallery(GalleryDTO gallery);
	public int insertFileAttach(FileAttachDTO fileAttach);
}
