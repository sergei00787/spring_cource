-- Table: public.department
-- DROP TABLE public.department;

CREATE TABLE public.department
(
    id integer NOT NULL DEFAULT nextval('department_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    min_salary integer,
    max_salary integer,
    CONSTRAINT department_pk PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.department
    OWNER to postgres;

-- Index: department_id_uindex
-- DROP INDEX public.department_id_uindex;

CREATE UNIQUE INDEX department_id_uindex
    ON public.department USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;


-- Table: public.employee
-- DROP TABLE public.employee;

CREATE TABLE public.employee
(
    id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    surname character varying COLLATE pg_catalog."default" NOT NULL,
    salary integer,
    detail_id integer,
    department_id integer,
    CONSTRAINT employee_pk PRIMARY KEY (id),
    CONSTRAINT employee_department_id_fk FOREIGN KEY (department_id)
        REFERENCES public.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT employee_employee_detail_id_fk FOREIGN KEY (detail_id)
        REFERENCES public.employee_detail (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.employee
    OWNER to postgres;
-- Index: employee_id_uindex
-- DROP INDEX public.employee_id_uindex;

CREATE UNIQUE INDEX employee_id_uindex
    ON public.employee USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;


-- Table: public.employee_detail
-- DROP TABLE public.employee_detail;

CREATE TABLE public.employee_detail
(
    id integer NOT NULL DEFAULT nextval('employee_detail_id_seq'::regclass),
    city character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    CONSTRAINT employee_detail_pk PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE public.employee_detail
    OWNER to postgres;
-- Index: employee_detail_id_uindex

-- DROP INDEX public.employee_detail_id_uindex;

CREATE UNIQUE INDEX employee_detail_id_uindex
    ON public.employee_detail USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;

-- SEQUENCE: public.department_id_seq

-- DROP SEQUENCE public.department_id_seq;

CREATE SEQUENCE public.department_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.department_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.employee_detail_id_seq

-- DROP SEQUENCE public.employee_detail_id_seq;

CREATE SEQUENCE public.employee_detail_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.employee_detail_id_seq
    OWNER TO postgres;

-- SEQUENCE: public.employee_id_seq

-- DROP SEQUENCE public.employee_id_seq;

CREATE SEQUENCE public.employee_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.employee_id_seq
    OWNER TO postgres;