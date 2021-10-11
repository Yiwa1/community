create table comment(
 ID BIGINT auto_increment COMMENT '评论id',
 TYPE INT COMMENT '评论类型,1级评论或2级评论',
 PARENT_ID INT COMMENT '上级评论id',
 content text COMMENT '评论内容',
 LIKE_COUNT BIGINT COMMENT '点赞数',
 CREATOR VARCHAR(100) COMMENT '评论者id',
 GMT_CREATE BIGINT COMMENT '创建时间',
 GMT_MODIFIED BIGINT COMMENT '修改时间',
 primary key (ID)
)engine innodb charset utf8;