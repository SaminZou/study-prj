DROP TABLE IF EXISTS "role";
CREATE TABLE "role" (
                            "id" varchar(36) NOT NULL,
                            "role_name" varchar(255),
                            "create_time" timestamp(6) NOT NULL
)
;
ALTER TABLE "role" ADD CONSTRAINT "role_pkey" PRIMARY KEY ("id");

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
                            "id" varchar(36) NOT NULL,
                            "openid" varchar(100),
                            "nickname" varchar(100),
                            "icon" varchar(255),
                            "phone" varchar(50),
                            "email" varchar(100),
                            "sex" char(1),
                            "create_time" timestamp(6) NOT NULL,
                            "last_login_time" timestamp(6)
)
;
ALTER TABLE "user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("id");