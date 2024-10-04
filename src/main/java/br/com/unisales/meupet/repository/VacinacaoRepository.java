package br.com.unisales.meupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisales.meupet.table.Vacinacao;

@Repository
public interface VacinacaoRepository extends JpaRepository<Vacinacao, Integer> {
}
