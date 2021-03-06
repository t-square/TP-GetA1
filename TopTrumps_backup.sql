PGDMP                          x         	   TopTrumps    12.1    12.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16472 	   TopTrumps    DATABASE     �   CREATE DATABASE "TopTrumps" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE "TopTrumps";
                postgres    false                       0    0    DATABASE "TopTrumps"    COMMENT     E   COMMENT ON DATABASE "TopTrumps" IS 'A temporary teamwork database.';
                   postgres    false    2823            	           0    0    DATABASE "TopTrumps"    ACL     0   GRANT ALL ON DATABASE "TopTrumps" TO "Manager";
                   postgres    false    2823            �            1259    16534 
   GameStatus    TABLE     �   CREATE TABLE public."GameStatus" (
    "GameID" integer NOT NULL,
    "WinTime" integer,
    "RoundsPlayed" integer,
    "RoundsWon" integer,
    "NumberOfDraws" integer,
    "PName" character varying[],
    "Winner" character varying[]
);
     DROP TABLE public."GameStatus";
       public         heap    postgres    false            �            1259    16542    GameStatus_GameID_seq    SEQUENCE     �   ALTER TABLE public."GameStatus" ALTER COLUMN "GameID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."GameStatus_GameID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000
    CACHE 1
);
            public          postgres    false    202                       0    16534 
   GameStatus 
   TABLE DATA           |   COPY public."GameStatus" ("GameID", "WinTime", "RoundsPlayed", "RoundsWon", "NumberOfDraws", "PName", "Winner") FROM stdin;
    public          postgres    false    202   1       
           0    0    GameStatus_GameID_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public."GameStatus_GameID_seq"', 25, true);
          public          postgres    false    203            �
           2606    16541    GameStatus GameStatus_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."GameStatus"
    ADD CONSTRAINT "GameStatus_pkey" PRIMARY KEY ("GameID");
 H   ALTER TABLE ONLY public."GameStatus" DROP CONSTRAINT "GameStatus_pkey";
       public            postgres    false    202                �   x�}�1�0�����̙8:v�C�\:d+-� "�w�X�b����{BFb��$4TmlF���~?/��>6�W\1 �~fH�BM��I��`q�V��A�;���Y�^��vȱ����̟���7`]
�RX�ºtN��t����e�� �f� $�;     