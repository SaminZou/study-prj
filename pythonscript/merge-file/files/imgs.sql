DROP TABLE IF EXISTS "imgs";
CREATE TABLE "imgs" (
                            "id" varchar(36) NOT NULL,
                            "img_url" varchar(255),
                            "img_type" varchar(50),
                            "create_time" timestamp(6) NOT NULL,
                            "update_time" timestamp(6)
)
;
ALTER TABLE "imgs" ADD CONSTRAINT "imgs_pkey" PRIMARY KEY ("id");