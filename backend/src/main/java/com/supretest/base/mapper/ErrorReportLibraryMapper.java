package com.supretest.base.mapper;

import com.supretest.base.domain.ErrorReportLibrary;
import com.supretest.base.domain.ErrorReportLibraryExample;
import com.supretest.base.domain.ErrorReportLibraryWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErrorReportLibraryMapper {
    long countByExample(ErrorReportLibraryExample example);

    int deleteByExample(ErrorReportLibraryExample example);

    int deleteByPrimaryKey(String id);

    int insert(ErrorReportLibraryWithBLOBs record);

    int insertSelective(ErrorReportLibraryWithBLOBs record);

    List<ErrorReportLibraryWithBLOBs> selectByExampleWithBLOBs(ErrorReportLibraryExample example);

    List<ErrorReportLibrary> selectByExample(ErrorReportLibraryExample example);

    ErrorReportLibraryWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ErrorReportLibraryWithBLOBs record, @Param("example") ErrorReportLibraryExample example);

    int updateByExampleWithBLOBs(@Param("record") ErrorReportLibraryWithBLOBs record, @Param("example") ErrorReportLibraryExample example);

    int updateByExample(@Param("record") ErrorReportLibrary record, @Param("example") ErrorReportLibraryExample example);

    int updateByPrimaryKeySelective(ErrorReportLibraryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ErrorReportLibraryWithBLOBs record);

    int updateByPrimaryKey(ErrorReportLibrary record);
}