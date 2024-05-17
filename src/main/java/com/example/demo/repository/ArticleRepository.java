package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;
import com.example.demo.vo.Comment;
import com.example.demo.vo.ReactionPoint;
import com.example.demo.vo.Spot;


@Mapper
public interface ArticleRepository {

	@Insert("""
			INSERT INTO
			article SET
			regDate = NOW(),
			updateDate = NOW(),
			boardId = #{boardId},
			memberId = #{memberId},
			title = #{title}, `body` = #{body}
			""")
	public void writeArticle(int memberId, String title, String body, int boardId);

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
			<script>
			SELECT A.*, M.nickname AS extra__writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			WHERE A.id = #{id}
			GROUP BY A.id
			</script>
			""")
	public Article getForPrintArticle(int id);
	
	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);
	
	@Update("""
			<script>
			UPDATE article
			<set>
			<if test="title != null and title != ''">title = #{title},</if>
			<if test="body != null and body != ''">`body` = #{body},</if>
			updateDate = NOW(),
			</set>
			WHERE id = #{id}
			</script>
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
			SELECT A.*, M.nickname AS extra__writer, IFNULL(COUNT(C.id),0) AS cnt
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			LEFT JOIN `comment` AS C
			ON A.id = C.relId
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
			GROUP BY A.id
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

	@Select("""
			SELECT *
			FROM reactionPoint
			WHERE memberId = #{loginedId}
			AND relId = #{articleId}
			""")
	public ReactionPoint selectgoodArticle(int loginedId, int articleId);
	
	@Update("""
			UPDATE reactionPoint SET <if test="cnt != 1 "> point = 1, updateDate = NOW() WHERE memberId = #{loginedId} AND relId = #{articleId} 
			""")
	public int goodupdateArticle(int loginedId, int articleId, int cnt);

	@Update("""
			UPDATE article
			SET goodReactionPoint = goodReactionPoint + 1
			WHERE id = #{relId}
			""")
	public int increaseGoodReactionPoint(int relId);

	@Update("""
			UPDATE article
			SET goodReactionPoint = goodReactionPoint - 1
			WHERE id = #{relId}
			""")
	public int decreaseGoodReactionPoint(int relId);

	@Update("""
			UPDATE article
			SET badReactionPoint = badReactionPoint + 1
			WHERE id = #{relId}
			""")
	public int increaseBadReactionPoint(int relId);

	@Update("""
			UPDATE article
			SET badReactionPoint = badReactionPoint - 1
			WHERE id = #{relId}
			""")
	public int decreaseBadReactionPoint(int relId);
	
	@Select("""
			SELECT goodReactionPoint
			FROM article
			WHERE id = #{relId}
			""")
	public int getGoodRP(int relId);

	@Select("""
			SELECT badReactionPoint
			FROM article
			WHERE id = #{relId}
			""")
	public int getBadRP(int relId);

	@Select("""
			SELECT *
			FROM spot
			""")
	public List<Spot> getForPrintSpots();

}