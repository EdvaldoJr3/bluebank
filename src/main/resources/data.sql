DROP PROCEDURE IF EXISTS GetRoles;


DELIMITER //
CREATE PROCEDURE GetRoles ()
BEGIN
	DECLARE qtd INT DEFAULT 0;
    
	 -- ROLES
    SELECT COUNT(*) INTO qtd FROM bluebank.role WHERE role_id = 1 and role = 'ADMIN';
    IF qtd = 0 THEN
		INSERT INTO `bluebank`.`role` VALUES (1,'ADMIN');
        COMMIT;
	END IF;
	
	-- TIPO OPERACAO
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.tipo_operacao;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`tipo_operacao` (`id`, `data_alteracao`, `descricao`) VALUES (1, now(), 'Crédito');
        INSERT INTO `bluebank`.`tipo_operacao` (`id`, `data_alteracao`, `descricao`) VALUES (2, now(), 'Débito');
        COMMIT;
	END IF;	
	
	-- AGENCIA
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.agencia;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES (1, now(), '1', '101112', 1);

        INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES (2, now(), 2, '202122', 1);

        INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES (3, now(), 3, '303132', 1);

        INSERT INTO `bluebank`.`agencia` (`id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES (4, now(), 4, '404142', 1);
        COMMIT;
	END IF;
	
	-- CLIENTES
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.cliente;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
        VALUES ('1', '45657006542', now(), 'joao@seuemail.com', 'Rua de João ', 'João Alberto', '1');

        INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
        VALUES ('2', '94704634477', now(), 'maria@seuemail.com', 'Rua de Maria', 'Maria Clara', '1');

        INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
        VALUES ('3', '22987703015', now(), 'claudio@seuemail.com', 'Rua de Cláudio', 'Claudio Luiz', '1');

        INSERT INTO `bluebank`.`cliente` (`id`, `cpf`, `data_alteracao`, `email`, `endereco`, `nome`, `status`)
        VALUES ('4', '31825535850', now(), 'roberta@seuemail.com', 'Rua de Roberta', 'Roberta Miranda', '1');
        COMMIT;
	END IF;

	-- CONTA CORRENTE
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.conta_corrente;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES ('1', '1', '1', now(), '1', '505152', '1');

        INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES ('2', '2', '2', now(), '2', '606162', '1');

        INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES ('3', '3', '3', now(), '3', '707172', '1');

        INSERT INTO `bluebank`.`conta_corrente` (`id`, `agencia_id`, `cliente_id`, `data_alteracao`, `digito`, `numero`, `status`)
        VALUES ('4', '4', '4', now(), '4', '808182', '1');
        COMMIT;
	END IF;
	
	-- USUARIO
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.usuario;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
        VALUES ('1', '45657006542', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '1');

        INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
        VALUES ('2', '94704634477', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '2');

        INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
        VALUES ('3', '22987703015', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '3');

        INSERT INTO `bluebank`.`usuario` (`id`, `cpf`, `data_alteracao`, `senha`, `status`, `cliente_id`)
        VALUES ('4', '31825535850', now(), '$2a$10$lxcIEKek.K0o.PNqFaG/xOPNR97/leepscjFQmwMsomS/wbDsXh.S', '1', '4');
        COMMIT;
	END IF;
	
	-- USUARIO_ROLE
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.usuario_role;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('1', '1');
        INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('2', '1');
        INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('3', '1');
        INSERT INTO `bluebank`.`usuario_role` (`id`, `role_id`) VALUES ('4', '1');
        COMMIT;
	END IF;
	
	-- CONTA_CORRENTE_SALDO
    SET qtd = 0;
    SELECT COUNT(*) INTO qtd FROM bluebank.conta_corrente_saldo;
    IF qtd = 0 THEN
        INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`)
        VALUES ('1', '1', now(), '10000', '1');

        INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`)
        VALUES ('2', '2', now(), '20000', '1');

        INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`)
        VALUES ('3', '3', now(), '30000', '1');

        INSERT INTO `bluebank`.`conta_corrente_saldo` (`id`, `conta_corrente_id`, `data_alteracao`, `saldo`, `versao`)
        VALUES ('4', '4', now(), '40000', '1');
        COMMIT;
	END IF;
END //
DELIMITER ;


call GetRoles;