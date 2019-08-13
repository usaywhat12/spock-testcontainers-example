CREATE SEQUENCE public."Users_id_seq";

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('"Users_id_seq"'::regclass),
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default",
    lastname character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT "Users_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

GRANT ALL ON TABLE public.users TO test;