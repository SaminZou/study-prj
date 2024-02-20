DROP TABLE IF EXISTS "user_role";
CREATE TABLE "user_role" (
                             "id" varchar(36) NOT NULL,
                             "user_id" varchar(36),
                             "role_id" varchar(36),
                             "create_time" timestamp(6) NOT NULL,
                             "update_time" timestamp(6)
)
;
ALTER TABLE "user_role" ADD CONSTRAINT "user_role_pkey" PRIMARY KEY ("id");