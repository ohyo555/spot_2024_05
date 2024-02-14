package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;


@Mapper
public interface ArticleRepository {

	@Insert("""
			INSERT INTO
			article SET
			regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			title = #{title}, `body` = #{body}
			""")
	public void writeArticle(int memberId, String title, String body);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
			FROM article
			WHERE id = #{id}
			""")
	public Article getArticle(int id);
	
	@Select("""
			SELECT A.*, M.nickname AS extra__writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE authLevel = 7
			""")
	public List<Article> getNotice();
	
	@Select("""
			SELECT A.*, M.nickname AS extra__writer, IFNULL(G.sum, 0) AS `sum`, IFNULL(O.goodsum, 0) AS `goodsum`, IFNULL(X.badsum, 0) AS `badsum`
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			LEFT JOIN (SELECT SUM(`point`) AS `sum`, relId FROM reactionPoint GROUP BY relId) AS G
			ON G.relId = A.id
			LEFT JOIN (SELECT SUM(`point`) AS `goodsum`, relId FROM reactionPoint WHERE `point` = 1 GROUP BY relId) AS O
			ON O.relId = A.id
			LEFT JOIN (SELECT SUM(`point`) AS `badsum`, relId FROM reactionPoint WHERE `point` = -1 GROUP BY relId) AS `X`
			ON X.relId = A.id
			WHERE A.id = #{id}
				""")
	public Article getForPrintArticle(int id);
	
	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);

//	@Update("UPDATE article SET updateDate = NOW(), title = #{title}, `body` = #{body} WHERE id = #{id}")
	@Update("""
			UPDATE article
				<set>
					<if test="title != null and title != ''">title = #{title},</if>
					<if test="body != null and body != ''">`body` = #{body},</if>
					updateDate = NOW()
				</set>
			WHERE id = #{id}
				""")
	public void modifyArticle(int id, String title, String body);

	@Update("""
			UPDATE article SET hit = hit + 1 WHERE id = #{id}
				""")
	public int hitArticle(int id);
	
//	@Select("SELECT * FROM article ORDER BY id DESC")
	@Select("""
			SELECT A.*, M.nickname AS extra__writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			ORDER BY A.id DESC
			""")
	public List<Article> getArticles();
	
	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM article AS A
			WHERE 1
			<if test="boardId != 0">
				AND boardId = #{boardId}
			</if>
			<if test="searchKeyword != ''">
				<choose>
					<when test="searchKeywordTypeCode == 'title'">
						AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<when test="searchKeywordTypeCode == 'body'">
						AND A.body LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<otherwise>
						AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
						OR A.body LIKE CONCAT('%',#{searchKeyword},'%')
					</otherwise>
				</choose>
			</if>
			ORDER BY id DESC
			</script>
			""")
	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);
	
	@Select("""
			<script>
			SELECT A.*, M.nickname AS extra__writer, IFNULL(G.sum, 0) AS `sum`, IFNULL(O.goodsum, 0) AS `goodsum`, IFNULL(X.badsum, 0) AS `badsum`
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			LEFT JOIN (SELECT SUM(`point`) AS `sum`, relId FROM reactionPoint GROUP BY relId) AS G
			ON G.relId = A.id
			LEFT JOIN (SELECT SUM(`point`) AS `goodsum`, relId FROM reactionPoint WHERE `point` = 1 GROUP BY relId) AS O
			ON O.relId = A.id
			LEFT JOIN (SELECT SUM(`point`) AS `badsum`, relId FROM reactionPoint WHERE `point` = -1 GROUP BY relId) AS `X`
			ON X.relId = A.id
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			<if test="searchKeyword != ''">
				<choose>
					<when test="searchKeywordTypeCode == 'title'">
						AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<when test="searchKeywordTypeCode == 'body'">
						AND A.body LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<when test="searchKeywordTypeCode == 'extra__writer'">
						AND M.nickname LIKE CONCAT('%',#{searchKeyword},'%')
					</when>
					<otherwise>
						AND A.title LIKE CONCAT('%',#{searchKeyword},'%')
						OR A.body LIKE CONCAT('%',#{searchKeyword},'%')
					</otherwise>
				</choose>
			</if>
			ORDER BY A.id DESC
			<if test="limitFrom >= 0 ">
				LIMIT #{limitFrom}, #{limitTake}
			</if>
			</script>
			""")
	public List<Article> getForPrintArticles(int boardId, int limitFrom, int limitTake, String searchKeywordTypeCode, String searchKeyword);
	/*
	 * @Update(""" <if test="cnt = 1"> UPDATE good SET good = 0 WHERE memberId =
	 * #{loginedId} AND articleId = #{articleId} <if test="cnt = 0"> UPDATE good SET
	 * good = 1 WHERE memberId = #{loginedId} AND articleId = #{articleId} """)
	 * public int goodupdateArticle(int loginedId, int articleId, int cnt);
	 */
	
	@Select("""
			SELECT hitcount
			FROM article
			WHERE id = #{id}
			""")
	public int getArticleHitCount(int id);
	
	
	@Update("""
			INSERT INTO good SET memberId = #{loginedId}, articleId = #{articleId}, good = 1
			""")
	public int goodaddArticle(int loginedId, int articleId);

	@Update("""
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id}
			""")
	public int increaseHitCount(int id);
}