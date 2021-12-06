update produtos
set
    codigo = :#codigo, 
    descricao = :#descricao, 
    valor = :#valor
where
    codigo = :#codigo
