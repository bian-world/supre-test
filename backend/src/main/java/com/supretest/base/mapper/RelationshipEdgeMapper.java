package com.supretest.base.mapper;

import com.supretest.base.domain.RelationshipEdge;
import com.supretest.base.domain.RelationshipEdgeExample;
import com.supretest.base.domain.RelationshipEdgeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationshipEdgeMapper {
    long countByExample(RelationshipEdgeExample example);

    int deleteByExample(RelationshipEdgeExample example);

    int deleteByPrimaryKey(RelationshipEdgeKey key);

    int insert(RelationshipEdge record);

    int insertSelective(RelationshipEdge record);

    List<RelationshipEdge> selectByExample(RelationshipEdgeExample example);

    RelationshipEdge selectByPrimaryKey(RelationshipEdgeKey key);

    int updateByExampleSelective(@Param("record") RelationshipEdge record, @Param("example") RelationshipEdgeExample example);

    int updateByExample(@Param("record") RelationshipEdge record, @Param("example") RelationshipEdgeExample example);

    int updateByPrimaryKeySelective(RelationshipEdge record);

    int updateByPrimaryKey(RelationshipEdge record);
}
