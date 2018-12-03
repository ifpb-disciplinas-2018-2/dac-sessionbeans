CREATE TABLE clientes(
    nome varchar (25), 
    cpf varchar (25),
    id serial,
    PRIMARY KEY (id)
);
CREATE TABLE produtos(
    descricao varchar (50), 
    valor numeric,
    codigo serial,
    PRIMARY KEY (codigo)
);