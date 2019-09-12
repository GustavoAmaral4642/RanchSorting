
use ranchsorting;

select * from tb_campeonato; #1 - for friends
select * from tb_etapa; # 2 - C.t. ricardo carvalho
select * from tb_divisao; # 1 - Amador

#quantidade total de fichas
select * 
	from tb_ficha_inscricao 
		where fi_campeonato=1 
        and fi_etapa=2
        and fi_divisao=1;
        
#id ficha, id_competidor, quantidade de fichas, nome do competidor
select fi_id,
	   fi_competidor,
       count(fi_competidor) as 'quantidade_fichas',
       cp_nome
	from tb_ficha_inscricao 
		inner join tb_competidor on cp_id = fi_competidor
		where fi_campeonato=1 
        and fi_etapa=2
        and fi_divisao=1
			group by fi_competidor;
