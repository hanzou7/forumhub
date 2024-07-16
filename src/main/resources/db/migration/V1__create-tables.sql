create table topicos (
    id bigserial not null,
    titulo varchar(100) not null unique,
    mensagem varchar(200) not null unique,
    dataCriacao timestamp,
    status boolean,
    autor varchar(100) not null,
    curso varchar(100) not null,
    primary key (id)
);

create table usuarios (
    id bigserial not null,
    login varchar(100) not null,
    senha varchar(255) not null,
    primary key (id)
);
