drop table if exists consertos;

create table consertos (
    id bigint not null auto_increment,
    data_entrada varchar(10) not null,
    data_saida varchar(10) not null,
    mecanico_nome varchar(30) not null,
    mecanico_anos int not null,
    veiculo_marca varchar(30) not null,
    veiculo_modelo varchar(30) not null,
    veiculo_ano int not null,

    primary key(id)
);