create table notification
(
    ID BIGINT auto_increment,
    NOTIFIER varchar(100) null,
    RECEIVER varchar(100) null,
    CONTENT varchar(256) null,
    RELATE_ID BIGINT null,
    TYPE int null,
    GMT_CREATE int null,
    STATUS int null,
    constraint notification_pk
        primary key (ID)
)engine innodb charset utf8;
