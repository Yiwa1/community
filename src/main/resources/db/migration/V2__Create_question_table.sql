create table question(
      ID INT auto_increment,
      TITLE VARCHAR(30),
      DESCRIPTION TEXT,
      TAG VARCHAR(256),
      CREATOR VARCHAR(100),
      GMT_CREATE BIGINT,
      GMT_MODIFIED BIGINT,
      VIEW_COUNT  INT,
      COMMENT_COUNT INT,
      LIKE_COUNT INT,
      primary key (ID)
);