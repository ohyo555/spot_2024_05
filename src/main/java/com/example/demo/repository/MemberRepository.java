package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Mapper
public interface MemberRepository {
	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
			FROM `member`
			WHERE name = #{name} 
			AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);

	@Insert("""
			INSERT INTO
			`member` SET
			regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("SELECT * FROM `member` WHERE id = #{id}")
	public Member getMember(int id);

	@Select("""
			SELECT loginPw
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	public String login(String loginId, String loginPw);
	
	@Update("""
			<script>
			UPDATE member
			<set>
			<if test="loginPw != null and loginPw != ''">loginPw = #{loginPw},</if>
			<if test="name != null and name != ''">name = #{name},</if>
			<if test="nickname != null and nickname != ''">nickname = #{nickname},</if>
			<if test="cellphoneNum != null and cellphoneNum != ''">cellphoneNum = #{cellphoneNum},</if>
			<if test="email != null and email != ''">email = #{email},</if>
			updateDate = NOW()
			</set>
			WHERE id = #{id}
			</script>
			""")
	public void setMember(int id, String loginPw, String name, String nickname, String cellphoneNum, String email);

}