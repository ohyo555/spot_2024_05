DROP DATABASE IF EXISTS `OHJ_EXAM`;
CREATE DATABASE `OHJ_EXAM`;
USE `OHJ_EXAM`;

# spot 테이블 생성
CREATE TABLE spot(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    city CHAR(100) NOT NULL,
    spot_name CHAR(100) NOT NULL,
    location CHAR(100) NOT NULL,
    initialDate DATE NOT NULL,
    updateDate DATE NOT NULL,
    `area` INT(255) NOT NULL,
    etc CHAR(255) NOT NULL
);
		
ALTER TABLE spot ADD COLUMN e_city CHAR(100) NOT NULL AFTER city;

UPDATE spot
SET e_city = "chungju"
WHERE id BETWEEN 1 AND 4;
UPDATE spot
SET e_city = "jecheon"
WHERE id BETWEEN 5 AND 11;
UPDATE spot
SET e_city = "boeun"
WHERE id BETWEEN 12 AND 13;
UPDATE spot
SET e_city = "Okcheon"
WHERE id = "14";
UPDATE spot
SET e_city = "Yeongdong"
WHERE id BETWEEN 15 AND 16;
UPDATE spot
SET e_city = "koesan"
WHERE id BETWEEN 17 AND 18;
UPDATE spot
SET e_city = "Angseong"
WHERE id = "19";
UPDATE spot
SET e_city = "Danyang-eup"
WHERE id BETWEEN 20 AND 22;

# answer 테이블 생성
CREATE TABLE qna(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `key` CHAR(255) NOT NULL,
    answer CHAR(255) NOT NULL
);
	
INSERT INTO qna
SET `key` = '수정',
answer = '데이터를 수정하려면, 수정할 항목을 선택하고 변경할 값을 입력한 후 저장하세요';

INSERT INTO qna
SET `key` = '삭제',
answer = '데이터를 삭제하려면, 삭제할 항목을 선택하고 삭제 버튼을 누르세요';

INSERT INTO qna
SET `key` = '회원가입',
answer = '회원가입을 하려면, 필수 정보를 입력하고 가입 버튼을 누르세요';

INSERT INTO qna
SET `key` = '회원정보',
answer = '회원정보를 조회하려면, 내 정보 메뉴에서 확인할 수 있습니다';

INSERT INTO qna
SET `key` = '회원탈퇴',
answer = '회원탈퇴를 하려면, 설정 메뉴에서 회원탈퇴를 선택하고 확인 버튼을 누르세요';

INSERT INTO qna
SET `key` = '회원정보수정',
answer = '회원정보를 수정하려면, 내 정보 메뉴에서 변경할 정보를 수정하고 저장하세요';

INSERT INTO qna
SET `key` = '로그인',
answer = '로그인하려면, 아이디와 비밀번호를 입력하고 로그인 버튼을 누르세요';

INSERT INTO qna
SET `key` = '로그아웃',
answer = '로그아웃하려면, 메뉴에서 로그아웃 버튼을 누르세요';


