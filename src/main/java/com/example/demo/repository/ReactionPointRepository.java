package com.example.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReactionPointRepository {

	@Select("""
			SELECT IFNULL(SUM(RP.point),2)
			FROM reactionPoint AS RP
			WHERE RP.relTypeCode = #{relTypeCode}
			AND RP.relId = #{relId}
			AND RP.memberId = #{memberId}
			""")
	public int getSumReactionPoint(int memberId, String relTypeCode, int relId);

	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{memberId},
			`point` = 1
			""")
	public int addGoodReactionPoint(int memberId, String relTypeCode, int relId);

	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{memberId},
			`point` = -1
			""")
	public int addBadReactionPoint(int memberId, String relTypeCode, int relId);

	@Delete("""
			DELETE FROM reactionPoint
			WHERE memberId = #{memberId}
			AND relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			""")
	public void deleteReactionPoint(int memberId, String relTypeCode, int relId);

}